package com.ats.node;

public class App {

    final static String SETTINGS_FILENAME = "settings.json";

    public static void main(String[] args) {

        // acquire server pool ip, udp port num
        Settings settings = new Settings(SETTINGS_FILENAME);

        // init: ask for ip all dht servers, store them
        // inform and update: add node's own pictures to network
        // display user possible commands
        System.out.println(settings.getServerPoolId());
        System.out.println(settings.getUDPPort());
        System.out.println("Hello World!");
    }

}