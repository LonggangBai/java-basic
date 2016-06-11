package com.easyway.java.concurrent.practice;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * 结构化并发程序的开发
 * 任务执行
 * 
 * 
 * SingleThreadWebServer
 * <p/>
 * Sequential web server
 *
 * @author Brian Goetz and Tim Peierls
 */
public class SingleThreadWebServer {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            Socket connection = socket.accept();
            handleRequest(connection);
        }
    }

    private static void handleRequest(Socket connection) {
        // request-handling logic here
    }
}
