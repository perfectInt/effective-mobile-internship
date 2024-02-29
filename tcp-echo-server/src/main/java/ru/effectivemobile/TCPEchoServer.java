package ru.effectivemobile;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPEchoServer {

    private final static int PORT = 8080;

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(PORT);
             ExecutorService executorService = Executors.newFixedThreadPool(10)) {
            System.out.println("Server is listening on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected");

                executorService.execute(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}