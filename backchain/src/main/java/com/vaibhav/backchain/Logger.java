package com.vaibhav.backchain;

public abstract class Logger {
    public static int OUTPUTINFO = 0;
    public static int ERRORINFO = 1;
    public static int DEBUGINFO = 2;
    protected int levels;
    protected Logger nextLevelLogger;

    public void setNextLevelLogger(Logger mNextLevelLogger) {
        this.nextLevelLogger = mNextLevelLogger;

    }

    public void logMessage(int level, String message) {
        if (this.levels <= level) {
            displayLogInfo(message);

        }

        if (nextLevelLogger != null) {
            nextLevelLogger.logMessage(levels, message);
        }
    }


    protected abstract void displayLogInfo(String message);


}
