package com.ats.node;

import java.net.*;
import com.ats.node.network.http.client.HttpClient;

public class App3 {

    public static void main(String[] args) throws Exception {
        try{
            HttpClient http = new HttpClient();

            System.out.println("Testing 1 - Send Http GET request");
            String file = ("belgrade.jpg");
            URL url = new URL("http://" + "localhost:8000" + "/?param1=" + file);

            
            http.sendGet(url, file);
        }catch(ConnectException e){
            System.out.println(e.getMessage());
        }
    }
}