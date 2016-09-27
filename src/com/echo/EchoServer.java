package com.echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ServerSocket serverSocket = null;

		try {
			// 1
			serverSocket = new ServerSocket();
			// 2
			InetAddress inetAddress = InetAddress.getLocalHost();
			String hostAddress = inetAddress.getHostAddress();
			serverSocket.bind(new InetSocketAddress(hostAddress, 5000));
			System.out.println("[서버] 연결기다림");

			// 3
			Socket socket = serverSocket.accept();
			InetSocketAddress inetSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			String remoteHostAddress = inetSocketAddress.getAddress().getHostAddress();
			System.out.println("[서버] 연결됨 from " + remoteHostAddress);

			try {
				// 4
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);

				while (true) {
					// 5
					String data = br.readLine();
					if (data == null) {
						System.out.println("[서버] closed by client");
						break;
					}
					System.out.println("[서버] 데이터 수신:" + data);
					pw.println(data);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (socket != null && socket.isClosed() == false) {
					// 6
					socket.close();
				}
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (serverSocket != null && serverSocket.isClosed() == false) {
					// 7
					serverSocket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
