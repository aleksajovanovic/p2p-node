package com.ats.node.network.http;

import com.sun.net.httpserver.*;

import java.io.*;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;

public class NodeServer{

    public static void handleRequest(HttpExchange exchange) throws IOException {
        URI requestURI = exchange.getRequestURI();
        printRequestInfo(exchange);
        String response = "This is the response at " + requestURI;
        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public static void printRequestInfo(HttpExchange exchange) {

        System.out.println("-- HTTP method --");
        String requestMethod = exchange.getRequestMethod();
        System.out.println(requestMethod);

        System.out.println("-- query --");
        URI requestURI = exchange.getRequestURI();
        String query = requestURI.getQuery();
        System.out.println(query);
    }
    public static void initiateServer(HttpExchange exchange) throws IOException {
        final ServerSocket server = new ServerSocket(8080); 
        System.out.println("Listening for connection on port 8080 ...."); 
        while (true) { 
            Socket clientSocket = server.accept(); 
            InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream()); 
            BufferedReader reader = new BufferedReader(isr); 
            String line = reader.readLine(); 
            while (!line.isEmpty()) { 
                System.out.println(line); 
                line = reader.readLine(); 
            }
            server.close();
        }
    }
}