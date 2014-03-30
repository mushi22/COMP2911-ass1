import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class HotelBookingSystem {

	public HotelBookingSystem() {
		aHotel = new ArrayList<Hotel>();
		mBookings = new HashMap<String, ArrayList<Booking>>();
	}
	//Contains all hotels 
	private ArrayList<Hotel> aHotel;
	//contains bookings. 
	private HashMap<String, ArrayList<Booking>> mBookings;
	
	
	public static void main(String[] args){
		
		try
	      {
	          Scanner sc = new Scanner(new FileReader(args[0])); 
	          HotelBookingSystem hSystem = new HotelBookingSystem();
	          
	          String line = null;
	          String[] lines = null;
	          
	          while((line= sc.nextLine()) != null) {
	        	  lines = line.trim().split("(\\s)+");
	        	  
	        	  if (lines.length > 0 ) {
	        		  
	        		  if (lines[0].equals("Hotel")) {
	        			  //create new hotel
	        			  Hotel h = new Hotel(lines[2], Integer.valueOf(lines[1])); 
	        			  hSystem.addHotel(h);
	        		  }else if (lines[0].equals("Book")) {
	        			  //book room
	        		  }else if (lines[0].equals("Change")) {
	        			  //change booking
	        		  }else if (lines[0].equals("Cancel")) {
	        			  //cancel booking
	        		  }else if (lines[0].equals("Print" )) {
	        			  //print booking
	        			  
	        		  }
	        	  }
	          }
	          sc.close();
	      }
	      catch (FileNotFoundException e) {}
	}
	
	public void addHotel(Hotel r){
		if (!aHotel.contains(r)){
			aHotel.add(r);
		}
	}
}
