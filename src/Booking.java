
import java.util.ArrayList;
import java.util.List;


public class Booking {

	public Booking() {
		hotels = new ArrayList<Hotel>();
	}
	
	
	public void handleCommand(String command){
		String[] lines = command.trim().split("(\\s)+");
		
		if (lines.length > 0 ){
			if(lines[0].equals("Hotel")) {
				
				
				addRoom(lines[1], lines[2], Integer.valueOf(lines[3]));
			}
			if(lines[1].equals("Book")) {
				BookRooms(lines[1], new BookingPeriod(lines[2], Integer.valueOf(lines[3]), Integer.valueOf(lines[4])), getOrders(lines));
			}
		}
	}
	
	
	
	
	public void addRoom(String hotelName, String number, int capacity) {
		Hotel hotel = getHotel(hotelName);
		
		if(hotel == null) {
			hotel = new Hotel(hotelName);
			hotels.add(hotel);
		}
		hotel.addRoom(new Room(number, capacity));
	}
	
	private Hotel getHotel(String hotelName) {
		for (Hotel hotel : hotels){
			if(hotel.getName().equals(hotelName)){
				return hotel;
			}
		}
		return null;
	}
	
	
	private void bookRoom(String user, BookingPeriod period, List<RoomOrder> orders){
		for(Hotel hotel : hotels){
			if(hotel.book(user, period, orders)){
				return;
			}
		}
		System.out.println("Booking Rejected");
	}
	
	
	
	private ArrayList<Hotel> hotels;



	
	
}

	