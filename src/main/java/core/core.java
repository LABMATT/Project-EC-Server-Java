package core;

import socketManger.WsSever;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class core {
    public static void main(String[] args) throws IOException {
        System.out.println("LABMATT EDIT COLLABORATE SERVER JAVA ADDITON");
        System.out.println("Created by Matthew Lewington (LABMATT)");

        WsSever wss = new WsSever();

        wss.runServer();

    }
}
