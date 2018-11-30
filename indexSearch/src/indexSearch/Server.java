package indexSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{
	private final int portNum;
	private final int id;
	
	Server(int pn,int id){
		this.portNum = pn;
		this.id = id;
	}
	
	@Override
	public void run() {
		try 
		{
			ServerSocket serverSocket = new ServerSocket(this.portNum);
			Socket clientSocket = serverSocket.accept();
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				Search s = new Search();
				out.println(s.search(inputLine));
				out.println(inputLine);
			}
		} catch (IOException e) {
			System.out.println(
					"Exception caught when trying to listen on port " + this.portNum + " or listening for a connection");
			System.out.println(e.getMessage());
		}
	}

//	public static void main(String[] args) throws IOException {
//		if (args.length != 1) {
//			System.err.println("Usage: java EchoServer <port number>");
//			System.exit(1);
//		}
//
//		int portNum = Integer.parseInt(args[0]);
//		try 
//		{
//			ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));
//			Socket clientSocket = serverSocket.accept();
//			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
//			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//			String inputLine;
//			while ((inputLine = in.readLine()) != null) {
//				Search s = new Search();
//				out.println(s.search(inputLine));
//				out.println(inputLine);
//			}
//		} catch (IOException e) {
//			System.out.println(
//					"Exception caught when trying to listen on port " + this.portNum + " or listening for a connection");
//			System.out.println(e.getMessage());
//		}
//	}
}
