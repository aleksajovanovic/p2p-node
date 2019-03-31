package com.ats.node;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;
import org.apache.commons.io.IOUtils;
import java.net.*;

public class Settings {

    private JSONObject json;

    public Settings(String filename) {

        try {
            this.json = new JSONObject(readJsonFile(filename));

        } catch (IOException e) {
            System.out.println("Error reading JSON file from resources");
            System.out.println(e.getMessage());
        }
    }

    public String getServerPoolIp() {
        return this.json.getJSONObject("serverPool").getString("masterIp");
    }

    public InetAddress getServerPoolInetAddress() {
        try {
            String serverPoolId = this.json.getJSONObject("serverPool").getString("masterIp");
            return InetAddress.getByName(serverPoolId);

        } catch (UnknownHostException e) {
            System.out.println("Error getting InetAddress");
            System.out.println(e.getMessage());
        }

        return null;
    }

    public int getUDPPort() {
        return this.json.getJSONObject("serverPool").getInt("UDPPort");
    }

    private String readJsonFile(String filename) throws IOException {

        ClassLoader classLoader = App.class.getClassLoader();

        try (InputStream is = classLoader.getResourceAsStream(filename)) {
            return IOUtils.toString(is, StandardCharsets.UTF_8);
        }
    }

}