package library.returnBook;
import library.entities.Book;
import library.entities.Library;
import library.entities.Loan;

// Author - Poornima
// Reviewer - Rovidu
// Mediator - Sankalpa

// Change 1 - change the class name and file name
// Change 2 - change both ReturnBookUI type object Ui and parameter uI to ui
// Change 3 - change enum name and enum variable name
// Change 4 - change Library and Loan object names
// Change 5 - change constructor name rETURN_bOOK_cONTROL and function names GeTiNsTaNcE, sEt_uI
// Change 6 - change sEt_sTaTe function and uI_sTaTe enum from ReturnBookUI class
// Change 7 - change function name bOoK_sCaNnEd, parameter bOoK_iD and object name cUrReNt_bOoK
//			- change function name gEt_BoOk, DiSpLaY, iS_On_LoAn, GeT_LoAn_By_BoOkId, Is_OvEr_DuE
//			- change variable name Over_Due_Fine
// Change 8 - change function names CaLcUlAtE_OvEr_DuE_FiNe, sCaNnInG_cOmPlEtE, dIsChArGe_lOaN
//			- change parameter iS_dAmAgEd


// public class rETURN_bOOK_cONTROL{
public class ReturnBookControl{

	//private ReturnBookUI Ui;
	private ReturnBookUI ui;	//change Ui to ui
	//private enum cOnTrOl_sTaTe { INITIALISED, READY, INSPECTING };
	private enum ControlState { INITIALISED, READY, INSPECTING };	//change cOnTrOl_sTaTe to ControlState
	//private cOnTrOl_sTaTe sTaTe;
	private ControlState state;	//change cOnTrOl_sTaTe to ControlState and sTaTe to state
	//private Library lIbRaRy;
	private Library library;	//change lIbRaRy to library
	//private Loan CurrENT_loan;
	private Loan currentLoan;	//change CurrENT_loan to currentLoan
	
	//public rETURN_bOOK_cONTROL() {
	public ReturnBookControl() {	// change rETURN_bOOK_cONTROL to ReturnBookControl
		//this.lIbRaRy = Library.GeTiNsTaNcE();
		this.library = Library.getInstance();	//change lIbRaRy, GeTiNsTaNcE to library and getInstance
		//sTaTe = cOnTrOl_sTaTe.INITIALISED;
		state = ControlState.INITIALISED;		//change cOnTrOl_sTaTe to ControlState and sTaTe to state
	}
	
	//public void sEt_uI(ReturnBookUI uI) {
	public void setUI(ReturnBookUI ui) {		//change uI, sEt_uI to ui, setUI
		//if (!sTaTe.equals(cOnTrOl_sTaTe.INITIALISED))
		if (!state.equals(ControlState.INITIALISED)) 	//change cOnTrOl_sTaTe to ControlState and sTaTe to state
			throw new RuntimeException("ReturnBookControl: cannot call setUI except in INITIALISED state");
		
		//this.Ui = uI;
		this.ui = ui;		//change Ui and uI to ui
		//uI.sEt_sTaTe(ReturnBookUI.uI_sTaTe.READY);
		ui.setState(ReturnBookUI.UiState.READY);	//change uI, sEt_sTaTe, uI_sTaTe (enum) to ui, setState, UiState
		//sTaTe = cOnTrOl_sTaTe.READY;
		state = ControlState.READY;		//change cOnTrOl_sTaTe to ControlState and sTaTe to state
	}

