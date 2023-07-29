package com.brainmentors.chatapp.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
//Thread==Worker
//Worker need a job to perform
//for job u give runnable
//Once job is created by runnable so write the job logic inside a run function
//Assign the job to a thread/worker

//thread implements runnable and runnable has run fx so we are getting run indirectly from there

public class ServerWorker extends Thread{
	private Socket clientSocket;
	private InputStream in;
	private OutputStream out;
	private Server server;
	public ServerWorker(Socket clientSocket,Server server) throws IOException
	{
		this.server=server;
		this.clientSocket=clientSocket;
		in=clientSocket.getInputStream(); //client data read
		out=clientSocket.getOutputStream();  //client data write
		System.out.println("New Client Comes..");
	}
	@Override
	public void run()
	{
		//Read data from the client and broadcast to all
		//Earlier in server file data is converted to bytes then stored in array then read(this process is easier)
		BufferedReader br=new BufferedReader(new InputStreamReader(in));  //msg read through inputstream and converted to string by buffered reader
		String line;
		try {
		while(true)
		{
			line=br.readLine(); //read the msgs line by line  //\n is needed to show always that the line is read
			System.out.println("Line read..."+line);
			if(line.equalsIgnoreCase("quit"))
				break;
			//out.write(line.getBytes());   //send data to client
			//Broadcast to all clients
			for(ServerWorker serverWorker:server.workers)
			{
				//\n to also write the data
				line=line+"\n";
				serverWorker.out.write(line.getBytes());  //sent to all
			}
		} 
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally {
			try {
			if(br!=null)
				br.close();
			if(in!=null)
				in.close();
			if(out!=null)
				out.close();
			if(clientSocket!=null)
				clientSocket.close();
			}
		catch(Exception ex)     //This catch is for any error in cleaning the resources
		{
			ex.printStackTrace();
		}
		}
	}
	}
