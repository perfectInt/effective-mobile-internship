package ru.effectivemobile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8080;

        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {

            String userInput;
            System.out.println("Connected to server. Type 'bye' to exit.");

            while ((userInput = consoleReader.readLine()) != null) {
                out.println(userInput);

                if ("bye".equals(userInput)) {
                    socket.close();
                    break;
                }

                String serverResponse = in.readLine();
                System.out.println(serverResponse);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());;
        }
    }
}
