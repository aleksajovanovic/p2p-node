package com.ats.node;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NodeLogger {

    private static final Logger LOGGER = Logger.getLogger(NodeLogger.class.getName());

    public NodeLogger() {
    }

    public void logServerDetails(String serverIp, int serverPort) {
        LOGGER.log(Level.INFO,
                "Successuflly obtained default Server data from settings file. Default Server IP: {0}. Default Server Port: {1}",
                new Object[] { serverIp, Integer.toString(serverPort), 2 });
    }

    public static void logDHTServerDetails(HashMap<String, String> dhtServer) {

        LOGGER.log(Level.INFO, "DHT Servers details obtained\n");

        for (Entry<String, String> entry : dhtServer.entrySet()) {
            System.out.println("ID: " + entry.getKey() + " , IP: " + entry.getValue());
        }
    }

    public static void logMessage(String msg) {
        LOGGER.log(Level.INFO, msg);
    }

}