package ru.edu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyFirstServer {

  private final int port;

  public MyFirstServer(int port) {
    this.port = port;
  }

  public void start() throws IOException {
    ServerSocket serverSocket = new ServerSocket(port);

    while (true) {
      // accept() - блокирует код, пока не получит запрос от клиента
      Socket socket = serverSocket.accept();
      try {
        processConnection(socket);
      } catch (Exception e) {
        System.out.println("Failed to .processConnectionSocket error=" + e.toString());
        e.printStackTrace();
      } finally {
        socket.close();
      }
    }

  }

  private void processConnection(Socket socket) throws IOException {
    System.out.println("Method .processConnection started");

    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    char[] message = new char[512];
    reader.read(message);

    System.out.println("IN: ");
    System.out.println(message);
    System.out.println();
    // Получаем
//    IN:
//    GET / HTTP/1.1
//    User-Agent: PostmanRuntime/7.29.2
//    Accept: */*
//    Postman-Token: 929cd3f5-814f-4cc0-bbf7-2170bcb0d7a9
//    Host: localhost:9000
//    Accept-Encoding: gzip, deflate, br
//    Connection: keep-alive

    PrintWriter writer = new PrintWriter(socket.getOutputStream());
    writer.write("HTTP/1.1 200 OK\n");
    writer.write("Content-Length: 5\n");
    writer.println();
    writer.write("Hello");
    writer.println();
    writer.flush();

    System.out.println("Method .processConnection finished");

  }

}
