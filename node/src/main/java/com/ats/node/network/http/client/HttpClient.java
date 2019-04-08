package com.ats.node.network.http.client;

import java.io.*;
import java.net.*;
public class HttpClient{

	// HTTP GET request
	public void sendGet(URL url, String file) throws Exception {

		// todo: sendGet should take image name as a parameter
        // image name should be into the end of the link as below
        // Instead of localhost, the request will be sent to an IP, so sendGet also needs to take param of the IP being sent


        try{
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to node : " + url);
            
            if (responseCode == HttpURLConnection.HTTP_OK){
                // opens input stream from the HTTP connection
                InputStream inputStream = con.getInputStream();
                // opens an output stream to save into file
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

