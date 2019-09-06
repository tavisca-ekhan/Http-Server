package com.tavisca.ebran.HttpServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread implements Runnable {
    private static boolean isAcceptingMoreClients = true;
    private Server server;

    public ServerThread(Socket client) {
        server = new Server(client);
    }

    @Override
    public void run() {
        try {
            server.handleClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(8080);
            System.out.println("ServerThread is listening for Client");
            ServerThread.stopServer();
            while (isAcceptingMoreClients) {
                Socket socket = server.accept();
                System.out.println("Client joins the ServerThread");
                new Thread(new ServerThread(socket)).start();
            }

            server.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static boolean stopServer() {
        return isAcceptingMoreClients = false;
    }
}
