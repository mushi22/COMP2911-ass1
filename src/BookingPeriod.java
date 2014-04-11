import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class BookingPeriod {

	
	public BookingPeriod (String month, int bdate, int nights){
		
		Date date;
		
		try {
			date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(month);
		} catch (ParseException e) {
			throw new IllegalArgumentException("cannot parse month");
		}
		
		this.startDate = Calendar.getInstance();
		this.startDate.setTime(date);
		
		
		this.startDate.set(Calendar.YEAR, DEFAULT_YEAR);
		this.startDate.set(Calendar.DATE, bdate);
		
		this.nights = nights;
		
		
		
	}
	
	public int getnigths(){
		return nights;
	}
	
	public Calendar getTime(){
		return (Calendar) startDate.clone();
	}
	
	private final int DEFAULT_YEAR = 2014;
	private Calendar startDate;
	private int nights;

}
