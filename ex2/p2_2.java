package ex2;

import java.net.*;
import java.util.Scanner;

public class p2_2 {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        int port = 12345;

        try (DatagramSocket socket = new DatagramSocket();
             Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введіть повідомлення для сервера (введіть 'exit' для виходу):");

            while (true) {
                System.out.print("> ");
                String message = scanner.nextLine();

                if ("exit".equalsIgnoreCase(message)) {
                    break;
                }

                DatagramPacket packet = new DatagramPacket(
                        message.getBytes(),
                        message.getBytes().length,
                        InetAddress.getByName(serverAddress),
                        port
                );
                socket.send(packet);

                byte[] buffer = new byte[1024];
                DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
                socket.receive(responsePacket);
                String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
                System.out.println("Відповідь від сервера: " + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

