import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class Room implements Comparable<Room> {
  public Room(String number, int capacity) {
    this.number = number;
    this.capacity = capacity;
    this.bookings = new TreeMap<Calendar, String>();
  }

  public String getNumber() {
    return number;
  }

  public int getCapacity() {
    return capacity;
  }
  public void addBooking(BookingPeriod period, String user) {
    for (Calendar day : period.getDates()) {
      this.bookings.put(day, user);
    }
  }

  public void removeBooking(BookingPeriod period) {
    for (Calendar day : period.getDates()) {
      this.bookings.remove(day);
    }
  }

  public boolean isFree(BookingPeriod period) {
    for (Calendar day : period.getDates()) {
      if (this.bookings.containsKey(day)) {
        return false;
      }
    }
    return true;
  }

  public boolean isBooked(String user, BookingPeriod period) {
    for (Calendar day : period.getDates()) {
      if (!user.equals(this.bookings.get(day))) {
        return false;
      }
    }
    return true;
  }

  /**
   * for the Print command, each reservation for the hotel should be printed on a
   * separate line in order of room number and start date with fields separated by a single space
   * hotelName roomNumber startMonth startDate numNights user
   */
  public void print(String hotelName) {
    SimpleDateFormat format = new SimpleDateFormat("MMM dd", Locale.ENGLISH);
    String currentUser = null;
    int nights = 1;
    Calendar start = null;
    for (Map.Entry<Calendar, String> entry : this.bookings.entrySet()) {
      if (entry.getValue().equals(currentUser) && isAdjoining(start, nights, entry.getKey())) {
        nights++;
      } else {
        if (currentUser != null) {
          System.out.println(hotelName + " " + this.number + " " +
              format.format(start.getTime()) + " " + nights + " " + currentUser);
        }
        nights = 1;
        start = entry.getKey();
        currentUser = entry.getValue();
      }
    }

    if ((currentUser != null)) {
      System.out.println(hotelName + " " + this.number + " " +
          format.format(start.getTime()) + " " + nights + " " + currentUser);
    }
  }

  @Override
  public int compareTo(Room o) {
    return number.compareTo(o.number);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Room)) return false;

    Room room = (Room) o;

    return !(number != null ? !number.equals(room.number) : room.number != null);
  }

  @Override
  public int hashCode() {
    return number != null ? number.hashCode() : 0;
  }

  private boolean isAdjoining(Calendar start, int nights, Calendar other) {
    if (null == start) {
      return false;
    }

    Calendar calendar = (Calendar) start.clone();
    calendar.add(Calendar.DATE, nights);
    return calendar.equals(other);
  }

  private String number;
  private int capacity;
  private Map<Calendar, String> bookings;
}
