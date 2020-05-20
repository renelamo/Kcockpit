package kockpit.core;

import com.fazecast.jSerialComm.*;
import krpc.client.Connection;
import krpc.client.RPCException;
import krpc.client.services.*;
import krpc.client.services.SpaceCenter.*;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class KRPCClient implements AutoCloseable {
    public CommunicationManager commManager;
    //region variables
    Control control;
    InputStream in;
    OutputStream STM32;
    Vessel vessel;
    SpaceCenter spaceCenter;
    Camera camera;
    String osName;
    boolean sendTimeForAPPE;
    boolean connectKrpc = true;
    long refTime = System.currentTimeMillis();
    public Logger logger;
    //endregion variables

    public KRPCClient() {
        logger = new Logger(Logger.LogLevel.Debug, System.out);
        commManager = new CommunicationManager(this);
    }

    public static void main(String[] args) {//arg1 = debugLevel, arg2 = connectKRPC
        KRPCClient client = new KRPCClient();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                if (client.in != null) {
                    client.in.close();
                }
                if (client.STM32 != null) {
                    client.STM32.close();
                }
                System.out.println();
                client.logger.INFO("Processus arrêté par l'utilisateur");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }));

        //region args
        if (args.length > 0) {
            client.logger.logLevel = Logger.LogLevel.valueOf(args[0]);
            client.logger.INFO("Niveau de debug choisi: " + client.logger.logLevel.name());
            System.out.println();
        } else {
            client.logger.INFO("Niveau de debug par défaut: " + client.logger.logLevel);
        }
        if (args.length > 1) {
            client.connectKrpc = Boolean.parseBoolean(args[1]);
        }
        if (!client.connectKrpc) {
            client.logger.WARNING("Connection au serveur KRPC désactivée");
        }
        //endregion args

        try {
            client.connectSerial();
            if (client.connectKrpc)
                client.connectKRPC();
            while (true) {
                try {
                    client.communicate();
                }
                catch (IOException e) {
                    client.logger.WARNING("Panneau déconnecté, tentative de reconnection");
                    client.connectSerial();
                }
                catch (RPCException e) {
                    client.logger.WARNING("KRPC déconnecté, tentative de reconnection");
                    if (client.connectKrpc)
                        client.connectKRPC();
                }
            }
        }
        catch (RPCException rpc) {
            client.logger.ERROR("Encore une RPCException ¯\\_(ツ)_/¯");
        }
        catch (UnknownOSException e) {
            client.logger.ERROR("Mais t'es sur quel OS PUTAIN!!");
        }
        catch (Exception e) {
            e.printStackTrace();
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
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                firstTime = false;
            }

            commPort = this.connectDevice();

            commPort.openPort();
            commPort.setComPortParameters(115200, 8, 1, SerialPort.NO_PARITY);
            commPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING | SerialPort.TIMEOUT_WRITE_BLOCKING,
                    100/*impossible de descendre en dessous de 100 ms sur les systèmes Unix*/, 100);
            in = commPort.getInputStream();
            if (in == null) {
                logger.ERROR("Impossible de récupérer le flux entrant, réessai plus tard");
            }
        }
        while (in == null);
        STM32 = commPort.getOutputStream();
    }

    public void connectKRPC() throws RPCException, InterruptedException {
        commManager.connectKrpc = true;
        int i = 0;
        logger.INFO_START("Connexion à krpc en cours.");
        Connection connection;
        while (true) {
            try {
                connection = Connection.newInstance("KControls");
                break;
            }
            catch (java.io.IOException e) {
                if (logger.logLevel.filter(Logger.LogLevel.Info)) {
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
        logger.INFO_END();
        KRPC krpc = KRPC.newInstance(connection);
        logger.INFO("Connecté à KRPC version: " + krpc.getStatus().getVersion());
        spaceCenter = SpaceCenter.newInstance(connection);
        i = 0;
        boolean first = true;
        while (true) {
            try {
                vessel = spaceCenter.getActiveVessel();
                System.out.println();
                logger.INFO("Vaisseau commandé: " + vessel.getName());
                break;
            }
            catch (RPCException e) {
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
            if (logger.logLevel.filter(Logger.LogLevel.Info)) {
                if (i < 2) {
                    System.out.print(".");
                    ++i;
                } else {
                    System.out.print("\b\b  \b\b");
                    i = 0;
                }
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

    public JSONObject getCalibration() {
        return commManager.calibrations;
    }
}
