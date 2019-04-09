package com.ats.node.network.http.client;

import java.io.*;
import java.net.*;
public class HttpClient{

	public void sendGet(URL url, String file) throws Exception {
        try{
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to node : " + url);
            
            if (responseCode == HttpURLConnection.HTTP_OK){
                InputStream inputStream = con.getInputStream();
                FileOutputStream outputStream = new FileOutputStream("src/main/resources/pictures/" + file);
    
                int bytesRead = -1;
                byte[] buffer = new byte[1024];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.close();
                inputStream.close();
    
                System.out.println("File downloaded");
            }else {
                System.out.println("No file to download. Server replied HTTP code: " + responseCode);
            }
            con.disconnect();
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
	}
}