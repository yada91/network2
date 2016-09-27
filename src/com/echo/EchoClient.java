package com.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
	private static final String SERVER_IP = "192.168.1.10";
	private static final int SERVER_PORT = 5000;

	public static void main(String[] args) {

		// TODO Auto-generated method stub

		Socket socket = null;

		try {
			// 1
			socket = new Socket();

			// 2
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
			System.out.println("[client] connected");

			// 3
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);

			Scanner sc = new Scanner(System.in);
			String data;

			while (true) {
				System.out.print(">>");
				data = sc.nextLine();
				if (data.equals("exit")) {
					break;
				}
				pw.println(data);

				String result = br.readLine();
				System.out.println("<<" + result);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (socket != null && socket.isClosed() == false) {
					socket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
