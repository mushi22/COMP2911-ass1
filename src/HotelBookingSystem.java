import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class HotelBookingSystem {
  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("Input file is required");
      System.exit(1);
    }

    Booking hSystem = new Booking();

    try {
      Scanner sc = new Scanner(new FileReader(args[0]));
      while ((sc.hasNextLine())) {
        hSystem.handleCommand(sc.nextLine());
      }
      sc.close();
    } catch (FileNotFoundException e) {
      System.out.println("Input file not found");
    }
  }
}
