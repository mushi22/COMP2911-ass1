import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * The hotel class handles things to do with hotel andreading in hotels to Hash 
 * and treep Map
 * @author sohaibmushtaq
 *
 */
public class Hotel {
  private String name;
  private Map<Integer, Set<Room>> roomsByCapacity;
  private Map<String, Room> roomsByNumber;

  public Hotel(String name) {
    this.name = name;
    this.roomsByCapacity = new HashMap<Integer, Set<Room>>();
    this.roomsByNumber = new TreeMap<String, Room>();
  }

  /**
   * This fucntion returns the name
   * @return name
   */
  public String getName() {
    return this.name;
  }

  /**
   * The function adds rooms using the Room class
   * @param room
   */
  public void addRoom(Room room) {
    Set<Room> set = this.roomsByCapacity.get(room.getCapacity());

    if (set == null) {
      set = new TreeSet<Room>();
      this.roomsByCapacity.put(room.getCapacity(), set);
    }

    set.add(room);
    this.roomsByNumber.put(room.getNumber(), room);
  }

  
  /**
   * The checks if booking is valid
   * @param user
   * @param period
   * @param orders
   * @return boolean 
   */
  public boolean book(String user, BookingPeriod period, List<RoomOrder> orders) {
	    Set<Room> freeRooms = new TreeSet<Room>();
	    StringBuilder sb = new StringBuilder();
	    sb.append(this.name);

	    for (RoomOrder order : orders) {
	      Set<Room> set = this.roomsByCapacity.get(order.getCapacity());
	      if (set == null) {
	        return false;
	      }

	      for (int i = 0; i < order.getNumber(); i++) {
	        Room free = null;
	        for (Room room : set) {
	          if (!freeRooms.contains(room) && room.isFree(period)) {
	            free = room;
	            sb.append(" ").append(room.getNumber());
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


	    for (Room room : freeRooms) {
	      room.addBooking(period, user);
	    }

	    System.out.println(sb);
	    return true;
	  }

  /**
   * The checks if the room has booking for user and that period
   * @param user
   * @param roomNumber
   * @param period
   * @return boolean
   */
  public boolean hasBooking(String user, String roomNumber, BookingPeriod period) {
    Room room = this.roomsByNumber.get(roomNumber);
    return (room != null) && room.isBooked(user, period);
  }

  /**
   * this fucntion cancels existing bookings
   * @param user
   * @param roomNumber
   * @param period
   */
  public boolean cancel(String user, String roomNumber, BookingPeriod period) {
    Room room = this.roomsByNumber.get(roomNumber);
    if ((room != null) && room.isBooked(user, period)) {
     room.removeBooking(period);
      //System.out.println("Reservation cancelled");
     return true;

    } else {
      System.out.println("Cancellation rejected");
      return false;
    
    }
    //System.out.println("Reservation cancelled");
  }

  /**
   * for the Print command, each reservation for the hotel should be printed on a
   * separate line in order of room number and start date with fields separated by a single space
   * hotelName roomNumber startMonth startDate numNights user
   */
  public void print() {
    for (Map.Entry<String, Room> entry : this.roomsByNumber.entrySet()) {
      entry.getValue().print(this.name);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Hotel)) return false;

    Hotel hotel = (Hotel) o;

    return !(name != null ? !name.equals(hotel.name) : hotel.name != null);
  }

  @Override
  public int hashCode() {
    return name != null ? name.hashCode() : 0;
  }
}
