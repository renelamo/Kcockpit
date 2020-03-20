package krpc.main;

public class Logger {
    public static int logLevel = 3;
    public static void INFO(Object o) {
        if(logLevel>1) {
            System.out.println("\u001B[32m[INFO] " + o +"\u001B[0m");
        }
    }
    public static void DEBUG(Object o) {
        if(logLevel>2) {
            System.out.println("\u001B[34m[DEBUG] " + o +"\u001B[0m");
        }
    }
    public static void WARNING(Object o){
        if(logLevel>0) {
            System.out.println("\u001B[33m[WARNING] " + o +"\u001B[0m");
        }
    }
    public static void INFO_START(Object o) {
        if(logLevel>1) {
            System.out.print("\u001B[32m[INFO] " + o);
            System.out.flush();
        }
    }
    public static void INFO_END(){
        if(logLevel>1) {
            System.out.println("\u001B[0m");
        }
    }
    public static void ERROR(Object o) { System.out.println("\u001B[31m[ERROR] "+o +"\u001B[0m");}
}
