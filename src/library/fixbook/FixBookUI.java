package library.fixbook;
import java.util.Scanner;

// Author : Sankalpa
// Reviewer : Chathura
// Mediator : Poornima	

public class FixBookUI {

	//public static enum uI_sTaTe { INITIALISED, READY, FIXING, COMPLETED };
	public static enum UIState { INITIALISED, READY, FIXING, COMPLETED }; // changed enum name uI_sTaTe to UIState

	//private fIX_bOOK_cONTROL CoNtRoL;
	private FixBookControl control; // changed variable type  fIX_bOOK_cONTROL to FixBookControl and CoNtRoL to control
	//private Scanner InPuT;
	private Scanner input; // changed variable name InPuT to input
	//private uI_sTaTe StAtE;
	private UIState state; // changed enum name uI_sTaTe to UIState

	
	public FixBookUI(FixBookControl control) {
		//this.CoNtRoL = CoNtRoL;
		this.control = control; //changed variable CoNtRoL to control
		//InPuT = new Scanner(System.in);
		input = new Scanner(System.in);
		//StAtE = uI_sTaTe.INITIALISED;
		state = UIState.INITIALISED; // changed enum name uI_sTaTe to UIState
		//control.SeT_Ui(this);
		control.setUI(this);//changed variable CoNtRoL to control
		
	}


	//public void SeT_StAtE(uI_sTaTe state) {
	public void setState(UIState state) { // changed enum name uI_sTaTe to UIState and SeT_StAtE to setState
		this.state = state;
	}

	
	//public void RuN() {
	public void run() { //change method name RuN to run
		//OuTpUt("Fix Book Use Case UI\n");
		output("Fix Book Use Case UI\n"); //changed method name OuTpUt to output
		
		while (true) {
			
			//switch (StAtE) {
			switch (state) { // changed variable name stAtE to state
			
			case READY:
				//String BoOk_EnTrY_StRiNg = iNpUt("Scan Book (<enter> completes): ");
				String bookEntryString = input("Scan Book (<enter> completes): "); // changed variable name BoOk_EnTrY_StRiNg to bookEntryString
				//if (BoOk_EnTrY_StRiNg.length() == 0) 
				if (bookEntryString.length() == 0) {
					//CoNtRoL.SCannING_COMplete();
					control.SCannING_COMplete();
				
				}else {
					try {
						//int BoOk_Id = Integer.valueOf(BoOk_EnTrY_StRiNg).intValue();
						int bookId = Integer.valueOf(bookEntryString).intValue(); // changed variable name BoOk_EnTrY_StRiNg to bookEntryString
						//CoNtRoL.BoOk_ScAnNeD(BoOk_Id);
						control.BoOk_ScAnNeD(bookId);
					}
					catch (NumberFormatException e) {
						//OuTpUt("Invalid bookId");
						output("Invalid bookId"); //changed method name OuTpUt to output
					}
				}
				break;	
				
			case FIXING:
				//String AnS = iNpUt("Fix Book? (Y/N) : ");
				String ans = input("Fix Book? (Y/N) : ");
				boolean FiX = false;
				//if (AnS.toUpperCase().equals("Y")) 
				//if (ans.toUpperCase().equals("Y")) 
					//FiX = true;
				if (ans.toUpperCase().equals("Y")) {
					FiX = true;
				}
				
				//CoNtRoL.FiX_BoOk(FiX);
				control.fixbook(FiX);
				break;
								
			case COMPLETED:
				//OuTpUt("Fixing process complete");
				output("Fixing process complete"); //changed method name OuTpUt to output
				return;
			
			default:
				//OuTpUt("Unhandled state");
				output("Unhandled state"); //changed method name OuTpUt to output
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
		
		
	//private void OuTpUt(Object object) {
	private void output(Object object) { //changed method name OuTpUt to output
		System.out.println(object);
	}
	

	//public void dIsPlAy(Object object) {
	public void display(Object object) { //changed method name dIsPlAy  to display
		//OuTpUt(object);
		output(object); //changed method name OuTpUt to output
	}
	
	
}
