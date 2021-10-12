package com.vaibhav.backchain.Error;

import com.vaibhav.backchain.Logger;

public class Consolbased extends Logger {


    public Consolbased(int levels) {
        this.levels=levels;
    }

    @Override
    protected void displayLogInfo(String message) {
        System.out.println("CONSOLE LOGGER INFO: " + message);
    }
}
