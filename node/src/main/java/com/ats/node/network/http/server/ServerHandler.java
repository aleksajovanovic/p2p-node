package com.ats.node.network.http.server;

import com.ats.node.NodeLogger;
import com.sun.net.httpserver.*;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;


public class ServerHandler implements HttpHandler, Runnable {
    public void handle(HttpExchange t) throws IOException {
        try {
            Map<String, String> parms = ServerHandler.queryToMap(t.getRequestURI().getQuery());
            String path = "src/main/resources/pictures/" + parms.get("param1");
            File f = new File(path);
            NodeLogger.logMessage("HTTP SERVER handle: getting file");

            if (f.exists()) {
                NodeLogger.logMessage("File Exists:Sending File");
                String response = "Sending File";

                byte[] bs = response.getBytes();
                t.sendResponseHeaders(200, 0);
                OutputStream os = t.getResponseBody();
                Files.copy(f.toPath(), os);
                os.close();
            } else {
                NodeLogger.logMessage("File Does not Exist:Sending Error");
                String response = "File Does Not Exist";

                byte[] bs = response.getBytes("UTF-8");
                t.sendResponseHeaders(404, bs.length);
                OutputStream os = t.getResponseBody();
                os.write(bs);
                os.close();
            }

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    // puts query parameter into map
    public static Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap<String, String>();
        for (String param : query.split("&")) {
            String pair[] = param.split("=");
            if (pair.length > 1) {
                result.put(pair[0], pair[1]);
            } else {
                result.put(pair[0], "");
            }
        }
        return result;
    }

    @Override
    public void run() {

    }
}