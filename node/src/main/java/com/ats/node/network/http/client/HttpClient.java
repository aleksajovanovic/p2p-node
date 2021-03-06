package com.ats.node.network.http.client;

import java.io.*;
import java.net.*;

public class HttpClient {

    public void sendGet(String url, String file) throws Exception {
        try {
            System.out.println("HTTP URL: " + url);
            URL test = new URL("http://localhost:8000/?param1=belgrade.jpg");

            HttpURLConnection con = (HttpURLConnection) (new URL(url)).openConnection();
            System.out.println(con.getResponseMessage());
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to node : " + url);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = con.getInputStream();
                FileOutputStream outputStream = new FileOutputStream("src/main/resources/pictures/" + file);

                int bytesRead = -1;
                byte[] buffer = new byte[3072];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.close();
                inputStream.close();

                System.out.println("File downloaded");
            } else {
                System.out.println("No file to download. Server replied HTTP code: " + responseCode);
            }
            con.disconnect();
        } catch (Exception e) {
            System.out.println("Error sending HTTP GET Request");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}