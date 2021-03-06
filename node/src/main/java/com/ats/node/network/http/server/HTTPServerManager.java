package com.ats.node.network.http.server;

import java.net.*;
import com.ats.node.NodeLogger;
import com.ats.node.network.http.server.ServerHandler;
import com.sun.net.httpserver.HttpServer;

public class HTTPServerManager implements Runnable {

    public HTTPServerManager() {
    }

    public void run() {
        try {

            NodeLogger.logMessage("HTTServerManager running in separate thread: " + Thread.currentThread().getId());
            int port = 8000;
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
            server.createContext("/", new ServerHandler());
            server.setExecutor(null); // creates a default executor
            server.start();
            

        } catch (Exception e) {
            System.out.println("Error ocurred in HTTPServerManager: ");
            System.out.println(e.getMessage());
        }
    }
}