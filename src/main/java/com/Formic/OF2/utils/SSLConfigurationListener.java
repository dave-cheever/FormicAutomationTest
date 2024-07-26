package com.Formic.OF2.utils;

import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SSLConfigurationListener implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        System.setProperty("javax.net.ssl.trustStore", "C:Users/dave.cheever/keystores/mykeystore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "Shiever!623519");
    }

    @Override
    public void onFinish(ISuite suite) {
        // Cleanup if necessary
    }
}

