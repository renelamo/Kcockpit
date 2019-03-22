package krpc.client;

import com.fazecast.jSerialComm.SerialPort;
import krpc.client.services.SpaceCenter;

import java.io.File;

public class WaitFor {
    static Connection connetcKrpc()throws InterruptedException{
        int i=0;
        Main.sop("Connexion à krpc en cours.");
        while(true) {
            try {
                Connection connection = Connection.newInstance("KControls");
                Main.SOP("");
                return connection;
            } catch (java.io.IOException e) {
                if(i<2) {
                    Main.sop(".");
                    ++i;
                }else{
                    Main.sop("\b\b");
                    i=0;
                }
                Thread.sleep(1000);
            }
        }
    }

    static SerialPort connectDevice() throws UnknownOSException, InterruptedException{
        File ACM0;
        switch (System.getProperty("os.name")){ //TODO: ajouter Mac et Solaris pour le fun ^^
            case "Linux":
                Main.SOP("Système Linux détecté");
                ACM0=new File("/dev/ttyACM0");
                break;
            case "Windows":
                Main.SOP("Système Windows détecté");
                ACM0=new File("COM3");
            default:
                System.err.println("Erreur: Le système "+System.getProperty("os.name")+" détecté est inconnu");
                throw new UnknownOSException();
        }

        Main.sop("Connexion au device.");
        int i=0;
        while(!ACM0.exists()){
            Thread.sleep(1000);
            if(i<2) {
                Main.sop(".");
                ++i;
            }else{
                Main.sop("\b\b");
                i=0;
            }
        }
        Main.SOP("");
        Main.SOP("Device connecté!");
        return SerialPort.getCommPort(ACM0.getAbsolutePath());
    }

    static SpaceCenter.Vessel activeVessel(SpaceCenter sc) throws InterruptedException{
        int i=0;
        boolean first=true;
        SpaceCenter.Vessel out;
        while(true){
            try{
                out=sc.getActiveVessel();
                Main.SOP("");
                Main.SOP("Vaisseau commandé: "+out.getName());
                return out;
            }catch(RPCException e){
                if(first){
                    Main.sop("Recherche du vaisseau actif.");
                    first=false;
                }else {
                    if (i<2){
                        Main.sop(".");
                        ++i;
                    }else{
                        Main.sop("\b\b");
                        i=0;
                    }
                }
                Thread.sleep(1000);

            }
        }
    }
}
