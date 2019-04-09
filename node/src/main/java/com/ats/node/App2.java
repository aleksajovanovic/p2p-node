package com.ats.node;

import java.net.*;

import com.ats.node.network.http.server.ServerHandler;
import com.sun.net.httpserver.HttpServer;

public class App2 {

    public static void main(String[] args) throws Exception {
        
        int port = 8000;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new ServerHandler());
        server.setExecutor(null); // creates a default executor
        server.start();

    }
}