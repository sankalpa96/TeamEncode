package library.fixbook;
import library.entities.Book;
import library.entities.Library;

//public class fIX_bOOK_cONTROL {
public class FixBookControl { //changed class name fIX_bOOK_cONTROL to FixBookControl
	
	private FixBookUI Ui;
	//private enum CoNtRoL_StAtE { INITIALISED, READY, FIXING };
	private enum controlState{ INITIALISED, READY, FIXING }; // changedd enum name CoNtRoL_StAtE to controlState
	//private CoNtRoL_StAtE StAtE;
	private controlState state; //changed variable StAtE to state
	
	//private Library LiBrArY;
	private Library library; //changed variable LiBrArY to library
	private Book CuRrEnT_BoOk;


	public FixBookControl() {
		//this.LiBrArY = Library.GeTiNsTaNcE();
		this.library = Library.GeTiNsTaNcE(); //changed variable LiBrArY to library
		//StAtE = CoNtRoL_StAtE.INITIALISED;
		state = controlState.INITIALISED; //changed variable StAtE to state
	}
	
	
	public void SeT_Ui(FixBookUI ui) {
		//if (!StAtE.equals(CoNtRoL_StAtE.INITIALISED))
		if (!state.equals(controlState.INITIALISED)) //changed variable StAtE to state
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
			
		this.Ui = ui;
		ui.SeT_StAtE(FixBookUI.uI_sTaTe.READY);
		//StAtE = CoNtRoL_StAtE.READY;	
		state = controlState.READY;	//changed variable StAtE to state		
	}


	public void BoOk_ScAnNeD(int BoOkId) {
		//if (!StAtE.equals(CoNtRoL_StAtE.READY))
		if (!state.equals(controlState.READY)) //changed variable StAtE to state
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
			
		//CuRrEnT_BoOk = LiBrArY.gEt_BoOk(BoOkId);
		CuRrEnT_BoOk = library.gEt_BoOk(BoOkId); //changed variable LiBrArY to library
		
		if (CuRrEnT_BoOk == null) {
			Ui.dIsPlAy("Invalid bookId");
			return;
		}
		if (!CuRrEnT_BoOk.iS_DaMaGeD()) {
			Ui.dIsPlAy("Book has not been damaged");
			return;
		}
		Ui.dIsPlAy(CuRrEnT_BoOk.toString());
		Ui.SeT_StAtE(FixBookUI.uI_sTaTe.FIXING);
		//StAtE = CoNtRoL_StAtE.FIXING;		
		state = controlState.FIXING; //changed variable StAtE to state		
	}


	public void FiX_BoOk(boolean mUsT_FiX) {
		//if (!StAtE.equals(CoNtRoL_StAtE.FIXING))
        if (!state.equals(controlState.FIXING))	//changed variable StAtE to state		
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
			
		if (mUsT_FiX) 
			//LiBrArY.RePaIr_BoOk(CuRrEnT_BoOk);
			library.RePaIr_BoOk(CuRrEnT_BoOk); //changed variable LiBrArY to library
		
		CuRrEnT_BoOk = null;
		Ui.SeT_StAtE(FixBookUI.uI_sTaTe.READY);
		//StAtE = CoNtRoL_StAtE.READY;
		state = controlState.READY;//changed variable StAtE to state
	}

	
	public void SCannING_COMplete() {
		//if (!StAtE.equals(CoNtRoL_StAtE.READY))
        if (!StAtE.equals(controlState.READY))			
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
			
		Ui.SeT_StAtE(FixBookUI.uI_sTaTe.COMPLETED);		
	}

}
