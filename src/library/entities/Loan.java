package library.entities;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

// Author   : Rovidu
// Reviewer : Sankalpa
// Mediator : Chathura

@SuppressWarnings("serial")
public class Loan implements Serializable {
	    
//    public static enum lOaN_sTaTe { CURRENT, OVER_DUE, DISCHARGED };    
    public static enum LoanState { CURRENT, OVER_DUE, DISCHARGED }; // Changed lOaN_sTaTe to LoanState by Author
    
//    private int LoAn_Id;
    private int loanId; // Changed LoAn_Id to loanId by Author
//    private Book BoOk;
    private Book book; // Changed BoOk to book by Author
//    private Member MeMbEr;
    private Member member; // Changed MeMbEr to member by Author
//    private Date DaTe;
    private Date date; // Changed DaTe to date by Author
//    private lOaN_sTaTe StAtE;
    private LoanState state; // Changed lOaN_sTaTe to state by Author
    
//    public Loan(int loanId, Book bOoK, Member mEmBeR, Date DuE_dAtE) {
    public Loan(int loanId, Book book, Member member, Date dueDate) { // bOoK, mEmBeR, & DuE_dAtE changed to book, member & dueDate by Author
        this.loanId = loanId; 
//        this.book = bOoK;
        this.book = book; // bOoK changed to book by Author
//        this.member = mEmBeR;
        this.member = member; // mEmBeR changed to member by Author
//        this.date = DuE_dAtE;
        this.date = dueDate; // DuE_dAtE changed to dueDate by Author
        this.state = LoanState.CURRENT;
    }

//    public void cHeCk_OvEr_DuE() {    
    public void checkOverDue() { // cHeCk_OvEr_DuE changed to checkOverDue by author             
//        if (state == lOaN_sTaTe.CURRENT &&
//                    Calendar.gEtInStAnCe().gEt_date().after(date)) 
//                    this.state = lOaN_sTaTe.OVER_DUE;            
        if (state == LoanState.CURRENT && Calendar.getInstance().getDate().after(date)) { // lOaN_sTaTe changed to LoanState & gEtInStAnCe().gEt_date() changed to getInstance().getDate() by author
            this.state = LoanState.OVER_DUE;		
        }
    }

//    public boolean Is_OvEr_DuE() {
    public boolean isOverDue() { // Is_OvEr_DuE changed to isOverDue by author
//        return state == lOaN_sTaTe.OVER_DUE;
        return state == LoanState.OVER_DUE; // lOaN_sTaTe changed to LoanState by author
    }

//    public Integer GeT_Id() {
    public Integer getId() { // GeT_Id changed to getId by author
        return loanId;
    }

//    public Date GeT_DuE_date() {
    public Date getDueDate() { // GeT_DuE_date changed to getDueDate by author
        return date;
    }

    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        StringBuilder sb = new StringBuilder();
        sb.append("Loan:  ").append(loanId).append("\n")
//		  .append("  Borrower ").append(member.GeT_ID()).append(" : ")
          .append("  Borrower ").append(member.getId()).append(" : ") // GeT_ID changed to getId() by author
//		  .append(member.GeT_LaSt_NaMe()).append(", ").append(member.GeT_FiRsT_NaMe()).append("\n")
          .append(member.getLastName()).append(", ").append(member.getFirstName()).append("\n") // GeT_LaSt_NaMe() changed to getLastName() & GeT_FiRsT_NaMe() changed to getFirstName() by author
//		  .append("  Book ").append(book.gEtId()).append(" : " )
          .append("  Book ").append(book.getId()).append(" : " ) // gEtId() changed to getId() by author
//		  .append(book.gEtTiTlE()).append("\n")
          .append(book.getTitle()).append("\n")// gEtTiTlE() changed to getTitle() by author
          .append("  DueDate: ").append(sdf.format(date)).append("\n")
          .append("  State: ").append(state);		
        return sb.toString();
    }

//    public Member GeT_member() {
    public Member getMember() { // lOaN_sTaTe changed to getMember by author
        return member;
    }

//    public Book GeT_book() {
    public Book getBook() { // lOaN_sTaTe changed to getBook by author
        return book;
    }

//    public void DiScHaRgE() {
    public void discharge() { // lOaN_sTaTe changed to discharge by author
//        state = lOaN_sTaTe.DISCHARGED;		
        state = LoanState.DISCHARGED; // lOaN_sTaTe changed to LoanState by author
    }

}
