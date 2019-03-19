import java.io.*;
import java.net.*;

class UDPClientTest {
    public static void main(String argv[]) throws Exception {

        String[] typeMessages = {"init", "informAndUpdate", "query", "exit"};

        // env variables
        InetAddress IPAddress = InetAddress.getLocalHost();
        int portNumber = 9876;

        for(int i = 0; i < typeMessages.length; i++) {
            Message udpMessage = new Message("udp", typeMessages[i], "theMessage");
            UDPClient udpClient = new UDPClient(IPAddress,portNumber);
            udpClient.sendUDPPacket(udpMessage);
        }      
    }
}
