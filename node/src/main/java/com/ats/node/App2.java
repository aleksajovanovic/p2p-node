package com.ats.node;

import java.io.*;
import java.net.*;

import com.ats.node.network.http.HttpGet;
import com.ats.node.network.http.MyHandler;
import com.sun.net.httpserver.HttpServer;

public class App2 {

    public static void main(String[] args) throws Exception {

        //HttpConnection http = new HttpConnection();

		System.out.println("Testing 1 - Send Http GET request");
		//http.sendGet();

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/test", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();

    }
}