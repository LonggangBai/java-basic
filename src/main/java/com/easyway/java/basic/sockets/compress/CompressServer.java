package com.easyway.java.basic.sockets.compress;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Logger;


public class CompressServer {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			throw new IllegalArgumentException("Parameter:<Port>");
		}

		int serverPort = Integer.parseInt(args[0]);

		ServerSocket serverSocket = new ServerSocket(serverPort);

		Executor service = Executors.newCachedThreadPool();

		Logger logger = Logger.getLogger("practical");
		logger.info("Server is started!!!!");

		while (true) {
			Socket socket = serverSocket.accept();
			service.execute(new CompressProtocol(logger, socket));
		}

	}

}