package library.payfine;
import library.entities.Library;
import library.entities.Member;

// Author   : Rovidu
// Reviewer : Sankalpa
// Mediator : Chathura

//public class pAY_fINE_cONTROL {
public class PayFineControl { // Renamed class name
	
//	private PayFineUI Ui;
	private PayFineUI ui; // Changed Ui to ui by author
//	private enum cOnTrOl_sTaTe { INITIALISED, READY, PAYING, COMPLETED, CANCELLED };
	private enum ControlState { INITIALISED, READY, PAYING, COMPLETED, CANCELLED }; // Changed enum cOnTrOl_sTaTe to ControlState by author
//	private cOnTrOl_sTaTe StAtE;
	private ControlState StAtE; // Changed cOnTrOl_sTaTe to ControlState
	
//	private Library LiBrArY;
	private Library library; // Changed LiBrArY to library by author
//	private Member MeMbEr;
	private Member member; // Changed MeMbEr to member by author


	public pAY_fINE_cONTROL() {
		this.library = Library.GeTiNsTaNcE();
		StAtE = cOnTrOl_sTaTe.INITIALISED;
	}
	
	
	public void SeT_uI(PayFineUI uI) {
		if (!StAtE.equals(cOnTrOl_sTaTe.INITIALISED)) {
			throw new RuntimeException("PayFineControl: cannot call setUI except in INITIALISED state");
		}	
		this.ui = uI;
		uI.SeT_StAtE(PayFineUI.uI_sTaTe.READY);
		StAtE = cOnTrOl_sTaTe.READY;		
	}


	public void CaRd_sWiPeD(int member_Id) {
		if (!StAtE.equals(cOnTrOl_sTaTe.READY)) 
			throw new RuntimeException("PayFineControl: cannot call cardSwiped except in READY state");
			
		member = library.gEt_member(member_Id);
		
		if (member == null) {
			ui.DiSplAY("Invalid Member Id");
			return;
		}
		ui.DiSplAY(member.toString());
		ui.SeT_StAtE(PayFineUI.uI_sTaTe.PAYING);
		StAtE = cOnTrOl_sTaTe.PAYING;
	}
	
	
	public void CaNcEl() {
		ui.SeT_StAtE(PayFineUI.uI_sTaTe.CANCELLED);
		StAtE = cOnTrOl_sTaTe.CANCELLED;
	}


	public double PaY_FiNe(double AmOuNt) {
		if (!StAtE.equals(cOnTrOl_sTaTe.PAYING)) 
			throw new RuntimeException("PayFineControl: cannot call payFine except in PAYING state");
			
		double ChAnGe = member.PaY_FiNe(AmOuNt);
		if (ChAnGe > 0) 
			ui.DiSplAY(String.format("Change: $%.2f", ChAnGe));
		
		ui.DiSplAY(member.toString());
		ui.SeT_StAtE(PayFineUI.uI_sTaTe.COMPLETED);
		StAtE = cOnTrOl_sTaTe.COMPLETED;
		return ChAnGe;
	}
	


}
