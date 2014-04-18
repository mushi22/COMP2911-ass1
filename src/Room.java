import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;


/**
 * This class implements Comparable<Room> and functions for other 
 * room related activites
 * @author sohaibmushtaq
 *
 */
public class Room implements Comparable<Room> {
  public Room(String number, int capacity) {
    this.number = number;
    this.capacity = capacity;
    this.bookings = new TreeMap<Calendar, String>();
  }

  /**
   * Get room number
   * @return number
   */
  public String getNumber() {
    return number;
  }

  /**
   * get the capacity
   * @return capacity
   */
  public int getCapacity() {
    return capacity;
  }
  
  /**
   * This add bookings
   * @param period
   * @param user
   */
  public void addBooking(BookingPeriod period, String user) {
    for (Calendar day : period.getDates()) {
      this.bookings.put(day, user);
    }
  }

  /**
   * The function removes the booking
   * @param period
   */
  public void removeBooking(BookingPeriod period) {
    for (Calendar day : period.getDates()) {
      this.bookings.remove(day);
    }
  }

  /**
   * Checks if the booking period is free
   * @param period
   * @return boolean
   */
  public boolean isFree(BookingPeriod period) {
    for (Calendar day : period.getDates()) {
      if (this.bookings.containsKey(day)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Checks if the thing is booked
   * @param user
   * @param period
   * @return boolean
   */
  public boolean isBooked(String user, BookingPeriod period) {
    for (Calendar day : period.getDates()) {
      if (!user.equals(this.bookings.get(day))) {
        return false;
      }
    }
    return true;
  }

 /**
  * or the Print command, each reservation for the hotel should be printed on a
  * separate line in order of room number and start date with fields separated by a single space
  * hotelName roomNumber startMonth startDate numNights user
  * @param hotelName
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

  /**
   * compare to function
   */
  @Override
  public int compareTo(Room o) {
    return number.compareTo(o.number);
  }

  /**
   * check if object is equal
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Room)) return false;

    Room room = (Room) o;

    return !(number != null ? !number.equals(room.number) : room.number != null);
  }

  /**
   * Check has hashcode
   */
  @Override
  public int hashCode() {
    return number != null ? number.hashCode() : 0;
  }

  /**
   * Checks if bookings are adjoining
   * @param start
   * @param nights
   * @param other
   * @return
   */
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
