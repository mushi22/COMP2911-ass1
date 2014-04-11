
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


public class Hotel {
	
	 private String name;
	  private Map<Integer, Set<Room>> roomsByCapacity;
	  private Map<String, Room> roomsByNumber;
	
	
	public Hotel(String name){
		this.name = name;
	    this.roomsByCapacity = new HashMap<Integer, Set<Room>>();
	    this.roomsByNumber = new TreeMap<String, Room>();
	}
	

	

	public void addRoom(Room room){
		
		Set<Room> set = this.roomsByCapacity.get(room.getCapacity());
		
		if(set == null) {
			set = new HashSet<Room>();
			this.roomsByCapacity.put(room.getCapacity(), set);
		}
		
		set.add(room);
		this.roomsByNumber.put(room.getNumber(), room);
		
	}
	
	public boolean book(String user, BookingPeriod period, List<RoomOrder> orders){
		Set<Room> freeRooms = new TreeSet<Room>();
		for(RoomOrder order : orders){
			Set<Room> set = this.roombyCapacity.get(order.getCapacity());
			if(set == null ) {
				return false;
			}
			
			
			for(int i = 0; i < order.getNumber(); i++){
				Room free = null;
				for (Room room : set) {
					if(!freeRooms.contains(room) && room.isFree(period)) {
						free = room;
						break;
					}
				}
				
				if (free == null) {
					return false;
				} else {
					freeRooms.add(free);
				}
			}
		}
		
		
		StringBuilder sb = new StringBuilder();
		sb.append(this.name);
		
		for(Room room : freeRooms) {
			sb.append(" ").append(room.getNumber());
			room.addBooking(period,user);
		}
		
		System.out.println(sb);
		return true;
	}
	
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
		

	
}	
	
	

	
	
	
	

