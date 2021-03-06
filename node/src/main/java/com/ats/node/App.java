package com.ats.node;

import com.ats.node.models.Peer;
import com.ats.node.network.http.server.*;

import java.util.HashMap;
import java.util.Scanner;

public class App {

    final static String SETTINGS_FILENAME = "settings.json";
    final static String PICTURES_DIRECTORY = "/classes/pictures";

    public static void main(String[] args) {

        // init HTTPServer in separete thread.
        Thread httpServerThread = new Thread(new HTTPServerManager());
        httpServerThread.start();

        // acquire master's server pool ip, udp port
        // Settings settings = new Settings(SETTINGS_FILENAME);
        // String serverIP = settings.getServerPoolIp();
        // int serverPort = settings.getUDPPort();
        Peer masterPeer = new Peer("ENG201-19", 20410); // DHT master peer

        NodeCLI nodeCLI = new NodeCLI(masterPeer, PICTURES_DIRECTORY);

        // init: ask for all dht servers info
        HashMap<String, String> dhtServers = nodeCLI.init();
        NodeLogger.logDHTServerDetails(dhtServers);

        // inform and update: add node's own pictures to network
        nodeCLI.informAndUpdate(PICTURES_DIRECTORY);

        Scanner choose = new Scanner(System.in);
        int userInput = -1;

        while (userInput != 3) {
            nodeCLI.printMenu();

            if (!choose.hasNextInt()) {
                nodeCLI.printInvalidInputMsg();
                choose.next();
                continue;
            }

            userInput = choose.nextInt();
            System.out.println("=====================================================================");

            switch (userInput) {
            case 1:
                String filename = nodeCLI.getUserFilenameInput();
                nodeCLI.query(filename);
                userInput = -1;
                break;
            case 2:
                nodeCLI.informAndUpdate(PICTURES_DIRECTORY);
                userInput = -1;
                break;
            case 3:
                nodeCLI.exit();
                choose.close();
                System.exit(0);
                break;
            default:
                System.out.println("Invalid command inserted");
                userInput = -1;
                break;
            }
        }
    }
}