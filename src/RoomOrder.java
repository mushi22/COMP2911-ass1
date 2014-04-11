public class RoomOrder {
  private int capacity;
  private int number;

  public RoomOrder(String type, int number) {
    this.capacity = getCapacity(type);
    this.number = number;
  }

  private int getCapacity(String type) {
    if ("single".equals(type)) {
      return 1;
    } else if ("double".equals(type)) {
      return 2;
    } else if ("triple".equals(type)) {
      return 3;
    } else {
      throw new IllegalArgumentException();
    }
  }

  public int getCapacity() {
    return capacity;
  }

  public int getNumber() {
    return number;
  }

}
