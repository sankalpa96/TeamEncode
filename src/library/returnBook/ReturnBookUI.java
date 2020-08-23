package library.returnBook;
import java.util.Scanner;

// Change 1 - change enum name uI_sTaTe and enum object name StATe to UiState and state
// Change 2 - change object name CoNtRoL and parameter name cOnTrOLto control

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
		iNpUt = new Scanner(System.in);
		//StATe = uI_sTaTe.INITIALISED;
		state = UiState.INITIALISED;	//change uI_sTaTe, StATe
		//cOnTrOL.sEt_uI(this);
		control.sEt_uI(this);	//change CoNtRoL
	}


	public void RuN() {		
		oUtPuT("Return Book Use Case UI\n");
		
		while (true) {
			//switch (StATe) {
			switch (state) {    //change StATe to state
			
			case INITIALISED:
				break;
				
			case READY:
				String BoOk_InPuT_StRiNg = iNpUt("Scan Book (<enter> completes): ");
				if (BoOk_InPuT_StRiNg.length() == 0) 
					//CoNtRoL.sCaNnInG_cOmPlEtE();
					control.sCaNnInG_cOmPlEtE();		//change CoNtRoL
				
				else {
					try {
						int Book_Id = Integer.valueOf(BoOk_InPuT_StRiNg).intValue();
						//CoNtRoL.bOoK_sCaNnEd(Book_Id);
						control.bOoK_sCaNnEd(Book_Id);	//change CoNtRoL
					}
					catch (NumberFormatException e) {
						oUtPuT("Invalid bookId");
					}					
				}
				break;				
				
			case INSPECTING:
				String AnS = iNpUt("Is book damaged? (Y/N): ");
				boolean Is_DAmAgEd = false;
				if (AnS.toUpperCase().equals("Y")) 					
					Is_DAmAgEd = true;
				
				//CoNtRoL.dIsChArGe_lOaN(Is_DAmAgEd);
				control.dIsChArGe_lOaN(Is_DAmAgEd);	//change CoNtRoL
			
			case COMPLETED:
				oUtPuT("Return processing complete");
				return;
			
			default:
				oUtPuT("Unhandled state");
                //throw new RuntimeException("ReturnBookUI : unhandled state :" + StATe);	
				throw new RuntimeException("ReturnBookUI : unhandled state :" + state);     //change StATe
			}
		}
	}

	
	private String iNpUt(String PrOmPt) {
		System.out.print(PrOmPt);
		return iNpUt.nextLine();
	}	
		
		
	private void oUtPuT(Object ObJeCt) {
		System.out.println(ObJeCt);
	}
	
			
	public void DiSpLaY(Object object) {
		oUtPuT(object);
	}
	
	//public void sEt_sTaTe(uI_sTaTe state) {
	public void sEt_sTaTe(UiState state) {		//change uI_sTaTe
		//this.StATe = state;
		this.state = state;		//change StATe

	
}
