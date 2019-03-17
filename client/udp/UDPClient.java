import java.io.*;
import java.net.*;

class UDPClient {
    private InetAddress serverIP;
    private int serverPortNumber;

    // constructor
    UDPClient(InetAddress serverIP, int serverPortNumber) {
        this.serverIP = serverIP;
        this.serverPortNumber = serverPortNumber;
    }

    public void sendUDPPacket(String typeRequest, String data) {
        // to bytes
        byte[] byteDataRequest = new byte[1024];
        byte[] byteDataResponse = new byte[1024];
        byteDataRequest = data.getBytes();
        // setup packet config
        DatagramPacket requestPacket = new DatagramPacket(byteDataRequest, byteDataRequest.length, this.serverIP, this.serverPortNumber);
        DatagramPacket responsePacket = new DatagramPacket(byteDataResponse, byteDataResponse.length);
        DatagramSocket clientSocket = null;
        try{
            clientSocket = new DatagramSocket();
            clientSocket.send(requestPacket);  
            clientSocket.receive(responsePacket);
            String modifiedSentence = new String(responsePacket.getData());
            System.out.println("FROM SERVER:" + modifiedSentence);
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

    /** each p2p client knows IP address of directory server (ID=1)
     *  starting with this IP the p2p client needs to ask DHT for
     *  IP addresses of remaining servers and get them.
     */
    private void init() {

    }

    /** p2p client needs to perdorm hashing of content name into server id
     *  contact target server to store the recorde (content name, client IP)
     *  keep the local recorde (content name, DHT server, server' IP)
     */
    private void informAndUpdate () {

    }
    /** requires p2p client to:
     *  - perform the hashing of the content's name into server id
     *  - contact server DHT to find the IP of client with required content name
     *    (after init all IP addresses of servers in DHT are known)
     *  - if content does not exist in the network DHTm return code "404 content not found"
    */
    private void query() {

    }

    /** p2p client request has to be dispersed across all servers in DHT
     *  inform them to remove the records related to the contnet stored 
     *  DHT server chosen as the entry can be any of the 4 and request
     *  as to be passed over the ring to delete all the recorded owned
     *  by the client who wants to exist
     */
    private void exit() {

    }
} 
