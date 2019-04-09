package com.ats.node;

import com.ats.node.models.Peer;
import com.ats.node.network.http.client.HttpClient;
import com.ats.node.network.udp.client.UDPClient;

import java.util.Scanner;
import java.net.URL;
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
        FileManager fileManager = new FileManager(directory);
        List<String> filenames = fileManager.getFilenames();
        UDPClient tempUDPCLient = new UDPClient(this.masterPeer);

        for (String file : filenames) {

            if (file.equals("belgrade.jpg")) {

                NodeLogger.logMessage("Creating informAndUpdate packet for " + file + "...");

                int hash = (Utils.hash(file) % 2) + 1;
                System.out.println("HASH: " + hash);

                Message informMsg = new Message("informAndUpdate", file);
                tempUDPCLient.sendPacket(informMsg);
                Message tempResponseMsg = tempUDPCLient.receive();
                if (tempResponseMsg.getInformAndUpdateResponse()) {
                    NodeLogger.logMessage("Successfully added" + file + "to Server Pool");
                } else {
                    NodeLogger.logMessage("Failed to add " + file + "to Server Pool");
                }
            }
        }
    }

    public void exit() {
        NodeLogger.logMessage("Creating exit packet ...");
        Message exitMsg = new Message("exit", "");
        UDPClient udpClient = new UDPClient(masterPeer);
        udpClient.sendPacket(exitMsg);
        Message responseMsg = udpClient.receive();

        if (!responseMsg.getInformAndUpdateResponse()) {
            NodeLogger.logMessage("Failed to inform server pool that peer will leave network");
        }
        NodeLogger.logMessage("Server pool removed peer from network");
    }

    public void query(String filename) {
        NodeLogger.logMessage("Creating query packet ...");
        // need to hash
        Message exitMsg = new Message("query", "");
        UDPClient udpClient = new UDPClient(masterPeer);
        udpClient.sendPacket(exitMsg);
        Message responseMsg = udpClient.receive();

        String res = responseMsg.getQueryResponse();
        if (!res.equals("")) {
            NodeLogger.logMessage("File not found in any of the peers");
        } else {
            NodeLogger.logMessage("File found at peer with ip: " + res);
            try {
                HttpClient http = new HttpClient();
                System.out.println("Testing 1 - Send Http GET request");
                URL url = new URL("http://" + res + ":" + "20410" + "/?param1=" + filename);

                http.sendGet(url, filename);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        // Here execute HTTP Client GET request

        FileManager fileManager = new FileManager(directory);
        if (fileManager.isFileExistent(filename)) {
            NodeLogger.logMessage("File " + filename + "downloaded successfully ...");
        } else {
            NodeLogger.logMessage("File " + filename + "not downloaded successfully ...");
        }
    }

    public String getUserFilenameInput() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Type the filename to query and press enter!");
        String filename = userInput.next();
        System.out.println("=====================================================================");
        NodeLogger.logMessage("Creating query packet ...");
        return filename;
    }

    public void printMenu() {
        System.out.println("=====================================================================");
        System.out.println("Type command and press enter to see. To end the program type \"end\".");
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