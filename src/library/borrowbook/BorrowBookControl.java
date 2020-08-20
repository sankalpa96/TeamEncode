package library.borrowbook;
import java.util.ArrayList;
import java.util.List;

import library.entities.Book;
import library.entities.Library;
import library.entities.Loan;
import library.entities.Member;

//public class bORROW_bOOK_cONTROL {
public class BorrowBookControl { 	//class name bORROW_bOOK_cONTROL was changed to BorrowBookControl
	
	private BorrowBookUI uI;
	
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
	

	//public void SeT_uI(BorrowBookUI uI) {
	public void setUI(BorrowBookUI uI) { 	//method name SeT_uI and variable name Ui were changed to setUI and uI
		// if (!state.equals(ControlState.INITIALISED)) 
			// throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
		if (!state.equals(ControlState.INITIALISED)) { 		//curly brackets were added to be accordance with style guidelines
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
		}
			
		this.uI = uI;
		//uI.SeT_StAtE(BorrowBookUI.uI_STaTe.READY);
		uI.setState(BorrowBookUI.UIState.READY); 	//method name SeT_StAtE and enum name uI_STaTe were changed to setState and UIState
		state = ControlState.READY;		
	}

		
	//public void SwIpEd(int member_Id) {
	public void swiped(int MemberId) { 	//method name SwIpEd and parameter variable name member_Id was chaned to swiped and MemberId
		// if (!state.equals(ControlState.READY)) 
			// throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
		if (!state.equals(ControlState.READY)){ 	//curly brackets were added to be accordance with style guidelines
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
		}
			


		//member = library.gEt_MeMbEr(MemberId);
		member = library.getMember(MemberId); 	//method name gEt_MeMbEr was changed to getMember
		if (member == null) {
			//uI.DiSpLaY("Invalid memberId");
			uI.display("Invalid memberId"); 	//method name DiSpLaY was changed to display
			return;
		}
		//if (library.cAn_MeMbEr_BoRrOw(member)) {
		if (library.canMemberBorrow(member)) { 	//method name cAn_MeMbEr_BoRrOw was changed to canMemberBorrow
			pendingList = new ArrayList<>();
			uI.setState(BorrowBookUI.UIState.SCANNING);
			state = ControlState.SCANNING; 
		}
		else {
			uI.display("Member cannot borrow at this time");
			uI.setState(BorrowBookUI.UIState.RESTRICTED); 
		}
	}
	
	
	public void ScAnNeD(int bookiD) {
		book = null;
		if (!state.equals(ControlState.SCANNING)) 
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
			
		book = library.gEt_BoOk(bookiD);
		if (book == null) {
			uI.display("Invalid bookId");
			return;
		}
		if (!book.iS_AvAiLaBlE()) {
			uI.display("Book cannot be borrowed");
			return;
		}
		pendingList.add(book);
		for (Book B : pendingList) 
			uI.display(B.toString());
		
		if (library.gEt_NuMbEr_Of_LoAnS_ReMaInInG_FoR_MeMbEr(member) - pendingList.size() == 0) {
			uI.display("Loan limit reached");
			CoMpLeTe();
		}
	}
	
	
	public void CoMpLeTe() {
		if (pendingList.size() == 0) 
			CaNcEl();
		
		else {
			uI.display("\nFinal Borrowing List");
			for (Book book : pendingList) 
				uI.display(book.toString());
			
			completedList = new ArrayList<Loan>();
			uI.setState(BorrowBookUI.UIState.FINALISING);
			state = ControlState.FINALISING;
		}
	}


	public void CoMmIt_LoAnS() {
		if (!state.equals(ControlState.FINALISING)) 
			throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
			
		for (Book B : pendingList) {
			Loan lOaN = library.iSsUe_LoAn(B, member);
			completedList.add(lOaN);			
		}
		uI.display("Completed Loan Slip");
		for (Loan LOAN : completedList) 
			uI.display(LOAN.toString());
		
		uI.setState(BorrowBookUI.UIState.COMPLETED);
		state = ControlState.COMPLETED;
	}

	
	public void CaNcEl() {
		uI.setState(BorrowBookUI.UIState.CANCELLED);
		state = ControlState.CANCELLED;
	}
	
	
}