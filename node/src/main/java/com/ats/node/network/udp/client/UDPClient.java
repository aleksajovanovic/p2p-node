package com.ats.node.network.udp.client;

import com.ats.node.models.Peer;
import java.io.*;
import java.net.*;
import com.ats.node.Message;

public class UDPClient {
    private static final int MAX_PACKET_LEN = 65508;
    private DatagramSocket socket;
    private Peer peer;

    public UDPClient(Peer peer) {
        this.peer = peer;

        try {
            this.socket = new DatagramSocket(peer.getPort());
        } catch (SocketException e) {
            System.out.println("Error initalizing DatagramSocket");
            System.out.println(e.getMessage());
        }
    }

    public Message receive() {
        byte[] buffer = new byte[UDPClient.MAX_PACKET_LEN];
        DatagramPacket packet = new DatagramPacket(buffer, UDPClient.MAX_PACKET_LEN);

        try {
            this.socket.receive(packet);
            System.out.println("RECEIVED MESSAGE: " + new String(packet.getData()));
            Message msg = proccessMsg(new String(packet.getData()));
            this.socket.close();
            return msg;
        } catch (IOException e) {
            System.out.println("Error receiving packet from server: ");
            System.out.println(e.getMessage());
        }
        this.socket.close();
        return new Message();
    }

    public void sendPacket(Message msg) {

        byte[] data = msg.toBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, this.peer.getIpInetAddress(),
                this.peer.getPort());

        try {
            this.socket.send(packet);
        } catch (IOException e) {
            System.out.println("Error sending packet to server");
            System.out.println(e.getMessage());
        }
    }

    private Message proccessMsg(String string) {
        String[] data = string.split("%");
        Message msg = new Message(data[0], data[1]);

        return msg;
    }
}