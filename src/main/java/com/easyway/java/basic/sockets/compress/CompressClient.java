package com.easyway.java.basic.sockets.compress;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 压缩客户端
 * 
 * @author Suifeng
 * 
 */
public class CompressClient {
	public static final int BUFFER_SIZE = 256;

	public static void main(String[] args) throws IOException {
		if (args.length != 3) {
			throw new IllegalArgumentException(
					"Parameters:<Server> <Port> <File>");
		}

		String server = args[0];
		int port = Integer.parseInt(args[1]);
		String filename = args[2];

		FileInputStream fileIn = new FileInputStream(filename);
		FileOutputStream fileOut = new FileOutputStream(filename + ".gz");

		Socket socket = new Socket(InetAddress.getByName(server), port);

		sendBytes(socket, fileIn);

		InputStream in = socket.getInputStream();

		int bytesRead;
		byte[] buffer = new byte[BUFFER_SIZE];

		while ((bytesRead = in.read(buffer)) != -1) {
			fileOut.write(buffer, 0, bytesRead);
			System.out.print("R");
		}

		System.out.println("");

		socket.close();

		fileIn.close();
		fileOut.close();
	}

	public static void sendBytes(Socket socket, FileInputStream fileIn)
			throws IOException {
		OutputStream out = socket.getOutputStream();

		int bytesRead;
		byte[] buffer = new byte[BUFFER_SIZE];

		while ((bytesRead = fileIn.read(buffer)) != -1) {
			out.write(buffer, 0, bytesRead);
			System.out.print("W");
		}

		// 单独关闭输出，通知服务器端输入已完毕
		socket.shutdownOutput();
	}

}
