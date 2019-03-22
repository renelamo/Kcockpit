package krpc.client;

import com.fazecast.jSerialComm.*;
import krpc.client.services.*;
import krpc.client.services.SpaceCenter.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.Object;



public class Main{

    public static void main(String[] args) {
        try {
            InputStream in;
            SerialPort commPort;
            do {
                commPort = WaitFor.connectDevice();

                commPort.openPort();
                commPort.setBaudRate(115200);
                commPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING | SerialPort.TIMEOUT_WRITE_BLOCKING, 1000, 1000);
                in = commPort.getInputStream();
            }
            while (in==null);
            OutputStream out = commPort.getOutputStream();

            Connection connection = WaitFor.connetcKrpc();
            KRPC krpc = KRPC.newInstance(connection);
            System.out.println("Connected to kRPC version " + krpc.getStatus().getVersion());
            SpaceCenter spaceCenter = SpaceCenter.newInstance(connection);
            Vessel vessel= WaitFor.activeVessel(spaceCenter);
            Control control = vessel.getControl();

            while (true) {
                Thread.sleep(5);
                if (in.available() > 0) {
                    int incommingByte=in.read();
                    SOP("Recu " + incommingByte);
                    if (incommingByte==1) {
			            control.activateNextStage();
			            vessel = spaceCenter.getActiveVessel();
			            control=vessel.getControl();
                        System.out.println("stage");
                    } else {
                        System.out.println("reçu autre chose");
                    }
                    out.write(0);
                }

            }

        }
        catch (RPCException rpc){
            SOP("Encore une RPCException :-(");
        }
        catch (UnknownOSException e){
            SOP("Mais t'es sur quel OS PUTAING!!");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    static void SOP(Object o){
        System.out.println(o);
    }
    static void sop(Object o){
        System.out.print(o);
    }
}
