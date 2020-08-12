package library.entities;
import java.util.Date;
import java.util.concurrent.TimeUnit;

// Author   : Rovidu
// Reviewer : Sankalpa
// Mediator : Chathura

public class Calendar {
	 
	private static Calendar SELF; // Changed sElF to SELF by Author
	private static java.util.Calendar CALENDAR; // Changed CALENDAR to CALENDAR by Author
	
	
	private Calendar() {
		CALENDAR = java.util.Calendar.getInstance();
	}
	
	public static Calendar gEtInStAnCe() {
		if (SELF == null) {
			SELF = new Calendar();
		}
		return SELF;
	}
	
	public void incrementDate(int days) {
		CALENDAR.add(java.util.Calendar.DATE, days);		
	}
	
	public synchronized void SeT_DaTe(Date DaTe) {
		try {
			CALENDAR.setTime(DaTe);
	        CALENDAR.set(java.util.Calendar.HOUR_OF_DAY, 0);  
	        CALENDAR.set(java.util.Calendar.MINUTE, 0);  
	        CALENDAR.set(java.util.Calendar.SECOND, 0);  
	        CALENDAR.set(java.util.Calendar.MILLISECOND, 0);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	public synchronized Date gEt_DaTe() {
		try {
	        CALENDAR.set(java.util.Calendar.HOUR_OF_DAY, 0);  
	        CALENDAR.set(java.util.Calendar.MINUTE, 0);  
	        CALENDAR.set(java.util.Calendar.SECOND, 0);  
	        CALENDAR.set(java.util.Calendar.MILLISECOND, 0);
			return CALENDAR.getTime();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}

	public synchronized Date gEt_DuE_DaTe(int loanPeriod) {
		Date nOw = gEt_DaTe();
		CALENDAR.add(java.util.Calendar.DATE, loanPeriod);
		Date dUeDaTe = CALENDAR.getTime();
		CALENDAR.setTime(nOw);
		return dUeDaTe;
	}
	
	public synchronized long GeT_DaYs_DiFfErEnCe(Date targetDate) {
		
		long Diff_Millis = gEt_DaTe().getTime() - targetDate.getTime();
	    long Diff_Days = TimeUnit.DAYS.convert(Diff_Millis, TimeUnit.MILLISECONDS);
	    return Diff_Days;
	}

}
