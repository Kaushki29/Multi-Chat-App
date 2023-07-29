package com.brainmentors.chatapp.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.brainmentors.chatapp.utils.configReader;

public class Server {
	ServerSocket serverSocket;
	ArrayList<ServerWorker> workers=new ArrayList<>();   //contains all the client sockets
	public Server() throws IOException
	{
		int PORT=Integer.parseInt(configReader.getValue("PORTNO"));
		serverSocket=new ServerSocket(PORT);
		System.out.println("Server starts and waiting for the Clients..");
		HandleClientRequest();
	}
	//Multiple Client Handshaking
	public void HandleClientRequest() throws IOException
	{
		//to add multiple clients
		// thread is between boss and the client(connects them)(server and multiple users)
		while(true) {
			Socket clientSocket=serverSocket.accept();  //Handshaking
			//Per Client per thread
			ServerWorker serverWorker=new ServerWorker(clientSocket,this); //Creating a new worker/thread
			workers.add(serverWorker);
			serverWorker.start();
			}
	}
	//for Single User
	/*public Server() throws IOException
	{
		//till 1024 port nos are reserved so we have to allocate after that
		//after 1024 also some port nos are reserved so we cannot allocate these also
		int PORT=Integer.parseInt(configReader.getValue("PORTNO"));
		//configReader is used to read configprop file in which port no is written and this will return string
		//so converted to integer
		serverSocket=new ServerSocket(PORT);
		System.out.println("Server started and waiting for the client connection..");
		//After the client comes handshaking is done
		Socket socket=serverSocket.accept();  //Handshaking
		System.out.println("Client joins the server..");
		//Recieving msg to the server
		InputStream in=socket.getInputStream();
		byte arr[]=in.readAllBytes();   //data is received in bytes form so stored in an array
		String str=new String(arr);     //array converted to string to show the msg
		System.out.println("Message Recieved from the Client "+str);
		in.close();
		socket.close();
	}*/
	public static void main(String[] args) throws IOException {
		Server server=new Server();
	}

}
