package krpc.main;

import krpc.client.RPCException;
import krpc.client.services.SpaceCenter;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static java.lang.Math.*;

public class CommunicationManager implements CommTable {

    boolean connectKrpc = true;
    private final float xCameraSpeed = 10f;//en ° par seconde
    private final float yCameraSpeed = 10f;//en ° par seconde
    private final float zCameraSpeed = 10f;//en m par seconde
    private long lastTimeX = System.currentTimeMillis();
    private long lastTimeY = System.currentTimeMillis();
    private long lastTimeZ = System.currentTimeMillis();
    private KRPCClient client;

    public CommunicationManager(KRPCClient client) {
        this.client = client;
    }

    private void waitSerial() throws IOException { //TODO: peut être que cette fonction est réalisée dans in.read()
        long entryTime = System.currentTimeMillis();
        while (client.in.available() < 1) {
            try {
                Thread.sleep(1);
                if (System.currentTimeMillis() > entryTime + 50) {
                    client.logger.WARNING("Timeout sur la communication série dans \""+
                            Thread.currentThread().getStackTrace()[2].getMethodName()+
                            "\"");
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    boolean handShake() throws IOException {
        while (client.in.available() > 0) {
            client.in.read();
        }
        client.STM32.write(HANDSHAKE_CODE);
        waitSerial();
        return client.in.read() == HANDSHAKE_CODE;
    }

    ////////////////////// ANALOG INPUTS ///////////////////////////////////
    //region analog
    void getThrottle() throws RPCException, IOException {
        float throttleValue;
        client.STM32.write(THROTTLE_CODE);
        waitSerial();
        throttleValue = (float) (client.in.read());
        throttleValue /= 255.0;
        if (connectKrpc) {
            client.control.setThrottle(throttleValue);
        }
        client.logger.DEBUG("Throttle=" + throttleValue);
    }

    void getPitch() throws RPCException, IOException {
        float pitchValue;
        client.STM32.write(PITCH_CODE);
        waitSerial();
        pitchValue = (float) (client.in.read());
        pitchValue -= 128;
        pitchValue /= 128;
        if (connectKrpc) {
            client.control.setPitch(pitchValue);
        }
        client.logger.DEBUG("Pitch=" + pitchValue);
    }

    void getYaw() throws RPCException, IOException {
        float yawValue;
        client.STM32.write(YAW_CODE);
        waitSerial();
        yawValue = (float) (client.in.read());
        yawValue -= 128;
        yawValue /= 128;
        if (connectKrpc) {
            client.control.setYaw(yawValue);
        }
        client.logger.DEBUG("Yaw=" + yawValue);
    }

    void getRoll() throws RPCException, IOException {
        float rollValue;
        client.STM32.write(ROLL_CODE);
        waitSerial();
        rollValue = (float) (client.in.read());
        rollValue -= 128;
        rollValue /= 128;
        if (connectKrpc) {
            client.control.setRoll(rollValue);
        }
        client.logger.DEBUG("Roll=" + rollValue);
    }

    void getX() throws RPCException, IOException {
        float xValue;
        client.STM32.write(X_CODE);
        waitSerial();
        xValue = (float) (client.in.read());
        xValue -= 128;
        xValue /= 128;
        if (connectKrpc) {
            long time = System.nanoTime();
            float deltaTime = ((time - lastTimeX) / 1000f);
            lastTimeX = time;

            float val = client.camera.getPitch() + xValue * xCameraSpeed * deltaTime;
            client.camera.setPitch(crop(val, client.camera.getMinPitch(), client.camera.getMaxPitch()));
        }
        client.logger.DEBUG("X=" + xValue);
    }

    void getY() throws RPCException, IOException {
        float yValue;
        client.STM32.write(Y_CODE);
        waitSerial();
        yValue = (float) (client.in.read());
        yValue -= 128;
        yValue /= 128;
        if (connectKrpc) {
            long time = System.currentTimeMillis();
            float deltaTime = ((time - lastTimeY) / 1000f);
            lastTimeY = time;
            float val = client.camera.getHeading() + yValue * deltaTime * yCameraSpeed;
            client.camera.setHeading(val % 360);
        }
        client.logger.DEBUG("Y=" + yValue);
    }

    void getZ() throws RPCException, IOException {
        float zValue;
        client.STM32.write(Z_CODE);
        waitSerial();
        zValue = (float) (client.in.read());
        zValue -= 128;
        zValue /= 128;
        if (connectKrpc) {
            long time = System.nanoTime();
            float deltaTime = ((time - lastTimeZ) / 1000f);
            lastTimeZ = time;

            float val = client.camera.getDistance() + zValue * zCameraSpeed * deltaTime;
            client.camera.setDistance(crop(val, client.camera.getMinDistance(), client.camera.getMaxDistance()));
        }
        client.logger.DEBUG("Z=" + zValue);
    }

    void getT() throws RPCException, IOException {
        float tValue;
        client.STM32.write(T_CODE);
        waitSerial();
        tValue = (float) (client.in.read());
        tValue -= 128;
        tValue /= 128;
        if (connectKrpc) {
            //client.control.setRoll(tValue);//TODO: trouver une utilité à cette fonction
        }
        client.logger.DEBUG("T=" + tValue);
    }

    private float crop(float data, float min, float max) {
        return max(min(data, max), min);
    }

    /**
     * Ajoute des deadZones à data aux extrémités et au centre de la plage de valeurs
     *
     * @param data Valeur à tester
     * @param p1   Valeur maximale de data pour retourner-1
     * @param p2   Valeur minimale de data pour retourner 0
     * @param p3   Valeur maximale de data pour retourner 0
     * @param p4   Valeur minimale de data pour retourner 1
     * @return Un flottant entre -1 et 1
     */
    private float deadZones(int data, int p1, int p2, int p3, int p4) {
        if (data <= p1) {
            return -1;
        }
        if (data <= p2) {
            return ((float) data - p2) / (p2 - p1);
        }
        if (data <= p3) {
            return 0;
        }
        if (data <= p4) {
            return ((float) data - p3) / (p4 - p3);
        }
        return 1;
    }

    /**
     * Ajoute des deadZones à data aux extrémités de la plage de valeurs
     *
     * @param data Valeur à tester
     * @param p1   Valeur maximale de data pour retourner 0
     * @param p2   Valeur minimale de data pour retourner 1
     * @return Un flottant entre 0 et 1
     */
    private float deadZones(int data, int p1, int p2) {
        if (data <= p1) {
            return 0;
        }
        if (data <= p2) {
            return ((float) data - p1) / (p2 - p1);
        }
        return 1;
    }
    //endregion

    //region action_groups
    //////////////////////////// ACTION GROUPS ////////////////////////////////////
    void getSAS() throws RPCException, IOException {
        client.STM32.write(SAS_CODE_GET);
        int dataValue;
        waitSerial();
        dataValue = client.in.read();
        boolean sas = (dataValue & 1) > 0;
        client.logger.DEBUG("sas=" + sas);
        boolean rcs = (dataValue & 2) > 0;
        client.logger.DEBUG("rcs=" + rcs);
        boolean lights = (dataValue & 4) > 0;
        client.logger.DEBUG("lights=" + lights);
        boolean gears = (dataValue & 8) > 0;
        client.logger.DEBUG("gears=" + gears);
        boolean brakes = (dataValue & 16) > 0;
        client.logger.DEBUG("brakes=" + brakes);
        boolean stage = (dataValue & 32) > 0;
        if (connectKrpc) {
            client.control.setSAS(sas);
            client.control.setRCS(rcs);
            client.control.setLights(lights);
            client.control.setGear(gears);
            client.control.setLegs(gears);
            client.control.setWheels(gears);
            client.control.setBrakes(brakes);
            if (stage) {
                client.control.activateNextStage();
                client.vessel = client.spaceCenter.getActiveVessel();
                client.control = client.vessel.getControl();
            }
        }
    }

    void sendLEDs() throws RPCException, IOException {
        short out = 0x00;
        if (connectKrpc) {
            if (client.control.getSAS()) {
                out += 1;
            }
            if (client.control.getRCS()) {
                out += 2;
            }
            if (client.control.getLights()) {
                out += 4;
            }
            if (client.control.getGear()) {
                out += 8;
            }
            if (client.control.getBrakes()) {
                out += 16;
            }
        }
        if((System.currentTimeMillis()/500)%2 == 0) {
            out += 32;
        }

        int toAdd = 1<<8;
        if (connectKrpc) {
            for (int actionGroupNumber = 1; actionGroupNumber <= 5; ++actionGroupNumber) {
                if (client.control.getActionGroup(actionGroupNumber)) {
                    out += toAdd;
                }
                toAdd *= 2;
            }
        }
        client.STM32.write(LEDS_CODE_SET);
        client.STM32.write(ByteBuffer.allocate(2).order(ByteOrder.nativeOrder()).putShort(out).array());
        client.logger.DEBUG("LEDs data: " + out);
    }

    void getActionGroups() throws IOException, RPCException {
        client.STM32.write(ACTIONS_CODE_GET);
        int dataValue;
        waitSerial();
        dataValue = client.in.read();
        boolean custom1 = dataValue % 2 == 1;
        dataValue /= 2;
        boolean custom2 = dataValue % 2 == 1;
        dataValue /= 2;
        boolean custom3 = dataValue % 2 == 1;
        dataValue /= 2;
        boolean custom4 = dataValue % 2 == 1;
        dataValue /= 2;
        boolean custom5 = dataValue % 2 == 1;
        dataValue /= 2;
        client.sendTimeForAPPE = dataValue % 2 == 1;
        if (connectKrpc) {
            client.control.setActionGroup(1, custom1);
            client.control.setActionGroup(2, custom2);
            client.control.setActionGroup(3, custom3);
            client.control.setActionGroup(4, custom4);
            client.control.setActionGroup(5, custom5);
        }
    }
    //endregion

    //region Bargraphs
    void sendElec() throws RPCException, IOException {
        float out = 0;
        if (connectKrpc)
            out = 20 * client.vessel.getResources().amount("ElectricCharge") / client.vessel.getResources().max("ElectricCharge");
        client.STM32.write(ELEC_CODE);
        client.STM32.write((int) out);
        client.logger.DEBUG("Elec : " + out * 5 + "%");
    }

    void sendFuel() throws RPCException, IOException {
        float out = 0;
        if (connectKrpc) {
            SpaceCenter.Resources resources = client.vessel.resourcesInDecoupleStage(client.control.getCurrentStage() - 1, false); //On récupère les ressources consommées dans ce stage, donc découplées au stage suivant (n-1)
            //SpaceCenter.Resources resources = client.vessel.getResources();
            float max = resources.max("SolidFuel") + resources.max("LiquidFuel");
            if (max == 0) { //Prevent dividing by 0 if no fuel tank detected
                out = 0;
            } else {
                out = 20 * (resources.amount("SolidFuel") + resources.amount("LiquidFuel")) / max;
            }
        }
        client.STM32.write(FUEL_CODE);
        client.STM32.write((int) out);
        client.logger.DEBUG("Fuel : " + out * 5 + "%");
    }

    void sendOxid() throws RPCException, IOException {
        float ratio = 0;
        if (connectKrpc)
            ratio = 20 * client.vessel.getResources().amount("Oxidizer") / client.vessel.getResources().max("Oxidizer");
        client.STM32.write(OXID_CODE);
        client.STM32.write((int) ratio);
        client.logger.DEBUG("Oxidizer : " + ratio * 5 + "%");
    }

    void sendMonoP() throws RPCException, IOException {
        float ratio = 0;
        if (connectKrpc)
            ratio = 20 * client.vessel.getResources().amount("MonoPropellant") / client.vessel.getResources().max("MonoPropellant");
        client.STM32.write(MONOP_CODE);
        client.STM32.write((int) ratio);
        client.logger.DEBUG("Mono Propellant : " + ratio * 5 + "%");
    }
    //endregion

    ///////////////////////////////// 7 SEGMENTS /////////////////////////////////////////////
    //region 7_seg
    void sendAlt() throws RPCException, IOException {
        long alt;
        if (connectKrpc) {
            //TODO: Fix potential narrowing conversion
            alt = (long) client.vessel.flight(client.vessel.getSurfaceReferenceFrame()).getSurfaceAltitude();
        } else {
            alt = (long) (Math.PI * 10e8);
        }
        client.STM32.write(ALTITUDE_CODE);
        client.STM32.write(ByteBuffer.allocate(Long.BYTES).order(ByteOrder.nativeOrder()).putLong(alt).array());
        client.logger.DEBUG("Altitude: " + alt);
    }

    void sendMET() throws RPCException, IOException {
        long out;
        if (connectKrpc) {
            out = (long) client.vessel.getMET();//TODO fix potential narrowing conversion
        } else {
            out = (System.currentTimeMillis() - client.refTime) / 100;
        }
        client.STM32.write(MET_CODE);
        client.STM32.write(ByteBuffer.allocate(Long.BYTES).order(ByteOrder.nativeOrder()).putLong(out).array());
        client.logger.DEBUG("MET:" + out);
    }

    void sendAPAlt() throws IOException, RPCException {
        if (connectKrpc) {
            client.STM32.write(AP_ALT_CODE);
            client.STM32.write(ByteBuffer.allocate(Long.BYTES).order(ByteOrder.nativeOrder())
                    .putLong((long) client.vessel.getOrbit().getApoapsisAltitude()).array());
        }
    }

    void sendAPTime() throws IOException, RPCException {
        if (connectKrpc) {
            client.STM32.write(AP_TIME_CODE);
            client.STM32.write(ByteBuffer.allocate(Long.BYTES).order(ByteOrder.nativeOrder())
                    .putLong((long) client.vessel.getOrbit().getTimeToApoapsis()).array());
        }
    }

    void sendPEAlt() throws IOException, RPCException {
        if (connectKrpc) {
            client.STM32.write(PE_ALT_CODE);
            client.STM32.write(ByteBuffer.allocate(Long.BYTES).order(ByteOrder.nativeOrder())
                    .putLong((long) client.vessel.getOrbit().getPeriapsisAltitude()).array());
        }
    }

    void sendPETime() throws IOException, RPCException {
        if (connectKrpc) {
            client.STM32.write(PE_TIME_CODE);
            client.STM32.write(ByteBuffer.allocate(Long.BYTES).order(ByteOrder.nativeOrder())
                    .putLong((long) client.vessel.getOrbit().getTimeToPeriapsis()).array());
        }
    }
    //endregion
}
