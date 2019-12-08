package krpc.main;

import com.fazecast.jSerialComm.SerialPort;
import krpc.client.Connection;
import krpc.client.RPCException;
import krpc.client.services.SpaceCenter;

import static krpc.main.KRPCClient.*;

import java.io.File;

public class WaitFor {
    static Connection connectKrpc()throws InterruptedException{
        int i=0;
        Logger.INFO_START("Connexion à krpc en cours.");
        while(true) {
            try {
                Connection connection = Connection.newInstance("KControls");
                Logger.INFO_END();
                return connection;
            } catch (java.io.IOException e) {
                if(i<2) {
                    System.out.print(".");
                    ++i;
                }else{
                    System.out.print("\b\b  \b\b");
                    i=0;
                }
                Thread.sleep(1000);
            }
        }
    }

    static SerialPort connectDevice() throws UnknownOSException, InterruptedException{
        File ACM0;
        File ACM1;
        switch (osName){ //TODO: ajouter Mac et Solaris pour le fun ^^
            case "Linux":
                ACM0=new File("/dev/ttyACM0");
                ACM1=new File("/dev/ttyACM1");
                break;
            case "Windows":
                ACM0=new File("COM3");
                ACM1=new File("COM4");
            default:
                Logger.ERROR("Le système "+ osName +" détecté est inconnu");
                throw new UnknownOSException();
        }
        Logger.DEBUG("ACM choisi");
        Logger.INFO_START("Connexion au device.");
        int i=0;
        while(!ACM0.exists()&&!ACM1.exists()){
            Thread.sleep(1000);
            if(i<2) {
                System.out.print(".");
                ++i;
            }else{
                System.out.print("\b\b  \b\b");
                i=0;
            }
        }
        Logger.INFO_END();
        Logger.INFO_START("Device connecté");
        if(ACM0.exists()) {
            System.out.println(" sur ACM0");
            Logger.INFO_END();
            return SerialPort.getCommPort(ACM0.getAbsolutePath());
        }
        System.out.println(" sur ACM1");
        Logger.INFO_END();
        return SerialPort.getCommPort(ACM1.getAbsolutePath());
    }

    static SpaceCenter.Vessel activeVessel(SpaceCenter sc) throws InterruptedException{
        int i=0;
        boolean first=true;
        SpaceCenter.Vessel out;
        while(true){
            try{
                out=sc.getActiveVessel();
                System.out.println();
                Logger.INFO("Vaisseau commandé: "+out.getName());
                return out;
            }catch(RPCException e){
                if(first){
                    Logger.INFO_START("Recherche du vaisseau actif.");
                    first=false;
                }else {
                    if (i<2){
                        System.out.print(".");
                        ++i;
                    }else{
                        System.out.print("\b\b  \b\b");
                        i=0;
                    }
                }
                Thread.sleep(1000);

            }
        }
    }
}
