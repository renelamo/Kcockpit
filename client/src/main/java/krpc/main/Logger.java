package krpc.main;

import java.io.PrintStream;

public class Logger {
    public LogLevel logLevel = LogLevel.Debug;
    public PrintStream stream = System.out;

    public Logger(LogLevel logLevel, PrintStream stream) {
        this.logLevel = logLevel;
        if (stream != null) {
            this.stream = stream;
        }
    }

    public void INFO(Object o) {
        if (logLevel.filter(LogLevel.Info)) {
            stream.println("\u001B[32m[INFO] " + o + "\u001B[0m");
        }
    }

    public void DEBUG(Object o) {
        if (logLevel.filter(LogLevel.Debug)) {
            stream.println("\u001B[34m[DEBUG] " + o + "\u001B[0m");
        }
    }

    public void WARNING(Object o){
        if (logLevel.filter(LogLevel.Warning)) {
            stream.println("\u001B[33m[WARNING] " + o + "\u001B[0m");
        }
    }

    public void INFO_START(Object o) {
        if (logLevel.filter(LogLevel.Info)) {
            stream.print("\u001B[32m[INFO] " + o);
            stream.flush();
        }
    }

    public void INFO_END() {
        if (logLevel.filter(LogLevel.Info)) {
            stream.println("\u001B[0m");
        }
    }

    public enum LogLevel {
        Error(0),
        Warning(1),
        Info(2),
        Debug(3);

        private final int level;

        LogLevel(int i) {
            level = i;
        }

        boolean filter(LogLevel reference) {
            return this.level <= reference.level;
        }
    }

    public void ERROR(Object o) {
        stream.println("\u001B[31m[ERROR] " + o + "\u001B[0m");
    }
}
