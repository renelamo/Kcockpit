package krpc.main;

import java.io.PrintStream;

public class Logger {
    public int logLevel = 3;
    public PrintStream stream = System.out;

    public Logger(int logLevel, PrintStream stream){
        if(logLevel < 4 && logLevel >= 0) {
            this.logLevel = logLevel;
        }
        if(stream != null) {
            this.stream = stream;
        }
    }

    public void INFO(Object o) {
        if(logLevel>1) {
            stream.println("\u001B[32m[INFO] " + o +"\u001B[0m");
        }
    }
    public void DEBUG(Object o) {
        if(logLevel>2) {
            stream.println("\u001B[34m[DEBUG] " + o +"\u001B[0m");
        }
    }
    public void WARNING(Object o){
        if(logLevel>0) {
            stream.println("\u001B[33m[WARNING] " + o +"\u001B[0m");
        }
    }
    public void INFO_START(Object o) {
        if(logLevel>1) {
            stream.print("\u001B[32m[INFO] " + o);
            stream.flush();
        }
    }
    public void INFO_END(){
        if(logLevel>1) {
            stream.println("\u001B[0m");
        }
    }
    public void ERROR(Object o) { stream.println("\u001B[31m[ERROR] "+o +"\u001B[0m");}
}
