package library.entities;
import java.util.Date;
import java.util.concurrent.TimeUnit;

// Author   : Rovidu
// Reviewer : Sankalpa
// Mediator : Chathura

public class Calendar {
	 
//    private static Calendar sElF;
    private static Calendar self; // Changed sElF to self by Author
        
//    private static java.util.Calendar cAlEnDaR;  
    private static java.util.Calendar calender; // Changed cAlEnDaR to calender by Author
		
    private Calendar() {
        calender = java.util.Calendar.getInstance();
    }
	
//    public static Calendar gEtInStAnCe() {
    public static Calendar getInstance() { // Changed gEtInStAnCe to getInstance by author
        if (self == null) {
            self = new Calendar();
        }
        return self;
    }
	
    public void incrementDate(int days) {
        calender.add(java.util.Calendar.DATE, days);		
    }
	
//    public synchronized void SeT_DaTe(Date DaTe) {
    public synchronized void setDate(Date date) { // Changed SeT_DaTe to setDate & DaTe to date by author
        try {
//            calender.setTime(DaTe);
            calender.setTime(date); // Changed DaTe to date by author
            calender.set(java.util.Calendar.HOUR_OF_DAY, 0);  
            calender.set(java.util.Calendar.MINUTE, 0);  
            calender.set(java.util.Calendar.SECOND, 0);  
            calender.set(java.util.Calendar.MILLISECOND, 0);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }	
    }
        
//    public synchronized Date gEt_DaTe() {
    public synchronized Date getDate() { // Changed gEt_DaTe to getDate by author
        try {
            calender.set(java.util.Calendar.HOUR_OF_DAY, 0);  
            calender.set(java.util.Calendar.MINUTE, 0);  
            calender.set(java.util.Calendar.SECOND, 0);  
            calender.set(java.util.Calendar.MILLISECOND, 0);
            return calender.getTime();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }	
    }

//    public synchronized Date gEt_DuE_DaTe(int loanPeriod) {
    public synchronized Date getDueDate(int loanPeriod) { // Changed gEt_DuE_DaTe to getDueDate by author
        
//        Previous coding style
//        Date nOw = getDate();
//        Date dUeDaTe = calender.getTime();
//        calender.setTime(nOw);
//        return dUeDaTe;

        Date now = getDate(); // Changed nOw to now by author
        calender.add(java.util.Calendar.DATE, loanPeriod);
        Date dueDate = calender.getTime(); // Changed dUeDaTe to dueDate by author
        calender.setTime(now); // Changed nOw to getDueDate by author
        return dueDate; // Changed dUeDaTe to dueDate by author
    }
	        
//    public synchronized long GeT_DaYs_DiFfErEnCe(Date targetDate) {		
    public synchronized long getDaysDifference(Date targetDate) { // Changed GeT_DaYs_DiFfErEnCe to getDaysDifference by author	

//        Previous coding style
//        long Diff_Millis = getDate().getTime() - targetDate.getTime();
//        long Diff_Days = TimeUnit.DAYS.convert(Diff_Millis, TimeUnit.MILLISECONDS);
//        return Diff_Days;
        
        long diffMills = getDate().getTime() - targetDate.getTime(); // Changed Diff_Millis to diffMills by author
        long diffDays = TimeUnit.DAYS.convert(diffMills, TimeUnit.MILLISECONDS); // Changed Diff_Days to diffMills by author
        return diffDays; // Changed Diff_Days to diffDays by author
    }
}
