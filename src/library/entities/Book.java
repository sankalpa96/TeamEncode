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

@SuppressWarnings("serial")
public class Book implements Serializable {
	
	private String title;	// change tItLe to title
	private String author;	// change AuThOr to author
	private String callNo;	// change CALLNO to callNo
	private int id;			// change iD to id
	
	private enum State { AVAILABLE, ON_LOAN, DAMAGED, RESERVED };	// change sTaTe to State
	private State state;	// change sTaTe to State and StAtE to state
	
	
	public Book(String author, String title, String callNo, int id) {
		this.author = author;	// change AuThOr to author
		this.title = title;		// change tItLe to title
		this.callNo = callNo;	// change CALLNO to callNo
		this.id = id;			// change iD to id
		this.state = State.AVAILABLE;	// change sTaTe to State and StAtE to state
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Book: ").append(id).append("\n")				// change iD to id
		  .append("  Title:  ").append(title).append("\n")		// change tItLe to title
		  .append("  Author: ").append(author).append("\n")		// change AuThOr to author
		  .append("  CallNo: ").append(callNo).append("\n")		// change CALLNO to callNo
		  .append("  State:  ").append(state);					// change StAtE to state
		
		return sb.toString();
	}

	public Integer gEtId() {
		return iD;
	}

	public String gEtTiTlE() {
		return title;	//change tItLe to title
	}


	
	public boolean iS_AvAiLaBlE() {
		return state == State.AVAILABLE;  // change sTaTe to State and StAtE to state
	}

	
	public boolean iS_On_LoAn() {
		return state == State.ON_LOAN;	// change sTaTe to State and StAtE to state
	}

	
	public boolean iS_DaMaGeD() {
		return state == State.DAMAGED;	// change sTaTe to State and StAtE to state
	}

	
	public void BoRrOw() {
		if (state.equals(State.AVAILABLE)) 	// change sTaTe to State and StAtE to state
			state = State.ON_LOAN;		// change sTaTe to State and StAtE to state
		
		else 
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", state));  // change StAtE to state
		
		
	}


	public void ReTuRn(boolean DaMaGeD) {
		if (state.equals(State.ON_LOAN)) 	// change sTaTe to State and StAtE to state
			if (DaMaGeD) 
				state = State.DAMAGED;		// change sTaTe to State and StAtE to state
			
			else 
				state = State.AVAILABLE;	// change sTaTe to State and StAtE to state
			
		
		else 
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", state));  // change StAtE to state
				
	}

	
	public void RePaIr() {
		if (state.equals(State.DAMAGED)) 	// change sTaTe to State and StAtE to state
			state = State.AVAILABLE;    	// change sTaTe to State and StAtE to state
		
		else 
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", state));  // change StAtE to state
		
	}


}
