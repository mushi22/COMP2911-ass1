
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Booking class that handles most functions, such as reading in input
 * then calling other classes to work with this input
 * creates the hotelarray list with object hotel
 * @author sohaibmushtaq
 *
 */
public class Booking {
  private ArrayList<Hotel> hotels;

  public Booking() {
    hotels = new ArrayList<Hotel>();
  }

  
 /**
  * The Hnadle Command reads the in the input from input file
  * Then Calls different functions related to commands from inputs
  * @param command from input
  */
  public void handleCommand(String command) {
    String[] lines = command.trim().split("(\\s)+");

    if (lines.length > 0) {
      if (lines[0].equals("Hotel")) {
/*
Hotel <hotel> <room> <capacity>
            # specify that <hotel> has a room with number <room> that has the size <capacity>
            # all rooms will be specified before any booking commands are issued
*/
        addRoom(lines[1], lines[2], Integer.valueOf(lines[3]));
      } else if (lines[0].equals("Book")) {
/*
Book <user> <month> <date> <nights> <type1> <number1> <type2> <number2> . . .
            # <user> request starting <month> and <date> for <nights>: <number1> rooms of <type1>, etc.
*/
        bookRooms(lines[1], new BookingPeriod(lines[2],
                  Integer.valueOf(lines[3]), Integer.valueOf(lines[4])), getOrders(lines));
      } else if (lines[0].equals("Change")) {
/*
Change <user> <hotel> <room> <month1> <date1> <nights1> <month2> <date2> <nights2> <type>
            # <user> request to change reservation for <hotel> <room> starting <month1> and <date1> for <nights1>
            # to a new reservation starting <month2> and <date2> for <nights2>: room of <type>
            # (only if reservation to be changed exists and was made by <user>, and the change can be made)
*/
          change(lines[1], lines[2], lines[3],
              new BookingPeriod(lines[4], Integer.valueOf(lines[5]), Integer.valueOf(lines[6])),
              new BookingPeriod(lines[7], Integer.valueOf(lines[8]), Integer.valueOf(lines[9])),
              lines[10]);
      } else if (lines[0].equals("Cancel")) {
/*
Cancel <user> <hotel> <room> <month> <date> <nights>
            # <user> request to cancel reservation for <hotel> <room> starting <month> and <date> for <nights>
            # (if reservation to be cancelled exists and was made by <user>)
*/
        Hotel hotel = getHotel(lines[2]);

        if (hotel == null) {
          System.out.println("Cancellation rejected");
        } else {
         boolean result = hotel.cancel(lines[1], lines[3],
              new BookingPeriod(lines[4], Integer.valueOf(lines[5]), Integer.valueOf(lines[6])));
          	//  System.out.println("Reservation cancelled");
         	if (result) {
         		System.out.println("Reservation cancelled");
         }
        }
        
      } else if (lines[0].equals("Print")) {
/*
Print <hotel>
            # print all reservations for <hotel>
*/
        print(lines);
      }
    }
  }

  /**
   * The function adds rooms to the system
   * @param hotelName
   * @param number
   * @param capacity
   */
  private void addRoom(String hotelName, String number, int capacity) {
    Hotel hotel = getHotel(hotelName);

    if (hotel == null) {
      hotel = new Hotel(hotelName);
      hotels.add(hotel);
    }

    hotel.addRoom(new Room(number, capacity));
  }

  /**
   * The function handles multiple room bookings needed
   * @param lines
   * @return
   */
  
  private List<RoomOrder> getOrders(String[] lines) {
    List<RoomOrder> orders = new ArrayList<RoomOrder>();

    for (int i = 5; i < lines.length; i = i + 2) {
      orders.add(new RoomOrder(lines[i], Integer.valueOf(lines[i + 1])));
    }
    return orders;
  }

  
  /**
   * The function books Roomsin the system.
   * @param user
   * @param period
   * @param orders
   */
  private void bookRooms(String user, BookingPeriod period, List<RoomOrder> orders) {
    for (Hotel hotel : hotels) {
      if (hotel.book(user, period, orders)) {
        return;
      }
    }

    System.out.println("Booking Rejected");
  }


  /**
   * The function handles changes to bookings in the system
   * @param user
   * @param hotelName
   * @param roomNumber
   * @param oldPeriod
   * @param newPeriod
   * @param type
   */
  private void change(String user, String hotelName, String roomNumber,
                      BookingPeriod oldPeriod, BookingPeriod newPeriod, String type) {
    Hotel hotel = getHotel(hotelName);
    List<RoomOrder> orders = Arrays.asList(new RoomOrder(type, 1));

    if ((hotel != null) && (hotel.hasBooking(user, roomNumber, oldPeriod))) {
      for (Hotel h : hotels) {
        if (h.book(user, newPeriod, orders)) {
          hotel.cancel(user, roomNumber, oldPeriod);
          return;
        }
      }
    }
    System.out.println("Change Rejected");
  }

  /**
   * This prints out using System.out.println
   * @param lines
   */
  private void print(String[] lines) {
    if (lines.length > 1) {
      Hotel hotel = getHotel(lines[1]);
      if (hotel != null) {
        hotel.print();
      }
    } else {
      for (Hotel hotel : hotels) {
        hotel.print();
      }
    }
  }

  /**
   * The gets the current hotel using hotelname
   * @param hotelName
   * @return
   */
  private Hotel getHotel(String hotelName) {
    for (Hotel hotel : hotels) {
      if (hotel.getName().equals(hotelName)) {
        return hotel;
      }
    }
    return null;
  }
}

	