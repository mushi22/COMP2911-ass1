import java.util.Calendar;
import java.util.Map;


public class Room implements Comparable<Room> {

	
	public Room(String number, int capacity){
		this.number = number;
		this.capacity = capacity;
		
	}
	public String getNumber(){
		return number;
		
	}
	
	public int getCapacity(){
		return capacity;
	}
	
	
	
	
	
	
	private String number;
	private int capacity;
	private Map<Calendar, String> bookings;
	@Override
	public int compareTo(Room arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
