package krpc.main;

//Queue that stores analog values to calculate a mean on it
public class AnalogQueue {
    private short occupied = 0;
    private short pos = 0;
    private int[] array;

    public AnalogQueue(short size) {
        array = new int[size];
    }

    public void push(int value) {
        array[pos++] = value;
        pos %= array.length;
        if (occupied < array.length) {
            occupied++;
        }
    }

    public int mean() {
        long out = 0;
        for (int val : array) {
            out += val;
        }
        return (int) (out / occupied);
    }
}
