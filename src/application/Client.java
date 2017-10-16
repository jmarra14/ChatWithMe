package application;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	private Socket soc;
	private PrintStream out;
	private BufferedReader in;
	private boolean initialized=false;

	
	public Client(String IP, int port){
		System.out.printf("IP %s and Port %d%n",IP,port);
		try {
			soc = new Socket(IP, port);
			out = new PrintStream(soc.getOutputStream());
			in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			System.out.println(soc.isConnected());
			initialized = true;
		} catch (UnknownHostException e) {
			System.out.println("Cannot connect to ChatBot!");
		} catch (IOException e) {
			System.out.println("Cannot connect to ChatBot!");
		}
		
	}
	
	public Client(){
		System.out.println("Default constructor called");
	}
	
	public void sendMessage(String msg){
			out.println(msg);
	}
	
	public String receiveMessage(){
		String msg = "";
		try {
			msg = in.readLine();
		} catch (IOException e) {
			System.out.println("Unable to recieve message.");
		}
		return msg;
	}
	
	public void endChat(){
		try {
			soc.close();
		} catch (IOException e) {
			System.out.println("Unable to close socket connection.");
		}
	}
	
	public boolean isConnected(){
		return soc.isConnected();
	}
	
	public boolean isInitialized(){
		return initialized;
	}
}
