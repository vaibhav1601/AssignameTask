package com.vaibhav.backchain.Error;

import com.vaibhav.backchain.Logger;

public class DebugBased extends Logger {

    public DebugBased(int levels) {

        this.levels=levels;
    }

    @Override
    protected void displayLogInfo(String message) {

    }
}
