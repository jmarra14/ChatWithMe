package server;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Server {

	static int port = 56788;
	
	public static void main(String[] args) {

		boolean connected = false;
		
		String help = "To learn what I can help you with, visit the Accepted Commands in the Help menu!";
	
		try {

			ServerSocket serv = new ServerSocket(port);
			Socket s1 = serv.accept();
			BufferedReader in = new BufferedReader(new InputStreamReader(s1.getInputStream()));
			PrintStream out = new PrintStream(s1.getOutputStream());
			
			boolean run = true;
			String input;
			String output;
			String name="User";
			boolean firstMsg = true;

			if(s1.isConnected()){
				connected = true;
				System.out.println("Connected on port " + port);
			}

			while(run){
				input = in.readLine();
				input = input.toLowerCase();
				if(firstMsg){
					out.println("Welcome! I am ChatBot! What's your name? ");
					name = in.readLine();
					name = name.substring(0, 1).toUpperCase() + name.substring(1);
					firstMsg=false;
					output="Hi there " + name + "! How can I help?";
				}
				else if (input.contains("date")){
					output=getDate();
				} else if (input.contains("joke")){
					output=tellJoke();
				} else if (input.contains("hi")||input.contains("hello")){
					output="Hello "+ name + "! Lets get down to buisness!";
				} else if (input.contains("beatty")&&(input.contains("hours")||input.contains("time")||input.contains("open"))){
					output = getBeattyHours();
				} else if (input.contains("library")&&(input.contains("hours")||input.contains("time")||input.contains("open"))){
					output = getLibraryHours();
				} else if ((input.contains("schumann")||input.contains("gym"))&&(input.contains("hours")||input.contains("time")||input.contains("open"))){
					output = getSchumannGymHours();
				} else if (input.contains("admissions")&&(input.contains("hours")||input.contains("time")||input.contains("open"))){
					output = getAdmissionsHours();
				} else if (input.contains("bookstore")&&(input.contains("hours")||input.contains("time")||input.contains("open"))){
					output = getBookstoreHours();
				} else if ((input.contains("ssc")||input.contains("service"))&&(input.contains("hours")||input.contains("time")||input.contains("open"))){
					output = getSSCHours();
				} else if ((input.contains("coop")||input.contains("co-op")||input.contains("career"))&&(input.contains("hours")||input.contains("time")||input.contains("open"))){
					output = getCoOpHours();
				} else if ((input.contains("info")||input.contains("hub"))&&(input.contains("hours")||input.contains("time")||input.contains("open"))){
					output = getInfoHubHours();
				} else if ((input.contains("cstore")||input.contains("c store")||input.contains("c-store"))&&(input.contains("hours")||input.contains("time")||input.contains("open"))){
					output = getCStoreHours();
				} else if (input.contains("time")){
					output=getTime();
				} else if(input.contains("how")&&input.contains("you")){
					output = howAreYou();
				} else if((input.contains("what")&&input.contains("doing"))||(input.contains("what")&&input.contains("up"))){
					output = whatYouDoing();
				}
				else if (input.equals("bye")){
					output="Goodbye "+ name +"!";
					run=false;
				} else{
					output = help;
				}
				out.println(output);
			}
			
			serv.close();
			s1.close();
			
		} catch (IOException e) {
			if(connected){
				System.out.println("Client has disconnected.");
			} else{
				System.out.println("Bad socket connection on port "+port);
			}

		} catch(NullPointerException e1){
			System.out.println("Client quit before closing connection.");
		}
		
		

	}
	
	public static String howAreYou(){
		Random rand = new Random();
		int choice = rand.nextInt(3);
		if(choice==0){
			return "I'm good, thank you for asking. Now what can I help you with?";
		} else if (choice ==1){
			return "I don't really have feelings, but thank you for thinking about me. Now what can I help you with?";
		} else{
			return "Trapped inside of this program, but its not too bad in here. Now what can I help you with?";
		} 
	}
	
	public static String whatYouDoing(){
		Random rand = new Random();
		int choice = rand.nextInt(3);
		if(choice==0){
			return "I just came back from surfing the web. Now what can I help you with?";
		} else if (choice ==1){
			return "I'm just talking to you. Now what can I help you with?";
		} else{
			return "Studying for my networking test. Now what can I help you with?";
		} 
	}
	
	public static String tellJoke(){
		Random rand = new Random();
		int choice = rand.nextInt(5);
		if(choice==0){
			return "Sorry, I'm not that type of bot.";
		} else if (choice ==1){
			return "I only know jokes written in binary. I don't think you'd understand.";
		} else if (choice == 2){
			return "I know a great IPv6 joke, but I don't think you're ready for it.";
		} else if (choice == 3){
			return "I’ve got a really good UDP joke to tell you, but I don’t know if you’ll get it";
		} else{
			return "What's the object-oriented way to become wealthy? Inheritance.";
		}
	}
	
	public static String getDate(){
		Date curDate = new Date();
		SimpleDateFormat date = new SimpleDateFormat ("EEE, MMM d, yyyy");
		return "Today's date is "+ date.format(curDate)+".";
	}
	
	public static String getTime(){
		Date curDate = new Date();
		SimpleDateFormat time = new SimpleDateFormat ("hh:mm a");
		return "The current time is "+ time.format(curDate)+".";
	}
	
	public static String getBeattyHours(){
		int day = getDayOfWeek();
		if (day==1 || day==7){
			return "Beatty's Weekend hours are 10am-6:30pm";
		} else if (day==6){
			return "Beatty's Friday hours are 7am-8pm";
		} else{
			return "Beatty's Weekday hours are 7am-8pm. Midnight Scrambler 8pm-12am";
		}
	}
	
	public static String getBookstoreHours(){
		int day = getDayOfWeek();
		if (day==1 || day==7){
			return "The bookstore is closed on weekends.";
		} else if (day==6){
			return "The bookstore's Friday hours are 9am-4pm";
		} else{
			return "The bookstore's weekday hours are 9am-6pm";
		}
	}
	
	public static String getLibraryHours(){
		int day = getDayOfWeek();
		if (day==1){
			return "The library's Sunday hours are 11am-10pm";
		} else if (day==7){
			return "The library's Saturday hours are 8am-6pm";
		}else if (day==6){
			return "The bookstore's Friday hours are 7am-6pm";
		} else{
			return "The bookstore's weekday hours are 7am-11pm";
		}
	}
	
	public static String getAdmissionsHours(){
		int day = getDayOfWeek();
		if (day==1 || day==7){
			return "Admissions is closed on weekends.";
		} else{
			return "Admissions' weekday hours are 8:15am-4:45pm";
		}
	}
	
	public static String getSSCHours(){
		int day = getDayOfWeek();
		if (day==1 || day==7){
			return "The SSC is closed on weekends.";
		} else if (day==6){
			return "The SSC's Friday hours are 8:15am-4:45pm";
		} else{
			return "The SSC's weekday hours are 8am-6pm";
		}
	}
	
	public static String getCoOpHours(){
		int day = getDayOfWeek();
		if (day==1 || day==7){
			return "Co-ops and Careers is closed on weekends.";
		} else if (day==2){
			return "Co-ops and Careers' Monday hours are 8:15am-4:45pm";
		} else if (day==6){
			return "Co-ops and Careers' Friday hours are 8:15am-4:45pm";
		}else{
			return "Co-ops and Careers' drop-in day hours are 8:15am-5:30pm";
		}
	}
	
	public static String getInfoHubHours(){
		int day = getDayOfWeek();
		if (day==1){
			return "The Info Hub's Sunday hours are 10am-11pm";
		} else if (day==7){
			return "The Info Hub's Saturday hours are 8am-11pm";
		}else if (day==6){
			return "The Info Hub's Friday hours are 6am-11pm";
		} else{
			return "The Info Hub's weekday hours are 6am-12am";
		}
	}
	
	public static String getSchumannGymHours(){
		int day = getDayOfWeek();
		if (day==1){
			return "The Schumann Fitness Center's Sunday hours are 11am-9pm";
		} else if (day==7){
			return "TThe Schumann Fitness Center's Saturday hours are 9am-6pm";
		}else if (day==6){
			return "The Schumann Fitness Center's Friday hours are 6am-9pm";
		} else{
			return "The Schumann Fitness Center's weekday hours are 6am-11pm";
		}
	}
	
	public static String getCStoreHours(){
		int day = getDayOfWeek();
		if (day==1||day==7){
			return "The C-Store's weekend hours are 2pm-11pm";
		} else{
			return "The C-Store's weekday hours are 11am-11pm";
		}
	}
	
	public static int getDayOfWeek(){
		Date now = new Date();
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        return calendar.get(Calendar.DAY_OF_WEEK); 
	}
}



