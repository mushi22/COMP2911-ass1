import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


public class Hotel {
  private String name;
  private Map<Integer, Set<Room>> roomsByCapacity;
  private Map<String, Room> roomsByNumber;

  public Hotel(String name) {
    this.name = name;
    this.roomsByCapacity = new HashMap<Integer, Set<Room>>();
    this.roomsByNumber = new TreeMap<String, Room>();
  }

  public String getName() {
    return this.name;
  }

  public void addRoom(Room room) {
    Set<Room> set = this.roomsByCapacity.get(room.getCapacity());

    if (set == null) {
      set = new TreeSet<Room>();
      this.roomsByCapacity.put(room.getCapacity(), set);
    }

    set.add(room);
    this.roomsByNumber.put(room.getNumber(), room);
  }

  public boolean book(String user, BookingPeriod period, List<RoomOrder> orders) {
    Set<Room> freeRooms = new TreeSet<Room>();
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

    for (Room room : freeRooms) {
      sb.append(" ").append(room.getNumber());
      room.addBooking(period, user);
    }

    System.out.println(sb);
    return true;
  }

  public boolean hasBooking(String user, String roomNumber, BookingPeriod period) {
    Room room = this.roomsByNumber.get(roomNumber);
    return (room != null) && room.isBooked(user, period);
  }

  public void cancel(String user, String roomNumber, BookingPeriod period) {
    Room room = this.roomsByNumber.get(roomNumber);
    if ((room != null) && room.isBooked(user, period)) {
      room.removeBooking(period);
    } else {
      System.out.println("Cancellation rejected");
    }
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
