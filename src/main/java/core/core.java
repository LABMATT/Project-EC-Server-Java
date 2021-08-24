package core;

import org.java_websocket.server.WebSocketServer;
import socketManger.SocketManger;
import sqlManger.InitDB;

import java.net.InetSocketAddress;

public class core {
    public static void main(String[] args) {
        System.out.println("LABMATT EDIT COLLABORATE SERVER JAVA ADDITON");
        System.out.println("Created by Matthew Lewington (LABMATT)");

        new InitDB().check();

        String host = "localhost";
        int port = 8080;

        WebSocketServer server = new SocketManger(new InetSocketAddress(host, port));
        server.run();
    }
}
