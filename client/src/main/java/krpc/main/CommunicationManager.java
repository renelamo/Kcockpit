package krpc.main;

import krpc.client.RPCException;
import krpc.client.services.SpaceCenter;
import krpc.client.services.SpaceCenter.Camera;

import java.io.IOException;

import static java.lang.Math.min;
import static java.lang.Math.max;
import static krpc.main.KRPCClient.*;

public class CommunicationManager implements CommTable {

    static final boolean connectKrpc=true;
    private static final float xCameraSpeed = 10f;//en ° par seconde
    private static long lastTimeX = System.currentTimeMillis();
    private static final float yCameraSpeed = 10f;//en ° par seconde
    private static long lastTimeY = System.currentTimeMillis();
    private static final float zCameraSpeed = 10f;//en m par seconde
    private static long lastTimeZ = System.currentTimeMillis();

    private boolean waitSerial() throws IOException{
        long entryTime = System.currentTimeMillis();
        while (in.available()<1){
            try {
                Thread.sleep(1);
                if(System.currentTimeMillis()> entryTime+50){
                    return true;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    /*
    void getStage() throws RPCException, IOException{
        STM32.write(STAGE_CODE);
        waitSerial();
        if(in.read() != 0) {
            control.activateNextStage();
            vessel = spaceCenter.getActiveVessel();
            control = vessel.getControl();
        }
    }
     */


    ////////////////////// ANALOG INPUTS ///////////////////////////////////
    void getThrottle() throws RPCException, IOException{
        float throttleValue;
        STM32.write(THROTTLE_CODE);
        waitSerial();
        throttleValue = (float)(in.read());
        throttleValue /= 255.0;
        if(connectKrpc) {
            control.setThrottle(throttleValue);
        }
        Logger.DEBUG("Throttle=" + throttleValue);
    }

    void getPitch() throws RPCException, IOException{
        float pitchValue;
        STM32.write(PITCH_CODE);
        waitSerial();
        pitchValue = (float)(in.read());
        pitchValue -=128;
        pitchValue /=128;
        if(connectKrpc) {
            control.setPitch(pitchValue);
        }
        Logger.DEBUG("Pitch="+pitchValue);
    }

    void getYaw() throws RPCException, IOException {
        float yawValue;
        STM32.write(YAW_CODE);
        waitSerial();
        yawValue=(float)(in.read());
        yawValue -=128;
        yawValue /=128;
        if(connectKrpc) {
            control.setYaw(yawValue);
        }
        Logger.DEBUG("Yaw="+yawValue);
    }

    void getRoll() throws RPCException, IOException{
        float rollValue;
        STM32.write(ROLL_CODE);
        waitSerial();
        rollValue=(float)(in.read());
        rollValue -=128;
        rollValue /=128;
        if(connectKrpc) {
            control.setRoll(rollValue);
        }
        Logger.DEBUG("Roll="+rollValue);
    }

    void getX() throws RPCException, IOException{
        float xValue;
        STM32.write(X_CODE);
        waitSerial();
        xValue=(float)(in.read());
        xValue -=128;
        xValue /=128;
        if(connectKrpc) {
            long time = System.nanoTime();
            float deltaTime = ((time - lastTimeX) / 1000f);
            lastTimeX = time;

            float val = camera.getPitch()+xValue*xCameraSpeed*deltaTime;
            camera.setPitch(setInBounds(val, camera.getMinPitch(), camera.getMaxPitch()));
        }
        Logger.DEBUG("X="+xValue);
    }

    void getY() throws RPCException, IOException{
        float yValue;
        STM32.write(Y_CODE);
        waitSerial();
        yValue=(float)(in.read());
        yValue -=128;
        yValue /=128;
        if(connectKrpc) {
            long time = System.currentTimeMillis();
            float deltaTime = ((time - lastTimeY)/1000f);
            lastTimeY = time;
            float val = camera.getHeading()+ yValue*deltaTime*yCameraSpeed;
            camera.setHeading(val%360);
        }
        Logger.DEBUG("Y="+yValue);
    }

    void getZ() throws RPCException, IOException{
        float zValue;
        STM32.write(Z_CODE);
        waitSerial();
        zValue=(float)(in.read());
        zValue -=128;
        zValue /=128;
        if(connectKrpc) {
            long time = System.nanoTime();
            float deltaTime = ((time - lastTimeZ) / 1000f);
            lastTimeZ = time;

            float val = camera.getDistance()+zValue*zCameraSpeed*deltaTime;
            camera.setDistance(setInBounds(val, camera.getMinDistance(), camera.getMaxDistance()));
        }
        Logger.DEBUG("Z="+zValue);
    }

    void getT() throws RPCException, IOException{
        float tValue;
        STM32.write(T_CODE);
        waitSerial();
        tValue=(float)(in.read());
        tValue -=128;
        tValue /=128;
        if(connectKrpc) {
            //control.setRoll(tValue);
        }
        Logger.DEBUG("T="+tValue);
    }

    private float setInBounds(float data, float min, float max){
        return max(min(data, max), min);
    }

    //////////////////////////// ACTION GROUPS ////////////////////////////////////
    void getSAS() throws RPCException, IOException{
        STM32.write(SAS_CODE_GET);
        int dataValue;
        waitSerial();
        dataValue=in.read();
        boolean sas=dataValue%2==1;
        Logger.DEBUG("sas="+sas);
        dataValue/=2;
        boolean rcs=dataValue%2==1;
        Logger.DEBUG("rcs="+rcs);
        dataValue/=2;
        boolean lights=dataValue%2==1;
        Logger.DEBUG("lights="+lights);
        dataValue/=2;
        boolean gears=dataValue%2==1;
        Logger.DEBUG("gears="+gears);
        dataValue/=2;
        boolean brakes=dataValue%2==1;
        Logger.DEBUG("brakes="+brakes);
        dataValue/=2;
        boolean stage = dataValue%2==1;
        if(connectKrpc){
            control.setSAS(sas);
            control.setRCS(rcs);
            control.setLights(lights);
            control.setGear(gears);
            control.setLegs(gears);
            control.setWheels(gears);
            control.setBrakes(brakes);
            if(stage){
                control.activateNextStage();
                vessel = spaceCenter.getActiveVessel();
                control = vessel.getControl();
            }
        }
    }

    void sendSAS() throws RPCException, IOException{
        byte out=0x00;
        if(control.getSAS()){
            out+=1;
        }
        if(control.getRCS()){
            out+=2;
        }
        if(control.getLights()){
            out+=4;
        }
        if(control.getGear()){
            out+=8;
        }
        if(control.getBrakes()){
            out+=16;
        }
        STM32.write(SAS_CODE_SET);
        STM32.write(out);
    }

    void getActionGroups() throws IOException, RPCException{
        STM32.write(ACTIONS_CODE_GET);
        int dataValue;
        waitSerial();
        dataValue=in.read();
        boolean custom1=dataValue%2==1;
        dataValue/=2;
        boolean custom2=dataValue%2==1;
        dataValue/=2;
        boolean custom3=dataValue%2==1;
        dataValue/=2;
        boolean custom4=dataValue%2==1;
        dataValue/=2;
        boolean custom5=dataValue%2==1;
        dataValue/=2;
        sendTimeForAPPE = dataValue%2==1;
        if(connectKrpc){
            control.setActionGroup(1, custom1);
            control.setActionGroup(2, custom2);
            control.setActionGroup(3, custom3);
            control.setActionGroup(4, custom4);
            control.setActionGroup(5, custom5);
        }
    }

    void sendActionGroups() throws IOException, RPCException{
        byte out=0x00;
        int toAdd = 1;
        for(int actionGroupNumber = 1; actionGroupNumber<=5; ++actionGroupNumber){
            if(control.getActionGroup(actionGroupNumber)){
                out+=toAdd;
            }
            toAdd*=2;
        }
        STM32.write(ACTIONS_CODE_SET);
        STM32.write(out);
    }

    ///////////////////////////////// 7 SEGMENTS /////////////////////////////////////////////

    void sendAlt() throws RPCException, IOException {
        double alt = vessel.flight(vessel.getSurfaceReferenceFrame()).getSurfaceAltitude();
        int log = (int) Math.log10(alt);
        int puissance10 = log>8 ? log-8 : 0;
        alt/=Math.pow(10, puissance10);
        STM32.write(ALTITUDE_CODE);
        STM32.write(puissance10);
        STM32.write((int)alt);
    }

    void sendMET() throws RPCException, IOException{
        long out = (long) vessel.getMET();
        STM32.write(MET_CODE);
        STM32.write((int)(out>>8));//MSB first
        STM32.write((int)out);
        Logger.DEBUG("MET:" + out);
    }


    void sendElec() throws RPCException, IOException{
        float out = 20 * vessel.getResources().amount("ElectricCharge")/vessel.getResources().max("ElectricCharge");
        STM32.write(ELEC_CODE);
        STM32.write((int)out);
        Logger.DEBUG("Elec : "+ out*5+ "%");
    }

    void sendFuel() throws RPCException, IOException{
        SpaceCenter.Resources resources = vessel.resourcesInDecoupleStage(control.getCurrentStage()-1, false); //On récupère les ressources consommées dans ce stage, donc découplées au stage suivant (n-1)
        //SpaceCenter.Resources resources = vessel.getResources();
        float max = resources.max("SolidFuel")+resources.max("LiquidFuel");
        float out;
        if(max == 0){
            out = 0;
        }else {
            out = 20 * (resources.amount("SolidFuel") + resources.amount("LiquidFuel")) / max;
        }
        STM32.write(FUEL_CODE);
        STM32.write((int)out);
        Logger.DEBUG("Fuel : "+ out*5+ "%");
    }

    void sendOxid() throws RPCException, IOException{
        float ratio = 20 * vessel.getResources().amount("Oxidizer")/vessel.getResources().max("Oxidizer");
        STM32.write(OXID_CODE);
        STM32.write((int)ratio);
        Logger.DEBUG("Oxidizer : "+ ratio*5+ "%");
    }

    void sendMonoP() throws RPCException, IOException{
        float ratio = 20 * vessel.getResources().amount("MonoPropellant")/vessel.getResources().max("MonoPropellant");
        STM32.write(MONOP_CODE);
        STM32.write((int)ratio);
        Logger.DEBUG("Mono Propellant : "+ ratio*5+ "%");
    }

    void sendAPAlt() throws IOException, RPCException{
        STM32.write(AP_ALT_CODE);
        STM32.write((int) vessel.getOrbit().getApoapsisAltitude());
    }

    void sendAPTime() throws IOException, RPCException{
        STM32.write(AP_TIME_CODE);
        STM32.write((int) vessel.getOrbit().getTimeToApoapsis());
    }

    void sendPEAlt() throws IOException, RPCException{
        STM32.write(PE_ALT_CODE);
        STM32.write((int) vessel.getOrbit().getPeriapsisAltitude());

    }

    void sendPETime() throws IOException, RPCException{
        STM32.write(PE_TIME_CODE);
        STM32.write((int) vessel.getOrbit().getTimeToPeriapsis());
    }
}
