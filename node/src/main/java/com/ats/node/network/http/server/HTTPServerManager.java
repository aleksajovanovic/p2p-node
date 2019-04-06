package com.ats.node.network.http.server;

import com.ats.node.NodeLogger;

public class HTTPServerManager implements Runnable {

    public HTTPServerManager() {
    }

    public void run() {
        try {

            NodeLogger.logMessage("HTTServerManager running in separate thread: " + Thread.currentThread().getId());
            // http server initialized here
            Thread.sleep(5000);
            NodeLogger
                    .logMessage("HTTServerManager still running in separate thread: " + Thread.currentThread().getId());

        } catch (Exception e) {
            System.out.println("Error ocurred in HTTPServerManager: ");
            System.out.println(e.getMessage());
        }
    }
}