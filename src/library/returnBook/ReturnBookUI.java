package library.returnBook;
import java.util.Scanner;

// Change 1 - change enum name uI_sTaTe and enum object name StATe to UiState and state
// Change 2 - change object name CoNtRoL and parameter name cOnTrOL to control
// Change 3 - change function names iNpUt, oUtPuT, Run, DiSpLaY, sEt_sTaTe to input, output, run, display, setState
// Change 4 - change parameter names ObJeCt, PrOmPt to object, prompt
// Change 5 - change bOoK_sCaNnEd, sCaNnInG_cOmPlEtE, dIsChArGe_lOaN from ReturnBookControl class to bookScanned, scanningComplete, dischargeLoan
// Change 6 - change variable name BoOk_InPuT_StRiNg, Book_Id, AnS, Is_DAmAgEd to bookInputString, bookId, ans, isDamaged
// Change 7 - add curly brackets to single line if blocks

public class ReturnBookUI {
	//public static enum uI_sTaTe { INITIALISED, READY, INSPECTING, COMPLETED };
	public static enum UiState { INITIALISED, READY, INSPECTING, COMPLETED };	//change uI_sTaTe
        
    //private rETURN_bOOK_cONTROL CoNtRoL;
	private ReturnBookControl control;		//change rETURN_bOOK_cONTROL, cOnTrOL
	//private uI_sTaTe StATe;
	private UiState state;		//change uI_sTaTe, StATe

	//public ReturnBookUI(rETURN_bOOK_cONTROL cOnTrOL) {
	public ReturnBookUI(ReturnBookControl control) {
        //this.CoNtRoL = cOnTrOL;
		this.control = control;	//change CoNtRoL, cOnTrOL to control
		//iNpUt = new Scanner(System.in);
		input = new Scanner(System.in);	//change iNpUt
		//StATe = uI_sTaTe.INITIALISED;
		state = UiState.INITIALISED;	//change uI_sTaTe, StATe
		//cOnTrOL.sEt_uI(this);
		control.sEt_uI(this);	//change CoNtRoL
	}

	//public void RuN() {	
	public void run() {		//change Run
		//oUtPuT("Return Book Use Case UI\n");
		output("Return Book Use Case UI\n");	//change oUtPuT
		
		while (true) {
			//switch (StATe) {
			switch (state) {    //change StATe to state
			
			case INITIALISED:
				break;
				
			case READY:
				//String BoOk_InPuT_StRiNg = iNpUt("Scan Book (<enter> completes): ");
				String bookInputString = input("Scan Book (<enter> completes): ");	//change BoOk_InPuT_StRiNg, iNpUt
				//if (BoOk_InPuT_StRiNg.length() == 0) 
				if (bookInputString.length() == 0) {	// change BoOk_InPuT_StRiNg and add curly brackets
					//CoNtRoL.sCaNnInG_cOmPlEtE();
					control.scanningComplete();		//change CoNtRoL, sCaNnInG_cOmPlEtE
				}
				else {
					try {
						//int Book_Id = Integer.valueOf(BoOk_InPuT_StRiNg).intValue();
						int bookId = Integer.valueOf(bookInputString).intValue();	//change Book_Id, BoOk_InPuT_StRiNg
						//CoNtRoL.bOoK_sCaNnEd(Book_Id);
						control.bookScanned(bookId);	//change CoNtRoL, bOoK_sCaNnEd, Book_Id
					}
					catch (NumberFormatException e) {
						//oUtPuT("Invalid bookId");
						output("Invalid bookId");		//change oUtPuT
					}					
				}
				break;				
				
			case INSPECTING:
				//String AnS = iNpUt("Is book damaged? (Y/N): ");
				String ans = input("Is book damaged? (Y/N): ");		//change AnS, iNpUt
				//boolean Is_DAmAgEd = false;
				boolean isDamaged = false;		//change Is_DAmAgEd
				//if (AnS.toUpperCase().equals("Y"))
				if (ans.toUpperCase().equals("Y")) {		//change AnS and add curly brackets	
					//Is_DAmAgEd = true;
					isDamaged = true;		//change Is_DAmAgEd
				}
				//CoNtRoL.dIsChArGe_lOaN(Is_DAmAgEd);
				control.dischargeLoan(isDamaged);	//change CoNtRoL, Is_DAmAgEd, dIsChArGe_lOaN
			
			case COMPLETED:
				//oUtPuT("Return processing complete");
				output("Return processing complete");	//change oUtPuT
				return;
			
			default:
				//oUtPuT("Unhandled state");
				output("Unhandled state");		//change oUtPuT
                //throw new RuntimeException("ReturnBookUI : unhandled state :" + StATe);	
				throw new RuntimeException("ReturnBookUI : unhandled state :" + state);     //change StATe
			}
		}
	}

	//private String iNpUt(String PrOmPt) {
	private String input(String prompt) {	//change iNpUt, PrOmPt
		//System.out.print(PrOmPt);
		System.out.print(PrOmPt);		//change PrOmPt
		//return iNpUt.nextLine();
		return input.nextLine();		//change iNpUt
	}	
		
	//private void oUtPuT(Object ObJeCt) 	
	private void output(Object object) {	//change oUtPuT, ObJeCt
		//System.out.println(ObJeCt);
		System.out.println(object);		//change ObJeCt
	}
	
	//public void DiSpLaY(Object object) {		
	public void display(Object object) {	//change DiSpLaY
		//oUtPuT(object);
		output(object);		//change oUtPuT
	}
	
	//public void sEt_sTaTe(uI_sTaTe state) {
	public void setState(UiState state) {		//change uI_sTaTe, sEt_sTaTe
		//this.StATe = state;
		this.state = state;		//change StATe

	
}
