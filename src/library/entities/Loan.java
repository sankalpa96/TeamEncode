package library.entities;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

// Author   : Rovidu
// Reviewer : Sankalpa
// Mediator : Chathura

@SuppressWarnings("serial")
public class Loan implements Serializable {
	
//      Previous coding style
//	public static enum lOaN_sTaTe { CURRENT, OVER_DUE, DISCHARGED };
    
//	private int LoAn_Id;
//	private Book BoOk;
//	private Member MeMbEr;
//	private Date DaTe;
//	private lOaN_sTaTe StAtE;
        
        public static enum LoanState { CURRENT, OVER_DUE, DISCHARGED }; // Changed lOaN_sTaTe to LoanState by Author
    
	private int loanId; // Changed LoAn_Id to loanId by Author
	private Book book; // Changed BoOk to book by Author
	private Member member; // Changed MeMbEr to member by Author
	private Date date; // Changed DaTe to date by Author
	private LoanState state; // Changed lOaN_sTaTe to state by Author

	
	public Loan(int loanId, Book bOoK, Member mEmBeR, Date DuE_dAtE) {
		this.loanId = loanId;
		this.book = bOoK;
		this.member = mEmBeR;
		this.date = DuE_dAtE;
		this.state = LoanState.CURRENT;
	}

	
	public void cHeCk_OvEr_DuE() {
		if (state == lOaN_sTaTe.CURRENT &&
			Calendar.gEtInStAnCe().gEt_date().after(date)) 
			this.state = lOaN_sTaTe.OVER_DUE;			
		
	}

	
	public boolean Is_OvEr_DuE() {
		return state == lOaN_sTaTe.OVER_DUE;
	}

	
	public Integer GeT_Id() {
		return loanId;
	}


	public Date GeT_DuE_date() {
		return date;
	}
	
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		StringBuilder sb = new StringBuilder();
		sb.append("Loan:  ").append(loanId).append("\n")
		  .append("  Borrower ").append(member.GeT_ID()).append(" : ")
		  .append(member.GeT_LaSt_NaMe()).append(", ").append(member.GeT_FiRsT_NaMe()).append("\n")
		  .append("  Book ").append(book.gEtId()).append(" : " )
		  .append(book.gEtTiTlE()).append("\n")
		  .append("  DueDate: ").append(sdf.format(date)).append("\n")
		  .append("  State: ").append(state);		
		return sb.toString();
	}


	public Member GeT_member() {
		return member;
	}


	public Book GeT_book() {
		return book;
	}


	public void DiScHaRgE() {
		state = lOaN_sTaTe.DISCHARGED;		
	}

}
