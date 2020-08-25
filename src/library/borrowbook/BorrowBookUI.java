
//Author: Chathura
//Reviewer: Poornima
//Mediator: Rovidu


package library.borrowbook;
import java.util.Scanner;


public class BorrowBookUI {
	
	//public static enum uI_STaTe { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };
	public static enum UIState { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED }; 	//changed enum name uI_STaTe to UIState

	//private bORROW_bOOK_cONTROL CoNtRoL;
	private BorrowBookControl control; 	//class name bORROW_bOOK_cONTROL and variable name CoNtRoL were changed to BorrowBookControl and control 
	//private Scanner InPuT;
	private Scanner input; 		//variable name InPuT was changed to input
	//private UIState StaTe;
	private UIState state; 		//variable name StaTe was changed to state

	
	public BorrowBookUI(BorrowBookControl control) {
		this.control = control;
		input = new Scanner(System.in);
		state = UIState.INITIALISED;
		//control.SeT_Ui(this);
		control.setUI(this); 	//method name SeT_Ui was changed to setUI
	}

	
	//private String iNpUT(String PrOmPt) {
	private String input(String prompt) { 	//method name iNpUT and parameter variable name PrOmPt were changed to input and prompt
		System.out.print(prompt); 
	}	
		
		
	//private void OuTpUt(Object object) {
	private void output(Object object) { 	//method name OuTpUt and parameter variable name ObJeCt were changed to output and object
		System.out.println(object);
	}
	
			
	//public void SeT_state(UIState state) {
	public void setState(UIState state) { 	//method name SeT_state and parameter variable name StAtE were changed to setState and state 
		this.state = state;
	}

	
	public void RuN() {
		output("Borrow Book Use Case UI\n");
		
		while (true) {
			
			switch (state) {			
			
			case CANCELLED:
				output("Borrowing Cancelled");
				return;

				
			case READY:
				String MEM_STR = input("Swipe member card (press <enter> to cancel): ");
				if (MEM_STR.length() == 0) {
					control.CaNcEl();
					break;
				}
				try {
					int MeMbEr_Id = Integer.valueOf(MEM_STR).intValue();
					control.SwIpEd(MeMbEr_Id);
				}
				catch (NumberFormatException e) {
					output("Invalid Member Id");
				}
				break;

				
			case RESTRICTED:
				input("Press <any key> to cancel");
				control.CaNcEl();
				break;
			
				
			case SCANNING:
				String BoOk_StRiNg_input = input("Scan Book (<enter> completes): ");
				if (BoOk_StRiNg_input.length() == 0) {
					control.CoMpLeTe();
					break;
				}
				try {
					int BiD = Integer.valueOf(BoOk_StRiNg_input).intValue();
					control.ScAnNeD(BiD);
					
				} catch (NumberFormatException e) {
					output("Invalid Book Id");
				} 
				break;
					
				
			case FINALISING:
				String AnS = input("Commit loans? (Y/N): ");
				if (AnS.toUpperCase().equals("N")) {
					control.CaNcEl();
					
				} else {
					control.CoMmIt_LoAnS();
					input("Press <any key> to complete ");
				}
				break;
				
				
			case COMPLETED:
				output("Borrowing Completed");
				return;
	
				
			default:
				output("Unhandled state");
				throw new RuntimeException("BorrowBookUI : unhandled state :" + state);			
			}
		}		
	}


	public void DiSpLaY(Object object) {
		output(object);		
	}


}
