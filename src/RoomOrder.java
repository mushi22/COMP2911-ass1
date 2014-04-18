/**
 * Public class to for order of rooms
 * works as a helper class to Room.java
 * @author sohaibmushtaq
 *
 */
public class RoomOrder {
  private int capacity;
  private int number;

  /**
   * The roomORder constructor
   * @param type
   * @param number
   */
  public RoomOrder(String type, int number) {
    this.capacity = getCapacity(type);
    this.number = number;
  }

  /**
   * this gets the capacity of room type
   * @param type
   * @return
   */
  private int getCapacity(String type) {
    if ("single".compareToIgnoreCase(type) == 0) {
      return 1;
    } else if ("double".compareToIgnoreCase(type) ==0) {
      return 2;
    } else if ("triple".compareToIgnoreCase(type) == 0) {
      return 3;
    } else {
      throw new IllegalArgumentException();
    }
  }

  /**
   * Gets the capacity
   * @return capacity
   */
  public int getCapacity() {
    return capacity;
  }

  /**
   * Gets the number
   * @return number
   */
  public int getNumber() {
    return number;
  }

}
