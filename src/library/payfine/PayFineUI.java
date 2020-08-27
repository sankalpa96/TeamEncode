package library.payfine;
import java.util.Scanner;

// Author   : Rovidu
// Reviewer : Sankalpa
// Mediator : Chathura

public class PayFineUI {
//  public static enum uI_sTaTe { INITIALISED, READY, PAYING, COMPLETED, CANCELLED };
    public static enum UiState { INITIALISED, READY, PAYING, COMPLETED, CANCELLED }; // Changed uI_sTaTe to UiState by author

//  private pAY_fINE_cONTROL CoNtRoL;
    private PayFineControl control; // Changed pAY_fINE_cONTROL to PayFineControl & CoNtRoL to control by author

    private Scanner input;

//  private uI_sTaTe StAtE;
    private UiState state; // Changed uI_sTaTe to UiState & StAtE to state by author

//  public PayFineUI(pAY_fINE_cONTROL control) {
    public PayFineUI(PayFineControl control) { // Changed pAY_fINE_cONTROL to PayFineControl by author
//      this.CoNtRoL = control;
        this.control = control; // Changed CoNtRoL to control by author
        input = new Scanner(System.in);
        state = UiState.INITIALISED;
//      control.SeT_uI(this);
        control.setUi(this); // Changed SeT_uI to setUi by author
    }

//  public void SeT_StAtE(uI_sTaTe state) {
    public void setState(UiState state) { // Changed SeT_StAtE to setState & uI_sTaTe to UiState by author
        this.state = state;
    }

//  public void RuN() {
    public void run() { // Changed RuN to run by author
        output("Pay Fine Use Case UI\n");

        while (true) {
            switch (state) {
            case READY:
//              String Mem_Str = input("Swipe member card (press <enter> to cancel): ");
                String memStr = input("Swipe member card (press <enter> to cancel): "); // Changed Mem_Str to memStr by author
//              if (Mem_Str.length() == 0) {
                if (memStr.length() == 0) { // Changed Mem_Str to memStr by author
//                  CoNtRoL.CaNcEl();
                    control.cancel(); // Changed CaNcEl to cancel by author
                    break;
                }
                try {
//                  int Member_ID = Integer.valueOf(Mem_Str).intValue();
                    int mamberId = Integer.valueOf(memStr).intValue(); // Changed Member_ID to mamberId & Mem_Str to memStr by author
//                  CoNtRoL.CaRd_sWiPeD(Member_ID);
                    control.CaRd_sWiPeD(mamberId); // Changed Member_ID to mamberId & Mem_Str to memStr by author
                } catch (NumberFormatException e) {
                    output("Invalid memberId");
                }
                break;

            case PAYING:
//              double AmouNT = 0;
                double amount = 0; // Changed AmouNT to amount by author
//              String Amt_Str = input("Enter amount (<Enter> cancels) : ");
                String amtStr = input("Enter amount (<Enter> cancels) : "); // Changed Amt_Str to amtStr by author
//              if (Amt_Str.length() == 0) {
                if (amtStr.length() == 0) { // Changed Amt_Str to amtStr by author
//                  CoNtRoL.CaNcEl();
                    control.CaNcEl(); // Changed CoNtRoL to control by author
                    break;
                }
                try {
//                  AmouNT = Double.valueOf(Amt_Str).doubleValue();
                    amount = Double.valueOf(amtStr).doubleValue(); // Changed AmouNT to amount & Amt_Str to amtStr by author
                } catch (NumberFormatException e) {}
                
//              if (AmouNT <= 0) {
                if (amount <= 0) { // Changed AmouNT to amount by author
                    output("Amount must be positive");
                    break;
                }
//              CoNtRoL.PaY_FiNe(AmouNT);
                control.PaY_FiNe(amount); // Changed CoNtRoL to control & AmouNT to amount by author
                break;

            case CANCELLED:
                output("Pay Fine process cancelled");
                return;

            case COMPLETED:
                output("Pay Fine process complete");
                return;

            default:
                output("Unhandled state");
                throw new RuntimeException("FixBookUI : unhandled state :" + state);	
            }		
        }		
    }

    private String input(String prompt) {
        System.out.print(prompt);
        return input.nextLine();
    }	

    private void output(Object object) {
        System.out.println(object);
    }	

//  public void DiSplAY(Object object) {
    public void display(Object object) { // Changed DiSplAY to display by author
        output(object);
    }
}
