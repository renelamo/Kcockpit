package krpc.main;

import com.fazecast.jSerialComm.*;
import krpc.client.Connection;
import krpc.client.RPCException;
import krpc.client.services.*;
import krpc.client.services.SpaceCenter.*;

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
    static CommunicationManager commManager;

    public static void main(String[] args) {
        try {
            SerialPort commPort;
            osName =System.getProperty("os.name");
            Logger.INFO("Système "+ osName +" détecté");

            do {
                /*
                try {
                    Thread.sleep(8000); //FIXME: je sais plus pourquoi j'attends ici
                }catch (Exception e){
                    e.printStackTrace();
                }
                 */
                commPort = WaitFor.connectDevice();

                commPort.openPort();
                commPort.setBaudRate(115200);
                commPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING | SerialPort.TIMEOUT_WRITE_BLOCKING, 1000, 1000);
                in = commPort.getInputStream();
                if(in==null){
                    Logger.ERROR("Impossible de récupérer le flux entrant");
                }
            }
            while (in==null);
            STM32 = commPort.getOutputStream();

            if(CommunicationManager.connectKrpc) {
                Connection connection = WaitFor.connectKrpc();
                KRPC krpc = KRPC.newInstance(connection);
                Logger.INFO("Connecté à KRPC version: " + krpc.getStatus().getVersion());
                spaceCenter = SpaceCenter.newInstance(connection);
                vessel = WaitFor.activeVessel(spaceCenter);
                control = vessel.getControl();
                camera = spaceCenter.getCamera();
            }

            commManager = new CommunicationManager();

            while (true) {
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
                //commManager.getStage();
                commManager.getSAS();
                commManager.sendSAS();
                commManager.getActionGroups();
                commManager.sendActionGroups();
                if(sendTimeForAPPE){
                    commManager.sendAPTime();
                    commManager.sendPETime();
                } else {
                    commManager.sendAPAlt();
                    commManager.sendPEAlt();
                }
            }
        }
        catch (RPCException rpc){
            Logger.ERROR("Encore une RPCException ¯\\_(ツ)_/¯");
        }
        catch (UnknownOSException e){
            Logger.ERROR("Mais t'es sur quel OS PUTAING!!");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
