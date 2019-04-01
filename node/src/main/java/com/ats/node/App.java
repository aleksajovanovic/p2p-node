package com.ats.node;

import java.net.*;
import com.ats.node.network.udp.client.UDPClient;
import com.ats.node.models.Peer;

public class App {

    final static String SETTINGS_FILENAME = "settings.json";

    public static void main(String[] args) {

        // acquire master's server pool ip, udp port
        Settings settings = new Settings(SETTINGS_FILENAME);
        String serverIP = settings.getServerPoolIp();
        int serverPort = settings.getUDPPort();

        // init: ask for all dht servers info
        Peer masterPeer = new Peer(serverIP, serverPort); // DHT master peer
        Message requestMsg = new Message("init", "");
        UDPClient udpClient = new UDPClient(masterPeer);
        udpClient.sendPacket(requestMsg);

        // message format not defined yet
        Message responseMsg = udpClient.receive();

        // inform and update: add node's own pictures to network
        // display user possible commands

    }

}