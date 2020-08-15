package library.entities;
import java.io.Serializable;

// Author : Poornima
// Reviewer : Rovidu
// Mediator : Sankalpa

// Change 1 - change tItLe to title
// Change 2 - change AuThOr to author
// Change 3 - change CALLNO to callNo
// Change 4 - change iD to id
// Change 5 - change enum name sTaTe to State
// Change 6 - change enum object name StAtE to state
// Change 7 - change function names gEtId & gEtTiTlE to getId & getTitle
// Change 8 - change function names iS_AvAiLaBlE, iS_On_LoAn, iS_DaMaGeD

@SuppressWarnings("serial")
public class Book implements Serializable {
	
	//private String tItLe;
	private String title;	// change tItLe to title
	//private String AuThOr;
	private String author;	// change AuThOr to author
	//private String CALLNO;
	private String callNo;	// change CALLNO to callNo
	//private int iD;
	private int id;			// change iD to id
	
	//private enum sTaTe { AVAILABLE, ON_LOAN, DAMAGED, RESERVED };
	private enum State { AVAILABLE, ON_LOAN, DAMAGED, RESERVED };	// change sTaTe to State
	//private sTaTe StAtE;
	private State state;	// change sTaTe to State and StAtE to state
	
	
	public Book(String author, String title, String callNo, int id) {
		//this.AuThOr = author;
		this.author = author;	// change AuThOr to author
		//this.tItLe = title;
		this.title = title;		// change tItLe to title
		//this.CALLNO = callNo;
		this.callNo = callNo;	// change CALLNO to callNo
		//this.iD = id;
		this.id = id;			// change iD to id
		//this.StAtE = sTaTe.AVAILABLE;
		this.state = State.AVAILABLE;	// change sTaTe to State and StAtE to state
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		//sb.append("Book: ").append(iD).append("\n")
		sb.append("Book: ").append(id).append("\n")				// change iD to id
		//  .append("  Title:  ").append(tItLe).append("\n")
		  .append("  Title:  ").append(title).append("\n")		// change tItLe to title
		//  .append("  Author: ").append(AuThOr).append("\n")
		  .append("  Author: ").append(author).append("\n")		// change AuThOr to author
		//    .append("  CallNo: ").append(CALLNO).append("\n")
		  .append("  CallNo: ").append(callNo).append("\n")		// change CALLNO to callNo
		//  .append("  State:  ").append(StAtE);
		  .append("  State:  ").append(state);					// change StAtE to state
		
		return sb.toString();
	}

	//public Integer gEtId() {	
	public Integer getId() {	// change gEtId to getId
		//return iD;
		return id;		// change iD to id
	}

	//public String gEtTiTlE() {
	public String getTitle() {	// change gEtTiTlE to getTitle
		//return tItLe;
		return title;	//change tItLe to title
	}


	//public boolean iS_AvAiLaBlE() {
	public boolean isAvailable() {		// change iS_AvAiLaBlE to isAvailable
		//return StAtE == sTaTe.AVAILABLE;
		return state == State.AVAILABLE;  // change sTaTe to State and StAtE to state
	}

	
	//public boolean iS_On_LoAn() {
		public boolean isOnLoan() {		// change iS_On_LoAn to isOnLoan
		//return StAtE == sTaTe.ON_LOAN;
		return state == State.ON_LOAN;	// change sTaTe to State and StAtE to state
	}

	//public boolean iS_DaMaGeD() {
	public boolean isDamaged() {		// change iS_DaMaGeD to isDamaged 
		//return StAtE == sTaTe.DAMAGED;
		return state == State.DAMAGED;	// change sTaTe to State and StAtE to state
	}

	
	public void BoRrOw() {
		//if (StAtE.equals(sTaTe.AVAILABLE)) 
		if (state.equals(State.AVAILABLE)) 	// change sTaTe to State and StAtE to state
			//StAtE = sTaTe.ON_LOAN;
			state = State.ON_LOAN;		// change sTaTe to State and StAtE to state
		
		else 
			//throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", StAtE));
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", state));  // change StAtE to state
		
		
	}


	public void ReTuRn(boolean DaMaGeD) {
		//if (StAtE.equals(sTaTe.ON_LOAN)) 
		if (state.equals(State.ON_LOAN)) 	// change sTaTe to State and StAtE to state
			if (DaMaGeD) 
				//StAtE = sTaTe.DAMAGED;
				state = State.DAMAGED;		// change sTaTe to State and StAtE to state
			
			else 
				//StAtE = sTaTe.AVAILABLE;
				state = State.AVAILABLE;	// change sTaTe to State and StAtE to state
			
		
		else 
			//throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", StAtE));
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", state));  // change StAtE to state
				
	}

	
	public void RePaIr() {
		if (state.equals(State.DAMAGED)) 	// change sTaTe to State and StAtE to state
			state = State.AVAILABLE;    	// change sTaTe to State and StAtE to state
		
		else 
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", state));  // change StAtE to state
		
	}


}
