package ru.effectivemobile;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPEchoServer {

    private final static int PORT = 8080;

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected");

                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}