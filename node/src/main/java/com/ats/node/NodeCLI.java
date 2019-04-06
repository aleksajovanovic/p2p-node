package com.ats.node;

import com.ats.node.models.Peer;
import java.util.Scanner;
import java.util.List;

public class NodeCLI {

    private Peer masterPeer;
    private NodeLogger logger;
    private String directory;

    public NodeCLI(Peer peer, String directory) {
        this.masterPeer = peer;
        this.logger = new NodeLogger();
    }

    public void init() {
        logger.logMessage("Creating init packet...");
        Message initMsg = new Message("init", "");
        // UDPClient udpClient = new UDPClient(masterPeer);
        // udpClient.sendPacket(initMsg);
        // message format not defined yet
        // Message responseMsg = udpClient.receive();
    }

    public void informAndUpdate(String directory) {
        // inform and update: add node's own pictures to network
        FileManager fileManager = new FileManager(directory);
        List<String> filenames = fileManager.getFilenames();

        for (String file : filenames) {
            logger.logMessage("Creating informAndUpdate packet for " + file + "...");
            // single thread
            // generate and obtain hash here
            Message informMsg = new Message("informAndUpdate", file);
            // UDPClient tempUDPCLient = new UDPClient(this.masterPeer);
            // tempUDPCLient.sendPacket(informMsg);
            // Message tempResponseMsg = tempUDPCLient.receive();
        }
    }

    public void exit() {
        logger.logMessage("Creating exit packet ...");
        Message exitMsg = new Message("exit", "");
        // UDPClient udpClient = new UDPClient(masterPeer);
        // udpClient.sendPacket(exitMsg);
        // message format not defined yet
        // Message responseMsg = udpClient.receive();
    }

    public void query(String filename) {
        logger.logMessage("Creating query packet ...");
        // need to hash
        Message exitMsg = new Message("query", "");
        // UDPClient udpClient = new UDPClient(masterPeer);
        // udpClient.sendPacket(exitMsg);
        // message format not defined yet
        // Message responseMsg = udpClient.receive();

        // HTTP req, res
        FileManager fileManager = new FileManager(directory);
        if (fileManager.isFileExistent(filename)) {
            logger.logMessage("File " + filename + "downloaded successfully ...");
        } else {
            logger.logMessage("File " + filename + "not downloaded successfully ...");
        }
    }

    public String getUserFilenameInput() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Type the filename to query and press enter!");
        String filename = userInput.next();
        System.out.println("=====================================================================");
        logger.logMessage("Creating query packet ...");
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