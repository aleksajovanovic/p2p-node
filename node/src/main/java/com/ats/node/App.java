package com.ats.node;

import java.net.*;
import com.ats.node.network.udp.client.UDPClient;
import com.ats.node.models.Peer;

public class App {

    final static String SETTINGS_FILENAME = "settings.json";

    public static void main(String[] args) {

        // acquire server pool ip, udp port num
        Settings settings = new Settings(SETTINGS_FILENAME);
        InetAddress serverIPAddress = settings.getServerPoolInetAddress();
        String serverIP = settings.getServerPoolIp();
        int serverPort = settings.getUDPPort();

        // init: ask for ip all dht servers, store them

        // DHT master peer
        Peer masterPeer = new Peer(serverIP, serverPort);
        Message udpMessage = new Message("init", "");
        UDPClient udpClient = new UDPClient(masterPeer);

        // inform and update: add node's own pictures to network
        // display user possible commands

    }

}