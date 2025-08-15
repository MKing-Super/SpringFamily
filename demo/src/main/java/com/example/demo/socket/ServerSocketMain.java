package com.example.demo.socket;


import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

// 服务端
public class ServerSocketMain {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            Socket clientSocket = serverSocket.accept();
            InputStream inputStream = clientSocket.getInputStream();
            try (Scanner scanner = new Scanner(inputStream)) {
                while (scanner.hasNextLine()) {
                    // Scanner.nextLine()需换行符
                    String line = scanner.nextLine();
                    System.out.println(line);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
