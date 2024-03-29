import java.text.ParseException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * The booking period sets the booking time in calendar
 * @author sohaibmushtaq
 *
 */

public class BookingPeriod {
  private static final int DEFAULT_YEAR = 2014;

  private int nights;
  private Calendar start;

  /**
   * The function uses month, day and nights an generates values in calendar class
   * @param month
   * @param day
   * @param nights
   */
  public BookingPeriod(String month, int day, int nights) {
    Date date;

    try {
      date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(month);
    } catch (ParseException e) {
      throw new IllegalArgumentException("cannot parse month");
    }

    this.start = Calendar.getInstance();
    this.start.setTime(date);

    this.start.set(Calendar.YEAR, DEFAULT_YEAR);
    this.start.set(Calendar.DATE, day);

    this.nights = nights;
  }

  /**
   * This function gets the dates for booking
   * @return date
   */
  public List<Calendar> getDates() {
    List<Calendar> dates = new LinkedList<Calendar>();
    Calendar tmp = (Calendar) this.start.clone();
    for (int i = 0; i < nights; i++) {
      dates.add((Calendar) tmp.clone());
      tmp.add(Calendar.DATE, 1);
    }
    return dates;
  }
}
