package krpc.main;

import com.fazecast.jSerialComm.*;
import krpc.client.Connection;
import krpc.client.RPCException;
import krpc.client.services.*;
import krpc.client.services.SpaceCenter.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class KRPCClient {
    public static Control control;
    public static InputStream in;
    public static OutputStream STM32;
    public static Vessel vessel;
    public static SpaceCenter spaceCenter;
    public static Camera camera;
    public static String osName;
    public static boolean sendTimeForAPPE;
    public static long refTime = System.currentTimeMillis();
    static CommunicationManager commManager;

    public static void main(String[] args) {//arg1 = debugLevel, arg2 = connectKRPC
        try {
            //region arguments
            if (args.length > 0) {
                Logger.logLevel = Integer.parseInt(args[0]);
                Logger.INFO("Niveau de debug choisi: " + args[0]);
            }
            if (args.length > 1) {
                CommunicationManager.connectKrpc = Boolean.parseBoolean(args[1]);
                if (!Boolean.parseBoolean(args[1])) {
                    Logger.WARNING("Connection au serveur KRPC désactivée");
                }
            }
            //endregion arguments
            //region connect serial
            SerialPort commPort;
            osName = System.getProperty("os.name");
            Logger.INFO("Système " + osName + " détecté");

            boolean firstTime = true;
            do {
                if (!firstTime) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                firstTime = false;
                commPort = WaitFor.connectDevice();

                commPort.openPort();
                commPort.setComPortParameters(115200, 8, 1, SerialPort.NO_PARITY);
                commPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING | SerialPort.TIMEOUT_WRITE_BLOCKING, 1000, 1000);
                in = commPort.getInputStream();
                if (in == null) {
                    Logger.ERROR("Impossible de récupérer le flux entrant, réessai plus tard");
                }
            }
            while (in == null);
            STM32 = commPort.getOutputStream();
            //endregion connect serial
            //region connect krpc
            if (CommunicationManager.connectKrpc) {
                Connection connection = WaitFor.connectKrpc();
                KRPC krpc = KRPC.newInstance(connection);
                Logger.INFO("Connecté à KRPC version: " + krpc.getStatus().getVersion());
                spaceCenter = SpaceCenter.newInstance(connection);
                vessel = WaitFor.activeVessel(spaceCenter);
                control = vessel.getControl();
                camera = spaceCenter.getCamera();
            }

            commManager = new CommunicationManager();
            //endregion connect krpc

            while (true) {
                if (!commManager.handShake()) {
                    Logger.ERROR("Echec du Handshake");
                } else {
                    Logger.DEBUG("Handshake réussi");
                }
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
                commManager.sendSAS();
                commManager.getActionGroups();
                commManager.sendActionGroups();
                if (sendTimeForAPPE) {
                    commManager.sendAPTime();
                    commManager.sendPETime();
                } else {
                    commManager.sendAPAlt();
                    commManager.sendPEAlt();
                }
            }
        } catch (RPCException rpc) {
            Logger.ERROR("Encore une RPCException ¯\\_(ツ)_/¯ (Le serveur KRPC est probablement stoppé)");
        } catch (UnknownOSException e) {
            Logger.ERROR("Mais t'es sur quel OS PUTAIN!!");
        } catch (IOException e) {
            Logger.INFO("Panneau déconnecté, fin d'execution");
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                in.close();
                STM32.close();
            }catch (IOException e){
                System.exit(0);
            }
        }
    }
}
