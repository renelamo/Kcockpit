package krpc.main;

import com.fazecast.jSerialComm.*;
import krpc.client.Connection;
import krpc.client.RPCException;
import krpc.client.services.*;
import krpc.client.services.SpaceCenter.*;
import sun.misc.Signal;
import sun.misc.SignalHandler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class KRPCClient implements AutoCloseable {
    //region variables
    public Control control;
    public InputStream in;
    public OutputStream STM32;
    public Vessel vessel;
    public SpaceCenter spaceCenter;
    public Camera camera;
    public String osName;
    public boolean sendTimeForAPPE;
    public long refTime = System.currentTimeMillis();
    CommunicationManager commManager;
    public Logger logger;
    //endregion variables

    public KRPCClient() {
        logger = new Logger(Logger.LogLevel.Debug, System.out);
        commManager = new CommunicationManager(this);
    }

    public static void main(String[] args) {//arg1 = debugLevel, arg2 = connectKRPC
        KRPCClient client = new KRPCClient();
        Signal.handle(new Signal("INT"), signal -> {
            try {
                if (client.in != null) {
                    client.in.close();
                }
                if (client.STM32 != null) {
                    client.STM32.close();
                }
                client.logger.INFO("Fin de l'execution");
                System.exit(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //region args
        if (args.length > 0) {
            client.logger.logLevel = Logger.LogLevel.valueOf(args[0]);
            client.logger.INFO("Niveau de debug choisi: " + client.logger.logLevel.name());
        } else {
            client.logger.INFO("Niveau de debug par défaut: " + client.logger.logLevel);
        }
        if (args.length > 1) {
            client.commManager.connectKrpc = Boolean.parseBoolean(args[1]);
        }
        if (!client.commManager.connectKrpc) {
            client.logger.WARNING("Connection au serveur KRPC désactivée");
        }
        //endregion args

        //region init
        try {
            client.connectSerial();
            client.connectKRPC();
        } catch (RPCException rpc) {
            client.logger.ERROR("Encore une RPCException ¯\\_(ツ)_/¯ (Le serveur KRPC est probablement stoppé)");
        } catch (UnknownOSException e) {
            client.logger.ERROR("Mais t'es sur quel OS PUTAIN!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //endregion init

        while (true) {
            try {
                client.communicate();
            } catch (IOException e) {
                client.logger.WARNING("Panneau déconnecté, tentative de reconnection");
                try {
                    client.connectSerial();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            } catch (RPCException e) {
                client.logger.WARNING("KRPC déconnecté, tentative de reconnection");
                try {
                    client.connectKRPC();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public void connectSerial() throws UnknownOSException, InterruptedException {
        SerialPort commPort;
        osName = System.getProperty("os.name");
        logger.INFO("Système " + osName + " détecté");

        boolean firstTime = true;
        do {
            if (!firstTime) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                firstTime = false;
            }

            commPort = this.connectDevice();

            commPort.openPort();
            commPort.setComPortParameters(115200, 8, 1, SerialPort.NO_PARITY);
            commPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING | SerialPort.TIMEOUT_WRITE_BLOCKING, 100, 100);
            in = commPort.getInputStream();
            if (in == null) {
                logger.ERROR("Impossible de récupérer le flux entrant, réessai plus tard");
            }
        }
        while (in == null);
        STM32 = commPort.getOutputStream();
    }

    public void connectKRPC() throws RPCException, InterruptedException {
        if (commManager.connectKrpc) {
            int i = 0;
            logger.INFO_START("Connexion à krpc en cours.");
            Connection connection;
            while (true) {
                try {
                    connection = Connection.newInstance("KControls");
                    break;
                } catch (java.io.IOException e) {
                    if (i < 2) {
                        System.out.print(".");
                        ++i;
                    } else {
                        System.out.print("\b\b  \b\b");
                        i = 0;
                    }
                    Thread.sleep(1000);
                }
            }
            logger.INFO_END();
            KRPC krpc = KRPC.newInstance(connection);
            logger.INFO("Connecté à KRPC version: " + krpc.getStatus().getVersion());
            spaceCenter = SpaceCenter.newInstance(connection);
            i = 0;
            boolean first = true;
            SpaceCenter.Vessel out;
            while (true) {
                try {
                    vessel = spaceCenter.getActiveVessel();
                    System.out.println();
                    logger.INFO("Vaisseau commandé: " + vessel.getName());
                    break;
                } catch (RPCException e) {
                    if (first) {
                        logger.INFO_START("Recherche du vaisseau actif.");
                        first = false;
                    } else {
                        if (i < 2) {
                            System.out.print(".");
                            ++i;
                        } else {
                            System.out.print("\b\b  \b\b");
                            i = 0;
                        }
                    }
                    Thread.sleep(1000);

                }
            }
            control = vessel.getControl();
            camera = spaceCenter.getCamera();
        }
    }

    public void communicate() throws IOException, RPCException {
        while (true) {
            /*
            if (!commManager.handShake()) {
                logger.ERROR("Echec du Handshake");
            } else {
                logger.DEBUG("Handshake réussi");
            }*/
            commManager.sendElec();
            commManager.sendFuel();
            commManager.sendOxid();
            commManager.sendMonoP();
            commManager.sendMET();
            commManager.sendAlt();
            commManager.getPitch();
            commManager.getRoll();
            commManager.getYaw();
            commManager.getThrottle();
            commManager.getSAS();
            commManager.sendLEDs();
            commManager.sendAlt();
            commManager.getActionGroups();
            if (sendTimeForAPPE) {
                commManager.sendAPTime();
                commManager.sendPETime();
            } else {
                commManager.sendAPAlt();
                commManager.sendPEAlt();
            }
        }
    }

    SerialPort connectDevice() throws UnknownOSException, InterruptedException {
        File ACM0;
        File ACM1;
        switch (osName) { //TODO: ajouter Mac et Solaris pour le fun ^^
            case "Linux":
                ACM0 = new File("/dev/ttyACM0");
                ACM1 = new File("/dev/ttyACM1");
                break;
            case "Windows":
                ACM0 = new File("COM3");
                ACM1 = new File("COM4");
            default:
                logger.ERROR("Le système " + osName + " détecté est inconnu");
                throw new UnknownOSException();
        }
        logger.DEBUG("Préfixe ACM choisi");
        logger.INFO_START("Connexion au panneau en cours.");
        int i = 0;
        while (!ACM0.exists() && !ACM1.exists()) {
            Thread.sleep(1000);
            if (i < 2) {
                System.out.print(".");
                ++i;
            } else {
                System.out.print("\b\b  \b\b");
                i = 0;
            }
        }
        logger.INFO_END();
        logger.INFO_START("Connexion au panneau établie");
        if (ACM0.exists()) {
            System.out.println(" sur ACM0");
            logger.INFO_END();
            return SerialPort.getCommPort(ACM0.getAbsolutePath());
        }
        System.out.println(" sur ACM1");
        logger.INFO_END();
        return SerialPort.getCommPort(ACM1.getAbsolutePath());
    }

    @Override
    public void close() throws Exception {
        in.close();
        STM32.close();
    }
}
