package application;

import java.io.File;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;

public class ChatController {
	private Client client;
	
	@FXML
	Button btnSend;
	
	@FXML
	TextField txtSend;
	
	@FXML
	TextArea txtLog;
	
	@FXML
	VBox window;
	
	@FXML
	MenuItem txtPurple;
	
	@FXML
	MenuItem txtRed;
	
	@FXML
	MenuItem txtGreen;
	
	@FXML
	MenuItem txtOrange;
	
	@FXML
	MenuItem txtBlack;
	
	@FXML
	MenuItem txtBlue;
	
	@FXML
	MenuItem logPurple;
	
	@FXML
	MenuItem logRed;
	
	@FXML
	MenuItem logGreen;
	
	@FXML
	MenuItem logOrange;
	
	@FXML
	MenuItem logBlack;
	
	@FXML
	MenuItem logBlue;
	
	@FXML
	MenuItem bckPurple;
	
	@FXML
	MenuItem bckRed;
	
	@FXML
	MenuItem bckGreen;
	
	@FXML
	MenuItem bckWhite;
	
	@FXML
	MenuItem bckOrange;
	
	@FXML
	MenuItem bckBlack;
	
	@FXML
	MenuItem bckBlue;
	
	@FXML
	MenuItem fontArial;
	
	@FXML 
	MenuItem fontTimes;
	
	@FXML
	MenuItem fontApex;
	
	@FXML
	MenuItem fontCalibri;
	
	@FXML
	MenuItem aboutChat;
	
	@FXML
	MenuItem chatHelp;
	
	@FXML
	MenuItem fontDown;
	
	@FXML
	MenuItem fontUp;
	
	@FXML
	MenuItem menuQuit;
	
	@FXML
	MenuItem menuSend;
	
	String currentFont;
	double currentSize;
	String send;
	String alert;
	Media sound1;
	Media sound2;
	MediaPlayer player;
	
	public ChatController(){
		client = new Client("localhost", 56788);
		currentFont = "Apex Book New";
		currentSize = 14;
		send = "messageSound.mp3"; 
		alert = "error.mp3";
		sound1 = new Media(new File(send).toURI().toString());
		sound2 = new Media(new File(alert).toURI().toString());
	}
	
	public void sendMessage(){
		String message = txtSend.getText();
		if(message.equals("")||message==null){
			return;
		}
		try{
			addClientMessage(message);
			txtSend.clear();
			client.sendMessage(message);
			message = client.receiveMessage();
			if(message==null){
				return;
			}
			addServerMessage(message);
			player = new MediaPlayer(sound1);
			player.play();
			message=message.toLowerCase();
			if(message.contains("goodbye")){
				player = new MediaPlayer(sound2);
				player.play();
				txtLog.appendText("Chatbot has disconnected!"+System.lineSeparator());
			}
		} catch(NullPointerException ex){
			player = new MediaPlayer(sound2);
			player.play();
			txtLog.appendText("Not connected to ChatBot. Start Server program and relaunch."+System.lineSeparator());
			
		}
		
	}
	
	public void fontUp(){
		txtLog.setFont(Font.font(currentFont, currentSize+1));
	}
	
	public void fontDown(){
		txtLog.setFont(Font.font(currentFont, currentSize-1));
	}
	
	public void setFont(ActionEvent event){
		currentFont = ((MenuItem) event.getSource()).getText();
		txtLog.setFont(Font.font(currentFont,currentSize));
		txtSend.setFont(Font.font(currentFont,currentSize));
	}

	public void setTextColor(ActionEvent event){
		String color = ((MenuItem) event.getSource()).getText();
		color = color.toUpperCase();
		txtSend.setStyle("-fx-text-inner-color: "+color+";");
	}
	
	public void setLogColor(ActionEvent event){
		String color = ((MenuItem) event.getSource()).getText();
		txtLog.setStyle("-fx-text-inner-color: "+color+";");
	}
	
	public void setBackgroundColor(ActionEvent event){
		String color = ((MenuItem) event.getSource()).getText();
		window.setStyle("-fx-background-color: "+color+";");
	}
	
	public void quit(ActionEvent event){
		try{
			client.endChat();
			Platform.exit();
			System.exit(0);
		}catch(NullPointerException e){
			Platform.exit();
			System.exit(0);
		}
	}

	public void addClientMessage(String msg){
		txtLog.appendText("Me: " + msg + System.lineSeparator());
	}

	public void addServerMessage(String msg){
		txtLog.appendText("ChatBot: "+ msg + System.lineSeparator());
	}
	
	public void commandHelp(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Accepted Commands");
		alert.setHeaderText("ChatBot can help you with the following:");
		alert.setContentText("-Send any message to start your chat.\n-Date\n-Time\n-Wentworth hours (ask for a specific office/service)\n-Send 'bye' to end your chat.");
		alert.showAndWait();
	}
	
	public void aboutChat(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("About Chat With Me");
		alert.setHeaderText("Chat With Me");
		alert.setContentText("Created by Jacob Marra and Kristin Johnson\nNetwork Programming\nCOMP2100-09 Fall 2016");
		alert.showAndWait();
	}
	
}
