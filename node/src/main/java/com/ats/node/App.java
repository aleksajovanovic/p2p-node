package com.ats.node;

import java.net.*;
import com.ats.node.network.udp.client.UDPClient;
import com.ats.node.models.Peer;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    final static String SETTINGS_FILENAME = "settings.json";
    final static String PICTURES_DIRECTORY = "/pictures";

    public static void main(String[] args) {

        NodeLogger logger = new NodeLogger();

        // acquire master's server pool ip, udp port
        Settings settings = new Settings(SETTINGS_FILENAME);
        String serverIP = settings.getServerPoolIp();
        int serverPort = settings.getUDPPort();
        // log: successully acquired master ip
        System.out.println(serverPort);
        logger.logServerDetails(serverIP, serverPort);

        // init: ask for all dht servers info
        logger.logMessage("creating \"Init\" request");

        Peer masterPeer = new Peer(serverIP, serverPort); // DHT master peer
        Message requestMsg = new Message("init", "");
        UDPClient udpClient = new UDPClient(masterPeer);
        // udpClient.sendPacket(requestMsg);
        logger.logMessage("request \"Init\" sent");

        // message format not defined yet
        logger.logMessage("waiting for  \"Init\" response");
        // Message responseMsg = udpClient.receive();
        // log: "init" received
        logger.logMessage("\"Init\" response received");

        // inform and update: add node's own pictures to network
        // get pictures from resources folder
        FileManager fileManager = new FileManager(PICTURES_DIRECTORY);
        List<String> filenames = fileManager.getFilenames();
        // create and send a packet for each picture
        logger.logMessage("Filenames successfully obtained from resources");
        for (String file : filenames) {
            // create packet and send message type "informAndUpdate"
            logger.logMessage("File: " + file);
        }
        // display user possible commands

    }
}