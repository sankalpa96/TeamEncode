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
	private ControlState state; 	//constant name sTaTe was changed to STATE
	
	//private List<Book> pEnDiNg_LiSt;
	private List<Book> pendingList; 	//variable name pEnDiNg_LiSt was changed to pendingList
	//private List<Loan> cOmPlEtEd_LiSt;
	private List<Loan> completedList; 	//variable name cOmPlEtEd_LiSt was changed to completedList
	//private Book bOoK;
	private Book book; 	//variable name bOoK was changed to book
	
	
	public BorrowBookControl() {
		this.library = Library.GeTiNsTaNcE();
		state = ControlState.INITIALISED;
	}
	

	public void SeT_Ui(BorrowBookUI Ui) {
		if (!state.equals(ControlState.INITIALISED)) 
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
			
		this.uI = Ui;
		Ui.SeT_StAtE(BorrowBookUI.uI_STaTe.READY);
		state = ControlState.READY;		
	}

		
	public void SwIpEd(int member_Id) {
		if (!state.equals(ControlState.READY)) 
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
			
		member = library.gEt_MeMbEr(member_Id);
		if (member == null) {
			uI.DiSpLaY("Invalid memberId");
			return;
		}
		if (library.cAn_MeMbEr_BoRrOw(member)) {
			pendingList = new ArrayList<>();
			uI.SeT_StAtE(BorrowBookUI.uI_STaTe.SCANNING);
			state = ControlState.SCANNING; 
		}
		else {
			uI.DiSpLaY("Member cannot borrow at this time");
			uI.SeT_StAtE(BorrowBookUI.uI_STaTe.RESTRICTED); 
		}
	}
	
	
	public void ScAnNeD(int bookiD) {
		book = null;
		if (!state.equals(ControlState.SCANNING)) 
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
			
		book = library.gEt_BoOk(bookiD);
		if (book == null) {
			uI.DiSpLaY("Invalid bookId");
			return;
		}
		if (!book.iS_AvAiLaBlE()) {
			uI.DiSpLaY("Book cannot be borrowed");
			return;
		}
		pendingList.add(book);
		for (Book B : pendingList) 
			uI.DiSpLaY(B.toString());
		
		if (library.gEt_NuMbEr_Of_LoAnS_ReMaInInG_FoR_MeMbEr(member) - pendingList.size() == 0) {
			uI.DiSpLaY("Loan limit reached");
			CoMpLeTe();
		}
	}
	
	
	public void CoMpLeTe() {
		if (pendingList.size() == 0) 
			CaNcEl();
		
		else {
			uI.DiSpLaY("\nFinal Borrowing List");
			for (Book book : pendingList) 
				uI.DiSpLaY(book.toString());
			
			completedList = new ArrayList<Loan>();
			uI.SeT_StAtE(BorrowBookUI.uI_STaTe.FINALISING);
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
		uI.DiSpLaY("Completed Loan Slip");
		for (Loan LOAN : completedList) 
			uI.DiSpLaY(LOAN.toString());
		
		uI.SeT_StAtE(BorrowBookUI.uI_STaTe.COMPLETED);
		state = ControlState.COMPLETED;
	}

	
	public void CaNcEl() {
		uI.SeT_StAtE(BorrowBookUI.uI_STaTe.CANCELLED);
		state = ControlState.CANCELLED;
	}
	
	
}
