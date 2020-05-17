package krpc.core;

import krpc.client.Connection;
import krpc.client.services.SpaceCenter;

public class Test {
    public static void main(String[] args){
        try {
            SpaceCenter.Vessel vessel = SpaceCenter.newInstance(Connection.newInstance()).getActiveVessel();
            while (true){
                Thread.sleep(500);
                int stage = vessel.getControl().getCurrentStage();
                System.out.println(stage);
                System.out.println(vessel.resourcesInDecoupleStage(stage-1, false).getNames());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
