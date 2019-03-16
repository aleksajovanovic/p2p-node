import java.io.*;
import java.net.*;

class UDPServer {
    public static void main(String argv[]) throws Exception {

        // create UDP socket server in port 9876
        // this port is the one used in the client
        // still need to figure out how to obtain the ip of the
        // server machine withouth being hostname
        DatagramSocket serverSocket = new DatagramSocket(9876);

        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];

        while (true) {

            // save memory space to store received data
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            // wait for packet from client
            serverSocket.receive(receivePacket);

            // bytes to readable format
            String sentence = new String(receivePacket.getData());

            // on this side server knows IP and PORT right away (no lookup needed)
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            // perform operation requested by client
            String capitalizedSentence = sentence.toUpperCase();

            // once operation done send reply with rwquested data
            sendData = capitalizedSentence.getBytes();

            // create datagram packet to send response to client
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);

        }
    }
}