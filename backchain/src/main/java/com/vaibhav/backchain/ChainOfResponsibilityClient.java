package com.vaibhav.backchain;

import com.vaibhav.backchain.Error.Consolbased;
import com.vaibhav.backchain.Error.DebugBased;

public class ChainOfResponsibilityClient {

    private static Logger doChaining() {
        Logger consolLogger = new Consolbased(Logger.OUTPUTINFO);


        Logger errorLogger = new Consolbased(Logger.ERRORINFO);
        errorLogger.setNextLevelLogger(errorLogger);

        Logger debugLogger = new DebugBased(Logger.DEBUGINFO);
        debugLogger.setNextLevelLogger(debugLogger);

        return consolLogger;


    }

    public static void main(String args[]) {
        Logger chainLogger = doChaining();

        chainLogger.logMessage(Logger.OUTPUTINFO, "Enter the sequence of values ");
        chainLogger.logMessage(Logger.ERRORINFO, "An error is occured now");
        chainLogger.logMessage(Logger.DEBUGINFO, "This was the error now debugging is compeled");
    }
}
