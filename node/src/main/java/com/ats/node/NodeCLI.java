package com.ats.node;

import com.ats.node.models.Peer;
import com.ats.node.network.http.client.HttpClient;
import com.ats.node.network.udp.client.UDPClient;

import java.util.Scanner;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NodeCLI {

    private Peer masterPeer;
    private NodeLogger logger;
    private String directory;

    public NodeCLI(Peer peer, String directory) {
        this.masterPeer = peer;
        this.logger = new NodeLogger();
    }

    public HashMap<String, String> init() {
        NodeLogger.logMessage("Creating init packet...");

        Message initMsg = new Message("init", "");
        UDPClient udpClient = new UDPClient(masterPeer);
        udpClient.sendPacket(initMsg);
        NodeLogger.logMessage("init packet sent...");
        Message responseMsg = udpClient.receive();
        NodeLogger.logMessage("init packet received...");

        return responseMsg.getInitResponse();
    }

    public void informAndUpdate(String directory) {
        // inform and update: add node's own pictures to network
        // FileManager fileManager = new FileManager(directory);
        // List<String> filenames = fileManager.getFilenames();

        List<String> filenames = new ArrayList<String>();
        filenames.add("belgrade.jpg");
        filenames.add("shrek.jpg");

        for (String file : filenames) {
            UDPClient tempUDPCLient = new UDPClient(this.masterPeer);
            NodeLogger.logMessage("Creating informAndUpdate packet for " + file + "...");
            int hash = (Utils.hash(file) % 2) + 1;
            System.out.println("HASH: " + hash);

            Message informMsg = new Message("informAndUpdate", file);
            tempUDPCLient.sendPacket(informMsg);
            NodeLogger.logMessage(" informAndUpdate packet sent for " + file + "...");

            Message tempResponseMsg = tempUDPCLient.receive();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Error between packet sending");
                System.out.println(e.getMessage());
            }
            if (tempResponseMsg.getInformAndUpdateResponse()) {
                NodeLogger.logMessage("Successfully added " + file + " to Server Pool");
            } else {
                NodeLogger.logMessage("Failed to add " + file + " to Server Pool");
            }
        }
    }

    public void exit() {
        NodeLogger.logMessage("Creating exit packet ...");
        Message exitMsg = new Message("exit", "");
        UDPClient udpClient = new UDPClient(masterPeer);
        udpClient.sendPacket(exitMsg);
        NodeLogger.logMessage("exit packet sent ...");
        Message responseMsg = udpClient.receive();
        NodeLogger.logMessage("exit packet reponse received ...");
        if (!responseMsg.getInformAndUpdateResponse()) {
            NodeLogger.logMessage("Failed to inform server pool that peer will leave network");
        }
        NodeLogger.logMessage("Server pool removed peer from network");
    }

    public void query(String filename) {
        NodeLogger.logMessage("Creating query packet ...");
        // need to hash
        Message exitMsg = new Message("query", filename);
        UDPClient udpClient = new UDPClient(masterPeer);
        NodeLogger.logMessage("Sending query packet ...");
        udpClient.sendPacket(exitMsg);
        Message responseMsg = udpClient.receive();
        NodeLogger.logMessage("query packet received ...");

        String res = responseMsg.getQueryResponse();
        if (res.contains("ERR")) {
            NodeLogger.logMessage("File Not Found in Directory Servers");
        } else {
            NodeLogger.logMessage("File found at peer with ip: " + res);
            try {

                String httpServerIP = res.substring(1);

                // HTTP GET request
                HttpClient http = new HttpClient();
                System.out.println("Send Http GET request");
                String test = ("http://" + httpServerIP + ":" + "8000" + "/?param1=" + filename);

                http.sendGet(test, filename);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        // FileManager fileManager = new FileManager(directory);
        // if (fileManager.isFileExistent(filename)) {
        // NodeLogger.logMessage("File " + filename + "downloaded successfully ...");
        // } else {
        // NodeLogger.logMessage("File " + filename + "not downloaded successfully
        // ...");
        // }
    }

    public String getUserFilenameInput() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Type the filename to query and press enter!");
        String filename = userInput.next();
        System.out.println("=====================================================================");
        return filename;
    }

    public void printMenu() {
        System.out.println("=====================================================================");
        System.out.println("Type command and press enter.");
        System.out.println("1 - query");
        System.out.println("2 - informAndUpdate");
        System.out.println("3 - exit");
        System.out.print("Command: ");

    }

    public void printInvalidInputMsg() {
        System.out.println("Invalid input. Inser a command number!");
        System.out.println("=====================================================================");
    }
}