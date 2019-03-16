import java.io.*;
import java.net.*;

class UDPClient {
    public static void main(String argv[]) throws Exception {

        // udp client socket
        DatagramSocket clientSocket = new DatagramSocket();
        // get udp client ip
        InetAddress IPAddress = InetAddress.getByName("hostname");

        // data to be trasnmitted
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        // input from user
        String sentence = inFromUser.readLine();
        // readable data to bytes
        sendData = sentence.getBytes();

        // IPAddress: Server?
        // Port: Server?
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);

        // send datagram packet to server
        clientSocket.send(sendPacket);

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        // wait for server response
        clientSocket.receive(receivePacket);

        // back to readable data
        String modifiedSentence = new String(receivePacket.getData());

        // display data from server
        System.out.println("FROM SERVER:" + modifiedSentence);
        clientSocket.close();

    }
}
