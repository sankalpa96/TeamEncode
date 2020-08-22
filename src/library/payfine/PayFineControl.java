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
	private ControlState state; // Changed cOnTrOl_sTaTe to ControlState & StAtE to state
	
//	private Library LiBrArY;
	private Library library; // Changed LiBrArY to library by author
//	private Member MeMbEr;
	private Member member; // Changed MeMbEr to member by author


//	public pAY_fINE_cONTROL() {
	public PayFineControl() { // Changed pAY_fINE_cONTROL to PayFineControl by author
		this.library = Library.GeTiNsTaNcE();
//		StAtE = cOnTrOl_sTaTe.INITIALISED;
		state = ControlState.INITIALISED; // Changed StAtE to state & cOnTrOl_sTaTe to ControlState by author
	}
	
	
//	public void SeT_uI(PayFineUI uI) {
	public void setUi(PayFineUI ui) { // Changed SeT_uI to setUi & uI to ui by author
//		if (!StAtE.equals(cOnTrOl_sTaTe.INITIALISED)) {
		if (!state.equals(ControlState.INITIALISED)) { // Changed StAtE to state & cOnTrOl_sTaTe to ControlState by author
			throw new RuntimeException("PayFineControl: cannot call setUI except in INITIALISED state");
		}	
		this.ui = ui;
		ui.SeT_StAtE(PayFineUI.uI_sTaTe.READY);
//		StAtE = cOnTrOl_sTaTe.READY;
		state = ControlState.READY; // Changed StAtE to state & cOnTrOl_sTaTe to ControlState by author
	}


//	public void CaRd_sWiPeD(int member_Id) {
	public void cardSwiped(int mamberId) { // Changed CaRd_sWiPeD to cardSwiped & member_Id to mamberId by author
//		if (!StAtE.equals(cOnTrOl_sTaTe.READY)) 
		if (!state.equals(ControlState.READY)) // Changed StAtE to state & cOnTrOl_sTaTe to ControlState by author
			throw new RuntimeException("PayFineControl: cannot call cardSwiped except in READY state");
			
//		member = library.gEt_member(member_Id);
		member = library.gEt_member(mamberId); // Changed member_Id to mamberId by author
		
		if (member == null) {
			ui.DiSplAY("Invalid Member Id");
			return;
		}
		ui.DiSplAY(member.toString());
		ui.SeT_StAtE(PayFineUI.uI_sTaTe.PAYING);
//		StAtE = cOnTrOl_sTaTe.PAYING;
		state = ControlState.PAYING; // Changed StAtE to state & cOnTrOl_sTaTe to ControlState by author
	}
	
	
//	public void CaNcEl() {
	public void cancel() { // Changed CaNcEl to cancel by author
		ui.SeT_StAtE(PayFineUI.uI_sTaTe.CANCELLED);
//		StAtE = cOnTrOl_sTaTe.CANCELLED;
		state = ControlState.CANCELLED; // Changed StAtE to state & cOnTrOl_sTaTe to ControlState by author
	}


//	public double PaY_FiNe(double AmOuNt) {
	public double payFine(double amount) { // Changed PaY_FiNe to payFine & AmOuNt to amount by author
//		if (!StAtE.equals(cOnTrOl_sTaTe.PAYING)) 
		if (!state.equals(ControlState.PAYING)) // Changed StAtE to state & cOnTrOl_sTaTe to ControlState by author
			throw new RuntimeException("PayFineControl: cannot call payFine except in PAYING state");
			
//		double ChAnGe = member.PaY_FiNe(AmOuNt);
		double ChAnGe = member.PaY_FiNe(amount); // Changed AmOuNt to amount by author
		if (ChAnGe > 0) 
			ui.DiSplAY(String.format("Change: $%.2f", ChAnGe));
		
		ui.DiSplAY(member.toString());
		ui.SeT_StAtE(PayFineUI.uI_sTaTe.COMPLETED);
//		StAtE = cOnTrOl_sTaTe.COMPLETED; 
		state = ControlState.COMPLETED;  // Changed StAtE to state & cOnTrOl_sTaTe to ControlState by author
		return ChAnGe;
	}
	


}
