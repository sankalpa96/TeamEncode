package library.returnBook;
import library.entities.Book;
import library.entities.Library;
import library.entities.Loan;

// Change 1 - change the class name and file name
// Change 2 - change variable Ui and parameter uI to ui
// Change 3 - change enum name and enum variable name

// public class rETURN_bOOK_cONTROL{
public class ReturnBookControl{

	//private ReturnBookUI Ui;
	private ReturnBookUI ui;	//change Ui to ui
	//private enum cOnTrOl_sTaTe { INITIALISED, READY, INSPECTING };
	private enum ControlState { INITIALISED, READY, INSPECTING };	//change cOnTrOl_sTaTe to ControlState
	//private cOnTrOl_sTaTe sTaTe;
	private ControlState state;	//change cOnTrOl_sTaTe to ControlState and sTaTe to state
	
	private Library lIbRaRy;
	private Loan CurrENT_loan;
	

	public rETURN_bOOK_cONTROL() {
		this.lIbRaRy = Library.GeTiNsTaNcE();
		//sTaTe = cOnTrOl_sTaTe.INITIALISED;
		state = ControlState.INITIALISED;		//change cOnTrOl_sTaTe to ControlState and sTaTe to state
	}
	
	//public void sEt_uI(ReturnBookUI uI) {
	public void sEt_uI(ReturnBookUI ui) {		//change uI to ui
		if (!state.equals(ControlState.INITIALISED)) 	//change cOnTrOl_sTaTe to ControlState and sTaTe to state
			throw new RuntimeException("ReturnBookControl: cannot call setUI except in INITIALISED state");
		
		//this.Ui = uI;
		this.ui = ui;		//change Ui and uI to ui
		//uI.sEt_sTaTe(ReturnBookUI.uI_sTaTe.READY);
		ui.sEt_sTaTe(ReturnBookUI.uI_sTaTe.READY);	//change uI to ui
		//sTaTe = cOnTrOl_sTaTe.READY;
		state = ControlState.READY;		//change cOnTrOl_sTaTe to ControlState and sTaTe to state
	}


	public void bOoK_sCaNnEd(int bOoK_iD) {
		//if (!sTaTe.equals(cOnTrOl_sTaTe.READY))
		if (!state.equals(ControlState.READY)) 	//change cOnTrOl_sTaTe to ControlState and sTaTe to state
			throw new RuntimeException("ReturnBookControl: cannot call bookScanned except in READY state");
		
		Book cUrReNt_bOoK = lIbRaRy.gEt_BoOk(bOoK_iD);
		
		if (cUrReNt_bOoK == null) {
			//Ui.DiSpLaY("Invalid Book Id");
			ui.DiSpLaY("Invalid Book Id");		//change Ui to ui
			return;
		}
		if (!cUrReNt_bOoK.iS_On_LoAn()) {
			//Ui.DiSpLaY("Book has not been borrowed");
			ui.DiSpLaY("Book has not been borrowed");		//change Ui to ui
			return;
		}		
		CurrENT_loan = lIbRaRy.GeT_LoAn_By_BoOkId(bOoK_iD);	
		double Over_Due_Fine = 0.0;
		if (CurrENT_loan.Is_OvEr_DuE()) 
			Over_Due_Fine = lIbRaRy.CaLcUlAtE_OvEr_DuE_FiNe(CurrENT_loan);
		
		//Ui.DiSpLaY("Inspecting");
		ui.DiSpLaY("Inspecting");				//change Ui to ui
		//Ui.DiSpLaY(cUrReNt_bOoK.toString());
		ui.DiSpLaY(cUrReNt_bOoK.toString());	//change Ui to ui
		//Ui.DiSpLaY(CurrENT_loan.toString());
		ui.DiSpLaY(CurrENT_loan.toString());	//change Ui to ui
		
		if (CurrENT_loan.Is_OvEr_DuE()) 
			//Ui.DiSpLaY(String.format("\nOverdue fine : $%.2f", Over_Due_Fine));
			ui.DiSpLaY(String.format("\nOverdue fine : $%.2f", Over_Due_Fine));	//change Ui to ui
		
		//Ui.sEt_sTaTe(ReturnBookUI.uI_sTaTe.INSPECTING);
		ui.sEt_sTaTe(ReturnBookUI.uI_sTaTe.INSPECTING);		//change Ui to ui
		//sTaTe = cOnTrOl_sTaTe.INSPECTING;
		state = ControlState.INSPECTING;		//change cOnTrOl_sTaTe to ControlState and sTaTe to state
	}


	public void sCaNnInG_cOmPlEtE() {
		//if (!sTaTe.equals(cOnTrOl_sTaTe.READY)) 
		if (!state.equals(ControlState.READY)) 		//change cOnTrOl_sTaTe to ControlState and sTaTe to state
			throw new RuntimeException("ReturnBookControl: cannot call scanningComplete except in READY state");
		
		//Ui.sEt_sTaTe(ReturnBookUI.uI_sTaTe.COMPLETED);		
		ui.sEt_sTaTe(ReturnBookUI.uI_sTaTe.COMPLETED);		//change Ui to ui	
	}


	public void dIsChArGe_lOaN(boolean iS_dAmAgEd) {
		//if (!sTaTe.equals(cOnTrOl_sTaTe.INSPECTING))
		if (!state.equals(ControlState.INSPECTING)) 	//change cOnTrOl_sTaTe to ControlState and sTaTe to state
			throw new RuntimeException("ReturnBookControl: cannot call dischargeLoan except in INSPECTING state");
		
		lIbRaRy.DiScHaRgE_LoAn(CurrENT_loan, iS_dAmAgEd);
		CurrENT_loan = null;
		//Ui.sEt_sTaTe(ReturnBookUI.uI_sTaTe.READY);
		ui.sEt_sTaTe(ReturnBookUI.uI_sTaTe.READY);		//change Ui to ui
		//sTaTe = cOnTrOl_sTaTe.READY;
		state = ControlState.READY;		//change cOnTrOl_sTaTe to ControlState and sTaTe to state
	}


}
