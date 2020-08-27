package library.fixbook;
import java.util.Scanner;

// Author : Sankalpa
// Reviewer : Chathura
// Mediator : Poornima	

public class FixBookUI {

	//public static enum uI_sTaTe { INITIALISED, READY, FIXING, COMPLETED };
	public static enum uiState { INITIALISED, READY, FIXING, COMPLETED }; // changed enum name uI_sTaTe to uiState

	//private fIX_bOOK_cONTROL CoNtRoL;
	private FixBookControl control; // changed variable type  fIX_bOOK_cONTROL to FixBookControl and CoNtRoL to control
	//private Scanner InPuT;
	private Scanner input; // changed variable name InPuT to input
	//private uI_sTaTe StAtE;
	private uiState state; // changed enum name uI_sTaTe to uiState

	
	public FixBookUI(FixBookControl control) {
		//this.CoNtRoL = CoNtRoL;
		this.control = control; //changed variable CoNtRoL to control
		//InPuT = new Scanner(System.in);
		input = new Scanner(System.in);
		//StAtE = uI_sTaTe.INITIALISED;
		state = uiState.INITIALISED; // changed enum name uI_sTaTe to uiState
		control.SeT_Ui(this);//changed variable CoNtRoL to control
	}


	//public void SeT_StAtE(uI_sTaTe state) {
	public void SeT_StAtE(uiState state) { // changed enum name uI_sTaTe to uiState
		this.state = state;
	}

	
	public void RuN() {
		OuTpUt("Fix Book Use Case UI\n");
		
		while (true) {
			
			//switch (StAtE) {
			switch (state) { // changed variable name stAtE to state
			
			case READY:
				//String BoOk_EnTrY_StRiNg = iNpUt("Scan Book (<enter> completes): ");
				String bookEntryString = input("Scan Book (<enter> completes): "); // changed variable name BoOk_EnTrY_StRiNg to bookEntryString
				//if (BoOk_EnTrY_StRiNg.length() == 0) 
				if (bookEntryString.length() == 0) 
					//CoNtRoL.SCannING_COMplete();
					control.SCannING_COMplete();
				
				else {
					try {
						//int BoOk_Id = Integer.valueOf(BoOk_EnTrY_StRiNg).intValue();
						int bookId = Integer.valueOf(bookEntryString).intValue(); // changed variable name BoOk_EnTrY_StRiNg to bookEntryString
						//CoNtRoL.BoOk_ScAnNeD(BoOk_Id);
						control.BoOk_ScAnNeD(bookId);
					}
					catch (NumberFormatException e) {
						OuTpUt("Invalid bookId");
					}
				}
				break;	
				
			case FIXING:
				//String AnS = iNpUt("Fix Book? (Y/N) : ");
				String ans = input("Fix Book? (Y/N) : ");
				boolean FiX = false;
				//if (AnS.toUpperCase().equals("Y")) 
				if (ans.toUpperCase().equals("Y")) 
					FiX = true;
				
				//CoNtRoL.FiX_BoOk(FiX);
				control.FiX_BoOk(FiX);
				break;
								
			case COMPLETED:
				OuTpUt("Fixing process complete");
				return;
			
			default:
				OuTpUt("Unhandled state");
				//throw new RuntimeException("FixBookUI : unhandled state :" + StAtE);		
				throw new RuntimeException("FixBookUI : unhandled state :" + state); // changed variable name stAtE to state		
			
			}		
		}
		
	}

	
	//private String iNpUt(String prompt) {
	private String input(String prompt) { 
		System.out.print(prompt);
		//return InPuT.nextLine();
		return input.nextLine();
	}	
		
		
	private void OuTpUt(Object object) {
		System.out.println(object);
	}
	

	public void dIsPlAy(Object object) {
		OuTpUt(object);
	}
	
	
}
