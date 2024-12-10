package ex1;

import java.io.*;
import java.net.*;

public class p2 {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        int port = 12345;

        try (Socket socket = new Socket(serverAddress, port);
             BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("Підключено до сервера. Введіть повідомлення (введіть 'exit' для виходу):");

            String userInput;
            while ((userInput = consoleInput.readLine()) != null) {
                if ("exit".equalsIgnoreCase(userInput)) {
                    break;
                }
                out.println(userInput);
                System.out.println("Відповідь від сервера: " + in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

