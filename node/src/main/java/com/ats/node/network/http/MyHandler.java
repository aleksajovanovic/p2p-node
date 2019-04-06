package com.ats.node.network.http;

import com.sun.net.httpserver.*;
import java.io.*;

public class MyHandler implements HttpHandler {
    public void handle(HttpExchange t) throws IOException {
        NodeLogger.logMessage("This is the response;");
        OutputStream os = t.getResponseBody();

        os.close();
    }
}
