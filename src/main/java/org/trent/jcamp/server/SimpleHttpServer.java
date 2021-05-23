package org.trent.jcamp.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleHttpServer {

    private static final int port = 8801;

    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 2);
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            Socket socket = serverSocket.accept();
            executorService.submit(() -> {
                try {
                    service(socket);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            });
        }
    }

    private static void service(Socket socket) throws IOException {
        try (PrintWriter printWriter = new PrintWriter(socket.getOutputStream())) {
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-type:text/html;charset=utf-8");
            String body = "hello nio1";
            printWriter.println("Content-Length:" + body.getBytes().length);
            printWriter.println();
            printWriter.write(body);
        }
    }
}
