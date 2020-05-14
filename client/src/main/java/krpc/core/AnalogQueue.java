package krpc.core;

import krpc.jfx.AnalogCalibrator;
import org.json.simple.JSONObject;

//Queue that stores analog values to calculate a mean on it
class AnalogQueue {
    private short occupied = 0;
    private short pos = 0;
    private int[] array;

    public boolean doubleDeadZones;
    public int p1;
    public int p2;
    public int p3 = -1;
    public int p4;

    public AnalogQueue(short size, JSONObject calibration) {
        array = new int[size];
        if (calibration == null) {
            p1 = 0;
            p2 = p3 = 127;
            p4 = 255;
        } else {
            p1 = Integer.parseInt((String) calibration.get("p1"));
            p2 = Integer.parseInt((String) calibration.get("p2"));
            if (!calibration.get("p3").equals("")) {
                p3 = Integer.parseInt((String) calibration.get("p3"));
                p4 = Integer.parseInt((String) calibration.get("p4"));
            }
        }
    }

    public AnalogQueue(short size) {
        this(size, null);
    }

    public AnalogQueue push(int value) {
        array[pos++] = value;
        pos %= array.length;
        if (occupied < array.length) {
            occupied++;
        }
        return this;
    }

    private int mean() {
        long out = 0;
        for (int val : array) {
            out += val;
        }
        return (int) (out / occupied);
    }

    private float deadZonesDoubles() {
        int data = mean();
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

    private float deadZonesSimples() {
        int data = mean();
        if (data <= p1) {
            return 0;
        }
        if (data <= p2) {
            return ((float) data - p1) / (p2 - p1);
        }
        return 1;
    }

    public float getVal() {
        return (p3 > 0) ? deadZonesDoubles() : deadZonesSimples();
    }
}
