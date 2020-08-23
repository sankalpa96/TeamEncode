package library.fixbook;
import library.entities.Book;
import library.entities.Library;

// Author : Sankalpa
// Reviewer : Chathura
// Mediator : Poornima	

//public class fIX_bOOK_cONTROL {
public class FixBookControl { //changed class name fIX_bOOK_cONTROL to FixBookControl
	
	private FixBookUI Ui;
	//private enum CoNtRoL_StAtE { INITIALISED, READY, FIXING };
	private enum controlState{ INITIALISED, READY, FIXING }; // changedd enum name CoNtRoL_StAtE to controlState
	//private CoNtRoL_StAtE StAtE;
	private controlState state; //changed variable StAtE to state
	
	//private Library LiBrArY;
	private Library library; //changed variable LiBrArY to library
	
	//private Book CuRrEnT_BoOk;
	private Book currentBook; //changed variable CuRrEnT_BoOk to currentBook


	public FixBookControl() {
		//this.LiBrArY = Library.GeTiNsTaNcE();
		this.library = Library.GeTiNsTaNcE(); //changed variable LiBrArY to library
		//StAtE = CoNtRoL_StAtE.INITIALISED;
		state = controlState.INITIALISED; //changed variable StAtE to state
	}
	
	
	//public void SeT_Ui(FixBookUI ui) {
	public void setUI(FixBookUI ui) { // changed method name SeT_Ui to setUI
		//if (!StAtE.equals(CoNtRoL_StAtE.INITIALISED))
		if (!state.equals(controlState.INITIALISED)) {//changed variable StAtE to state
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
		}
			
			
		this.Ui = ui;
		//ui.SeT_StAtE(FixBookUI.uI_sTaTe.READY);
		ui.setState(FixBookUI.uiState.READY); 
		//StAtE = CoNtRoL_StAtE.READY;	
		state = controlState.READY;	//changed variable StAtE to state		
	}


	//public void BoOk_ScAnNeD(int BoOkId) {
	public void bookScanned(int BoOkId) { //changed method name BoOk_ScAnNeD to bookScanned	
		//if (!StAtE.equals(CoNtRoL_StAtE.READY))
		if (!state.equals(controlState.READY)) { //changed variable StAtE to state
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}
		
		//CuRrEnT_BoOk = LiBrArY.gEt_BoOk(BoOkId);
		currentBook = library.getBook(BoOkId); //changed variable LiBrArY to library and CuRrEnT_BoOk to currentBook
		
		//if (CuRrEnT_BoOk == null) {
		if (currentBook == null) { //changed variable CuRrEnT_BoOk to currentBook
			//Ui.dIsPlAy("Invalid bookId");
			Ui.display("Invalid bookId"); //changed method name dIsPlAy to display
			return;
		}
		//if (!CuRrEnT_BoOk.iS_DaMaGeD()) {
		if (!currentBook.isDamagerd()) { //changed variable CuRrEnT_BoOk to currentBook and iS_DaMaGeD to isDamagerd
			//Ui.dIsPlAy("Book has not been damaged");
			Ui.display("Book has not been damaged");
			return;
		}
		
		//Ui.dIsPlAy(CuRrEnT_BoOk.toString());
		Ui.display(currentBook.toString()); //changed variable CuRrEnT_BoOk to currentBook
		
		//Ui.SeT_StAtE(FixBookUI.uI_sTaTe.FIXING);
		Ui.setState(FixBookUI.uiState.FIXING); //changed method name SeT_StAtE to setState
		//StAtE = CoNtRoL_StAtE.FIXING;		
		state = controlState.FIXING; //changed variable StAtE to state		
	}


	//public void FiX_BoOk(boolean mUsT_FiX) {
	public void fixBook(boolean mustFix) { //changed method name FiX_BoOk to fixBook and mUsT_FiX to mustFix
		//if (!StAtE.equals(CoNtRoL_StAtE.FIXING))
        if (!state.equals(controlState.FIXING)) {	//changed variable StAtE to state		
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
		}
		
		//if (mUsT_FiX) 
		if (mustFix){ //changed variable mUsT_FiX to mustFix
			//LiBrArY.RePaIr_BoOk(CuRrEnT_BoOk);
			library.repairBook(currentBook); //changed variable LiBrArY to library
		}
		
		//CuRrEnT_BoOk = null;
		currentBook = null; //changed variable CuRrEnT_BoOk to currentBook
		//Ui.SeT_StAtE(FixBookUI.uI_sTaTe.READY);
		Ui.setState(FixBookUI.uiState.READY); //changed method name SeT_StAtE to setState
		//StAtE = CoNtRoL_StAtE.READY;
		state = controlState.READY;//changed variable StAtE to state
	}

	
	//public void SCannING_COMplete() {
		public void scanningComplete() { //changed SCannING_COMplete to scanningComplete
		//if (!StAtE.equals(CoNtRoL_StAtE.READY))
        if (!state.equals(controlState.READY))			
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
			
		//Ui.SeT_StAtE(FixBookUI.uI_sTaTe.COMPLETED);
		Ui.setState(FixBookUI.uiState.COMPLETED);	
	}

}
