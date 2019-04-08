package com.ats.node.network.http;

import com.ats.node.NodeLogger;
import com.sun.net.httpserver.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class MyHandler implements HttpHandler, Runnable {
    public void handle(HttpExchange t) throws IOException {
        Map <String,String>parms = MyHandler.queryToMap(t.getRequestURI().getQuery());
        String path = "src/main/resources/pictures/" + parms.get("param1");
        File f = new File(path);

        NodeLogger.logMessage("File Exists");
        String response = "Request recieved";

        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        PrintWriter pw = new PrintWriter(os, true);
        pw.println(os);
        os.close();
    }
    //puts query parameter into map
    public static Map<String, String> queryToMap(String query){
        Map<String, String> result = new HashMap<String, String>();
        for (String param : query.split("&")) {
            String pair[] = param.split("=");
            if (pair.length>1) {
                result.put(pair[0], pair[1]);
            }else{
                result.put(pair[0], "");
            }
        }
        return result;
      }

    @Override
    public void run() {

    }
}
