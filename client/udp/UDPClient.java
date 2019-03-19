import java.io.*;
import java.net.*;

class UDPClient {
    private InetAddress serverIP;
    private int serverPortNumber;

    byte[] byteDataRequest;
    byte[] byteDataResponse;

    DatagramPacket requestPacket;
    DatagramPacket responsePacket;
    DatagramSocket clientSocket;

    // constructor
    UDPClient(InetAddress serverIP, int serverPortNumber) {
        this.serverIP = serverIP;
        this.serverPortNumber = serverPortNumber;
        this.byteDataRequest = new byte[1024];
        this.byteDataResponse = new byte[1024];
        this.clientSocket = null;
    }

    /**
     * Sends a UDP packet to the server.
     * Message contains type of message and message
     * Type of message verified in server
     */
    public void sendUDPPacket(Message msg) {

        byteDataRequest = msg.toBytes();
        this.requestPacket = new DatagramPacket(byteDataRequest, byteDataRequest.length, this.serverIP, this.serverPortNumber);
        this.responsePacket = new DatagramPacket(byteDataResponse, byteDataResponse.length);
        this.clientSocket = null;

        try{
            this.clientSocket = new DatagramSocket();
            this.clientSocket.send(requestPacket);
            receivePacket();
            
        } catch(SocketException e) {
            System.out.println("Error receiving packet response from server");
            System.out.println(e.getMessage());
        } catch(IOException e) {
            System.out.println("Error reading received data from server");
            System.out.println(e.getMessage());
        } finally {
            if(clientSocket != null) {
                clientSocket.close();
            }
        }
    }

    /** 
     * Waits for server packet, select operation according to the type 
     * of message sent by server (should be the same as client message type)
     */
    public void receivePacket() {
        try{
            this.clientSocket.receive(responsePacket);
            Message msgReceived = 
                processMessageReceived(new String(responsePacket.getData()));
            
            switch (msgReceived.getMessageType()) {
                case "init":
                    init(msgReceived.getMessage());
                    break;
                case "informAndUpdate":
                    informAndUpdate(msgReceived.getMessage());
                    break;
                case "query":
                    query(msgReceived.getMessage());
                    break;
                case "exit":
                    exit(msgReceived.getMessage());
                    break;
                default:
                    System.out.println("Type of server response not recognized: "  + msgReceived.getMessageType());
                    break;
            }
            
        } catch(IOException e) {
            System.out.println("Error reading received data from server");
            System.out.println(e.getMessage());
        }
    }

    private void init(String dataReceived) {
        System.out.println("FROM SERVER:" + dataReceived);
    }

    private void informAndUpdate(String dataReceived) {
        System.out.println("FROM SERVER:" + dataReceived);
    }

    private void query(String dataReceived) {
        System.out.println("FROM SERVER:" + dataReceived);

    }

    private void exit(String dataReceived) {
        System.out.println("FROM SERVER:" + dataReceived);
    }

    public Message processMessageReceived(String dataReceived) {
        String[] data = dataReceived.split("\n");
        Message msgReceived = new Message(data[0], data[1]);
        return msgReceived;
    }
}
