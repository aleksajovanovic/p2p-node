import java.io.*;
import java.net.*;

class UDPClientTest {
    public static void main(String argv[]) throws Exception {

        // env variables
        InetAddress IPAddress = InetAddress.getLocalHost();
        int portNumber = 9876;
        //
        String testData = "ping server on UDP protocol";
        String typeRequest = "test";

        // initialize client
        UDPClient udpClient = new UDPClient(IPAddress,portNumber);
        udpClient.sendUDPPacket(typeRequest, testData);

        // type_request: init, inform_update, query, exit, ping
    }
}