	//public void bOoK_sCaNnEd(int bOoK_iD) {
	public void bookScanned(int bookId) {		//change bOoK_sCaNnEd, bOoK_iD to bookScanned, bookId
		//if (!sTaTe.equals(cOnTrOl_sTaTe.READY))
		if (!state.equals(ControlState.READY)) 	//change cOnTrOl_sTaTe to ControlState and sTaTe to state
			throw new RuntimeException("ReturnBookControl: cannot call bookScanned except in READY state");
		
		//Book cUrReNt_bOoK = lIbRaRy.gEt_BoOk(bOoK_iD);
		Book currentBook = library.getBook(bookId);	//change lIbRaRy, cUrReNt_bOoK, gEt_BoOk, bOoK_iD to library, currentBook, getBook bookId
		
		//if (cUrReNt_bOoK == null) {
		if (currentBook == null) {		// change cUrReNt_bOoK to currentBook
			//Ui.DiSpLaY("Invalid Book Id");
			ui.display("Invalid Book Id");		//change Ui to ui, DiSpLaY to display
			return;
		}
		
		//if (!cUrReNt_bOoK.iS_On_LoAn()) {
		if (!currentBook.isOnLoan()) {		// change cUrReNt_bOoK, iS_On_LoAn to currentBook, isOnLoan
			//Ui.DiSpLaY("Book has not been borrowed");
			ui.display("Book has not been borrowed");		//change Ui to ui, DiSpLaY to display
			return;
		}	
		//CurrENT_loan = lIbRaRy.GeT_LoAn_By_BoOkId(bOoK_iD);			
		currentLoan = library.getLoanByBookId(bookId);		//change CurrENT_loan, lIbRaRy, GeT_LoAn_By_BoOkId, bOoK_iD to currentLoan, library, getLoanByBookId, bookId
		//double Over_Due_Fine = 0.0;
		double overdueFine = 0.0;		//change Over_Due_Fine to overdueFine
		//if (CurrENT_loan.Is_OvEr_DuE())
		if (currentLoan.isOverdue()) 	//change CurrENT_loan, Is_OvEr_DuE to currentLoan isOverdue
			//Over_Due_Fine = lIbRaRy.CaLcUlAtE_OvEr_DuE_FiNe(CurrENT_loan);
			overdueFine = library.calculateOverdueFine(currentLoan);	//change CurrENT_loan, Over_Due_Fine, lIbRaRy, CaLcUlAtE_OvEr_DuE_FiNe to currentLoan, overdueFine, library, calculateOverdueFine
		
		//Ui.DiSpLaY("Inspecting");
		ui.display("Inspecting");				//change Ui to ui, DiSpLaY to display
		//Ui.DiSpLaY(cUrReNt_bOoK.toString());
		ui.display(currentBook.toString());	//change Ui to ui, DiSpLaY to display
		//Ui.DiSpLaY(CurrENT_loan.toString());
		ui.display(currentLoan.toString());	//change Ui, CurrENT_loan, DiSpLaY to ui, currentLoan, display
		
		//if (CurrENT_loan.Is_OvEr_DuE()) 
		if (currentLoan.isOverdue()) 	//change CurrENT_loan, Is_OvEr_DuE to currentLoan, isOverdue
			//Ui.DiSpLaY(String.format("\nOverdue fine : $%.2f", Over_Due_Fine));
			ui.display(String.format("\nOverdue fine : $%.2f", overdueFine));	//change Ui to ui, DiSpLaY to display, Over_Due_Fine to overdueFine
		
		//Ui.sEt_sTaTe(ReturnBookUI.uI_sTaTe.INSPECTING);
		ui.setState(ReturnBookUI.UiState.INSPECTING);		//change uI, sEt_sTaTe, uI_sTaTe (enum) to ui, setState, UiState
		//sTaTe = cOnTrOl_sTaTe.INSPECTING;
		state = ControlState.INSPECTING;		//change cOnTrOl_sTaTe to ControlState and sTaTe to state
	}

	//public void sCaNnInG_cOmPlEtE() {
	public void scanningComplete() {	//change sCaNnInG_cOmPlEtE to scanningComplete
		//if (!sTaTe.equals(cOnTrOl_sTaTe.READY)) 
		if (!state.equals(ControlState.READY)) 		//change cOnTrOl_sTaTe to ControlState and sTaTe to state
			throw new RuntimeException("ReturnBookControl: cannot call scanningComplete except in READY state");
		
		//Ui.sEt_sTaTe(ReturnBookUI.uI_sTaTe.COMPLETED);	
		ui.setState(ReturnBookUI.UiState.COMPLETED);		//change uI, sEt_sTaTe, uI_sTaTe (enum) to ui, setState, UiState	
	}

	//public void dIsChArGe_lOaN(boolean iS_dAmAgEd) {
	public void dischargeLoan(boolean isDamaged) {	//change dIsChArGe_lOaN, iS_dAmAgEd to dischargeLoan, isDamaged
		//if (!sTaTe.equals(cOnTrOl_sTaTe.INSPECTING))
		if (!state.equals(ControlState.INSPECTING)) 	//change cOnTrOl_sTaTe to ControlState and sTaTe to state
			throw new RuntimeException("ReturnBookControl: cannot call dischargeLoan except in INSPECTING state");
		
		//lIbRaRy.DiScHaRgE_LoAn(CurrENT_loan, iS_dAmAgEd);
		library.dischargeLoan(currentLoan, isDamaged);		//change lIbRaRy, DiScHaRgE_LoAn, CurrENT_loan, iS_dAmAgEd to library, dischargeLoan, currentLoan, isDamaged
		//CurrENT_loan = null;
		currentLoan = null;	//change CurrENT_loan to currentLoan
		//Ui.sEt_sTaTe(ReturnBookUI.uI_sTaTe.READY);
		ui.setState(ReturnBookUI.UiState.READY);		//change uI, sEt_sTaTe, uI_sTaTe (enum) to ui, setState, UiState
		//sTaTe = cOnTrOl_sTaTe.READY;
		state = ControlState.READY;		//change cOnTrOl_sTaTe to ControlState and sTaTe to state
	}


}
