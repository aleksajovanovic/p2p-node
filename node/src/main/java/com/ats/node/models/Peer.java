package com.ats.node.models;

import java.net.*;

public class Peer {
    private int id;
    private String ip;
    private int port;

    public Peer(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public Peer(int id, String ip, int port) {
        this.id = id;
        this.ip = ip;
        this.port = port;
    }

    public int getId() {
        return this.id;
    }

    public String getIp() {
        return this.ip;
    }

    public InetAddress getIpInetAddress() {
        try {
            InetAddress ip = InetAddress.getByName(this.ip);

        } catch (UnknownHostException e) {
            System.out.println("Error getting InetAddress");
            System.out.println(e.getMessage());
        }

        return null;
    }

    public int getPort() {
        return this.port;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

}