package com.ats.node;

import java.util.HashMap;

public class Message {

    private String messageType;
    private String message;

    public Message() {
        this.messageType = "";
        this.message = "";
    }

    public Message(String messageType, String message) {
        this.messageType = messageType;
        this.message = message;
    }

    public String getMessageType() {
        return this.messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public byte[] toBytes() {
        String packetMessage = getMessageType() + "%" + getMessage();
        return packetMessage.getBytes();
    }

    public HashMap<String, String> getInitResponse() {

        HashMap<String, String> dhtServers = new HashMap<String, String>();
        String[] data = this.message.split("%");
        String[] message = data[1].split(",");

        if (message[0].equals("OK")) {

            for (int i = 0; i < message.length; i++) {
                dhtServers.put(Integer.toString(i), message[i]);
            }
            NodeLogger.logDHTServerDetails(dhtServers);

        } else if (message[0].equals("ERR")) {
            NodeLogger.logMessage("UDPServer error: " + message[1]);
        }

        return dhtServers;
    }

    public boolean getInformAndUpdateResponse() {
        String[] data = this.message.split("%");
        String[] message = data[1].split(",");

        return message[0].equals("OK") ? true : false;
    }

    public boolean getExitResponse() {
        String[] data = this.message.split("%");
        String[] message = data[1].split(",");
        return message[0].equals("OK") ? true : false;
    }

    public String getQueryResponse() {
        String[] data = this.message.split("%");
        String[] message = data[1].split(",");

        if (message[0].equals("OK")) {
            return message[1];
        } else {
            return "";
        }
    }
}