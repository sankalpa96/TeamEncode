
//Author: Chathura
//Reviewer: Poornima
//Mediator: Rovidu

package library.borrowbook;
import java.util.ArrayList;
import java.util.List;

import library.entities.Book;
import library.entities.Library;
import library.entities.Loan;
import library.entities.Member;

//public class bORROW_bOOK_cONTROL {
public class BorrowBookControl { 	//class name bORROW_bOOK_cONTROL was changed to BorrowBookControl
	
	private BorrowBookUI ui;
	
	//private Library lIbRaRy;
	private Library library; 	//variable name lIbRaRy was changed to library
	//private Member member;
	private Member member; 		//variable name member was changed to member
	//private enum CONTROL_STATE { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };
	private enum ControlState { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED }; 	//enum name CONTROL_STATE was changed to ControlState
	//private ControlState sTaTe;
	private ControlState state; 	//variable name sTaTe was changed to state
	
	//private List<Book> pEnDiNg_LiSt;
	private List<Book> pendingList; 	//variable name pEnDiNg_LiSt was changed to pendingList
	//private List<Loan> cOmPlEtEd_LiSt;
	private List<Loan> completedList; 	//variable name cOmPlEtEd_LiSt was changed to completedList
	//private Book bOoK;
	private Book book; 	//variable name bOoK was changed to book
	
	
	public BorrowBookControl() {
		//this.library = Library.GeTiNsTaNcE();
		this.library = Library.getInstance(); 	//method name GeTiNsTaNcE was changed to getInstance
		state = ControlState.INITIALISED;
	}
	

	//public void SeT_ui(BorrowBookUI ui) {
	public void setUI(BorrowBookUI ui) { 	//method name SeT_ui and variable name Ui were changed to setUI and ui
		// if (!state.equals(ControlState.INITIALISED)) 
			// throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
		if (!state.equals(ControlState.INITIALISED)) { 		//curly brackets were added to be accordance with style guidelines
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
		}
			
		this.ui = ui;
		//ui.SeT_StAtE(BorrowBookUI.ui_STaTe.READY);
		ui.setState(BorrowBookUI.UIState.READY); 	//method name SeT_StAtE and enum name ui_STaTe were changed to setState and UIState
		state = ControlState.READY;		
	}

		
	//public void SwIpEd(int member_Id) {
	public void swiped(int memberId) { 	//method name SwIpEd and parameter variable name member_Id was chaned to swiped and memberId
		// if (!state.equals(ControlState.READY)) 
			// throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
		if (!state.equals(ControlState.READY)){ 	//curly brackets were added to be accordance with style guidelines
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
		}
			


		//member = library.gEt_MeMbEr(MemberId);
		member = library.getMember(memberId); 	//method name gEt_MeMbEr and argument variable MemberId were changed to getMember and memberId
		if (member == null) {
			//ui.DiSpLaY("Invalid memberId");
			ui.display("Invalid memberId"); 	//method name DiSpLaY was changed to display
			return;
		}
		//if (library.cAn_MeMbEr_BoRrOw(member)) {
		if (library.canMemberBorrow(member)) { 	//method name cAn_MeMbEr_BoRrOw was changed to canMemberBorrow
			pendingList = new ArrayList<>();
			ui.setState(BorrowBookUI.UIState.SCANNING);
			state = ControlState.SCANNING; 
		}
		else {
			ui.display("Member cannot borrow at this time");
			ui.setState(BorrowBookUI.UIState.RESTRICTED); 
		}
	}
	
	
	//public void ScAnNeD(int bookiD) {
	public void scanned(int bookId) { 		//method name ScAnNeD and parameter variable bookiD were chaned to scanned and bookId
		book = null;
		// if (!state.equals(ControlState.SCANNING)) 
			// throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
		if (!state.equals(ControlState.SCANNING)){ 	 	//curly brackets were added to be accordance with style guidelines
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
		}
			
			
		//book = library.gEt_BoOk(bookId);
		book = library.getBook(bookId); 	//method name gEt_BoOk was chaned to getBook
		if (book == null) {
			ui.display("Invalid bookId");
			return;
		}
		//if (!book.iS_AvAiLaBlE()) {
		if (!book.isAvailable()) { 	//method name iS_AvAiLaBlE was changed to isAvailable
			ui.display("Book cannot be borrowed");
			return;
		}
		pendingList.add(book);
		// for (Book B : pendingList) 
			// ui.display(B.toString());
		for (Book B : pendingList){ 	//curly brackets were add to be accordance with style guidelines
			//ui.display(B.toString());
			String stringB = B.toString();
			ui.display(stringB); 	//Method's return value was assigned to a variable before passing as an argument
		}
			
		
		//if (library.gEt_NuMbEr_Of_LoAnS_ReMaInInG_FoR_MeMbEr(member) - pendingList.size() == 0) {
		if (library.getNumberOfLoansRemainingForMember(member) - pendingList.size() == 0) { 	//method name gEt_NuMbEr_Of_LoAnS_ReMaInInG_FoR_MeMbEr was chaned getNumberOfLoansRemainingForMember
			ui.display("Loan limit reached");
			//CoMpLeTe();
			complete(); 	// method name CoMpLeTe was changed to complete
		}
	}
	
	
	public void complete() {
		// if (pendingList.size() == 0) 
			// cancel();
		if (pendingList.size() == 0){ 	//curly brackets were add to be accordance with style guidelines
			cancel(); 	//method name CaNcEl(); was changed to cancel()
		}
			
		
		else {
			ui.display("\nFinal Borrowing List");
			// for (Book book : pendingList) 
				// ui.display(book.toString());
			for (Book book : pendingList){ 		//curly brackets were add to be accordance with style guidelines
				String stringBook = book.toString()
				ui.display(stringBook); 	//Method's return value was assigned to a variable before passing as an argument
			} 
				
			
			completedList = new ArrayList<Loan>();
			ui.setState(BorrowBookUI.UIState.FINALISING);
			state = ControlState.FINALISING;
		}
	}


	//public void CoMmIt_LoAnS() { 	
	public void commitLoans() { 	// method name CoMmIt_LoAnS was changed to commitLoans
		// if (!state.equals(ControlState.FINALISING)) 
			// throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
		if (!state.equals(ControlState.FINALISING)) { 		//curly brackets were add to be accordance with style guidelines
			throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
		}
			
		
			
		for (Book B : pendingList) {
			//Loan lOaN = library.issueLoan(B, member);
			Loan loan = library.issueLoan(B, member); 		//variable name lOaN and method name iSsUe_LoAn were changed to loan and issueLoan
			completedList.add(loan);			
		}
		ui.display("Completed Loan Slip");
		// for (Loan LOAN : completedList) 
			// ui.display(LOAN.toString());
		for (Loan loan : completedList) { 		//curly brackets were add to be accordance with style guidelines
			ui.display(loan.toString()); 		//variable name LOAN was changed to loan
		}
			
		
		ui.setState(BorrowBookUI.UIState.COMPLETED);
		state = ControlState.COMPLETED;
	}

	
	public void cancel() {
		ui.setState(BorrowBookUI.UIState.CANCELLED);
		state = ControlState.CANCELLED;
	}
	
	
}
