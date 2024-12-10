package ex1;

import java.io.*;
import java.net.*;

public class p1 {
    public static void main(String[] args) {
        int port = 12345; // Порт для прослуховування
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущено, очікування підключень...");

            while (true) {
                Socket clientSocket = serverSocket.accept(); // Приймаємо підключення
                System.out.println("Клієнт підключився: " + clientSocket.getInetAddress());

                // Потоки для читання та запису
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Отримано: " + message);
                    out.println("Відповідь від сервера: " + message); // Ехо-відповідь
                }

                clientSocket.close(); // Закриваємо з'єднання після завершення
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


