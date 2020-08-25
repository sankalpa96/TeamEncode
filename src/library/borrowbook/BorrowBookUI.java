
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

	
	//public void RuN() {
	public void run() { 	//method name RuN was changed to run
		output("Borrow Book Use Case UI\n");
		
		while (true) {
			
			switch (state) {			
			
			case CANCELLED:
				output("Borrowing Cancelled");
				return;

				
			case READY:
				//String MEM_STR = input("Swipe member card (press <enter> to cancel): ");
				String memStr = input("Swipe member card (press <enter> to cancel): "); 	//variable MEM_STR was changed to memStr
				if (memStr.length() == 0) {
					//control.CaNcEl();
					control.cancel(); 	//method name CaNcEl was changed to cancel
					break;
				}
				try {
					//int MeMbEr_Id = Integer.valueOf(memStr).intValue();
					int memberId = Integer.valueOf(memStr).intValue(); 	//variable name MeMbEr_Id was changed to memberId
					//control.SwIpEd(memberId);
					control.swiped(memberId); 	//method name SwIpEd was changed to swiped
				}
				catch (NumberFormatException e) {
					output("Invalid Member Id");
				}
				break;

				
			case RESTRICTED:
				input("Press <any key> to cancel");
				control.cancel();
				break;
			
				
			case SCANNING:
				//String bookStringInput = input("Scan Book (<enter> completes): ");
				String bookStringInput = input("Scan Book (<enter> completes): "); 	//variable name bookStringInput was changed to bookStringInput
				if (bookStringInput.length() == 0) {
					//control.CoMpLeTe();
					control.complete(); 	//method name CoMpLeTe was changed to complete
					break;
				}
				try {
					//int BiD = Integer.valueOf(bookStringInput).intValue();
					int bId = Integer.valueOf(bookStringInput).intValue(); 	//variable name BiD was changed to bId
					//control.ScAnNeD(bId);
					control.scanned(bId); 	//method name ScAnNeD was changed to scanned
					
				} catch (NumberFormatException e) {
					output("Invalid Book Id");
				} 
				break;
					
				
			case FINALISING:
				//String AnS = input("Commit loans? (Y/N): ");
				String ans = input("Commit loans? (Y/N): "); 	//variable name AnS was changed to ans
				if (ans.toUpperCase().equals("N")) {
					control.cancel();
					
				} else {
					//control.CoMmIt_Loans();
					control.commitLoans(); 	//method name CoMmIt_Loans was changed to commitLoans
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


	//public void DiSpLaY(Object object) {
	public void display(Object object) { 	//method name DiSpLaY was changed to display
		output(object);		
	}


}
