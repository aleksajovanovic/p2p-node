package com.ats.node;

import java.net.*;

import com.ats.node.network.http.server.ServerHandler;
import com.sun.net.httpserver.HttpServer;

public class App2 {

    public static void main(String[] args) throws Exception {
        //HttpConnection http = new HttpConnection();

		//System.out.println("Testing 1 - Send Http GET request");
        //http.sendGet();
        

        //Thread httpServerThread = new Thread(new MyHandler());
        //httpServerThread.start();

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/", new ServerHandler());
        server.setExecutor(null); // creates a default executor
        server.start();


    }
}