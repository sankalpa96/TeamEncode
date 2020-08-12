package library.entities;
import java.io.Serializable;

// Author : Poornima
// Reviewer : Rovidu
// Mediator : Sankalpa
// Change 1 - change tItLe to title
// Change 2 - change AuThOr to author
// Change 3 - change CALLNO to callNo

@SuppressWarnings("serial")
public class Book implements Serializable {
	
	private String title;	// change tItLe to title
	private String author;	// change AuThOr to author
	private String callNo;	// change CALLNO to callNo
	private int iD;
	
	private enum sTaTe { AVAILABLE, ON_LOAN, DAMAGED, RESERVED };
	private sTaTe StAtE;
	
	
	public Book(String author, String title, String callNo, int id) {
		this.author = author;	// change AuThOr to author
		this.title = title;		// change tItLe to title
		this.callNo = callNo;	// change CALLNO to callNo
		this.iD = id;			
		this.StAtE = sTaTe.AVAILABLE;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Book: ").append(iD).append("\n")
		  .append("  Title:  ").append(title).append("\n")		// change tItLe to title
		  .append("  Author: ").append(author).append("\n")		// change AuThOr to author
		  .append("  CallNo: ").append(callNo).append("\n")		// change CALLNO to callNo
		  .append("  State:  ").append(StAtE);
		
		return sb.toString();
	}

	public Integer gEtId() {
		return iD;
	}

	public String gEtTiTlE() {
		return title;	//change tItLe to title
	}


	
	public boolean iS_AvAiLaBlE() {
		return StAtE == sTaTe.AVAILABLE;
	}

	
	public boolean iS_On_LoAn() {
		return StAtE == sTaTe.ON_LOAN;
	}

	
	public boolean iS_DaMaGeD() {
		return StAtE == sTaTe.DAMAGED;
	}

	
	public void BoRrOw() {
		if (StAtE.equals(sTaTe.AVAILABLE)) 
			StAtE = sTaTe.ON_LOAN;
		
		else 
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", StAtE));
		
		
	}


	public void ReTuRn(boolean DaMaGeD) {
		if (StAtE.equals(sTaTe.ON_LOAN)) 
			if (DaMaGeD) 
				StAtE = sTaTe.DAMAGED;
			
			else 
				StAtE = sTaTe.AVAILABLE;
			
		
		else 
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", StAtE));
				
	}

	
	public void RePaIr() {
		if (StAtE.equals(sTaTe.DAMAGED)) 
			StAtE = sTaTe.AVAILABLE;
		
		else 
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", StAtE));
		
	}


}
