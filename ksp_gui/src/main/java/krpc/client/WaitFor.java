package krpc.client;

import com.fazecast.jSerialComm.SerialPort;
import krpc.client.services.SpaceCenter;

import static krpc.client.KRPCClient.*;

import java.io.File;

public class WaitFor {
    static Connection connectKrpc()throws InterruptedException{
        int i=0;
        out("Connexion à krpc en cours.");
        while(true) {
            try {
                Connection connection = Connection.newInstance("KControls");
                System.out.println();
                return connection;
            } catch (java.io.IOException e) {
                if(i<2) {
                    System.out.print(".");
                    ++i;
                }else{
                    System.out.print("\b\b");
                    i=0;
                }
                Thread.sleep(1000);
            }
        }
    }

    static SerialPort connectDevice() throws UnknownOSException, InterruptedException{
        File ACM0;
        File ACM1;
        switch (os){ //TODO: ajouter Mac et Solaris pour le fun ^^
            case "Linux":
                ACM0=new File("/dev/ttyACM0");
                ACM1=new File("/dev/ttyACM1");
                break;
            case "Windows":
                ACM0=new File("COM3");
                ACM1=new File("COM4");
            default:
                System.err.println("[ERROR] Le système "+os+" détecté est inconnu");
                throw new UnknownOSException();
        }

        out("Connexion au device.");
        int i=0;
        while(!ACM0.exists()&&!ACM1.exists()){
            Thread.sleep(1000);
            if(i<2) {
                System.out.print(".");
                ++i;
            }else{
                System.out.print("\b\b");
                i=0;
            }
        }
        System.out.println();
        out("Device connecté");
        if(ACM0.exists()) {
            System.out.println(" sur ACM0");
            return SerialPort.getCommPort(ACM0.getAbsolutePath());
        }
        System.out.println(" sur ACM1");
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
                OUT("Vaisseau commandé: "+out.getName());
                return out;
            }catch(RPCException e){
                if(first){
                    out("Recherche du vaisseau actif.");
                    first=false;
                }else {
                    if (i<2){
                        System.out.print(".");
                        ++i;
                    }else{
                        System.out.print("\b\b");
                        i=0;
                    }
                }
                Thread.sleep(1000);

            }
        }
    }
}
