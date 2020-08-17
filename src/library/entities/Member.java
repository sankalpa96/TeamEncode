package library.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Author : Sankalpa
// Reviewer : Chathura
// Mediator : Poornima	

@SuppressWarnings("serial")
public class Member implements Serializable {
	
	//private String LaSt_NaMe;
	private String lastName; //variable name LaSt_NaMe was changed to lastName	
	
	//private String FiRsT_NaMe;
	private String firstName; //variable name FiRsT_NaMe was changed to lastName
	
	//private String EmAiL_AdDrEsS;
	private String emailAddress; //variable name EmAiL_AdDrEsS was changed to emailAddress
	
	//private int PhOnE_NuMbEr;
	private int phoneNumber; //variable name PhOnE_NuMbEr was changed to phoneNumber
	
	//private int MeMbEr_Id;
	private int memberId; //variable name MeMbEr_Id was changed to memberId
	
	//private double FiNeS_OwInG;
	private double finesOwing; //variable name FiNeS_OwInG was changed to finesOwing
	
	//private Map<Integer, Loan> cUrReNt_lOaNs;
	private Map<Integer, Loan> currentLoans; //variable name cUrReNt_lOaNs was changed to currentLoans

	
	public Member(String lastName, String firstName, String emailAddress, int phoneNumber, int memberId) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
		this.memberId = memberId;
		
		this.currentLoans = new HashMap<>();
	}

	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		// recorrected indentation
		sb.append("Member:  ").append(memberId).append("\n")
		  .append("  Name:  ").append(lastName).append(", ").append(firstName).append("\n")
		  .append("  Email: ").append(emailAddress).append("\n")
		  .append("  Phone: ").append(phoneNumber)
		  .append("\n")
		  .append(String.format("  Fines Owed :  $%.2f", finesOwing))
		  .append("\n");
		
		//for (Loan LoAn : currentLoans.values()) {
		for (Loan loan : currentLoans.values()) { // changing LoAn to loan
			sb.append(loan).append("\n"); // changing LoAn to loan
		}		  
		return sb.toString();
	}

	
	//public int GeT_ID() {
	public int getId() { //changing method name GeT_ID to getId
		return memberId;
	}

	
	//public List<Loan> GeT_LoAnS() {
	public List<Loan> getLoans() { //changing method name GeT_LoAnS to getLoans
		return new ArrayList<Loan>(currentLoans.values());
	}

	
	//public int gEt_nUmBeR_Of_CuRrEnT_LoAnS() {
	public int getNumberOfCurrentLoans() { //changing method name gEt_nUmBeR_Of_CuRrEnT_LoAnS to getNumberOfCurrentLoans
		return currentLoans.size();
	}

	
	//public double FiNeS_OwEd() { 
	public double finesOwned() { //changing method name FiNeS_OwEd to finesOwned
		return finesOwing;
	}

	
	//public void TaKe_OuT_LoAn(Loan lOaN) {
	public void takeOutLoan(Loan loan) { //changing lOaN to loan and method name to takeOutLoan
		// adding single consistent bracketing style 
		if (!currentLoans.containsKey(loan.getId())){ //changing lOaN to loan
			currentLoans.put(loan.getId(), loan); //changing lOaN to loan
		} else {
			throw new RuntimeException("Duplicate loan added to member");
		}
				
	}

	
	//public String GeT_lastName() {
	public String getLastName() { //changing method name GeT_lastName to getLastName
		return lastName;
	}

	
	//public String GeT_FiRsT_NaMe() {
	public String getFirstName() { //changing method name GeT_FiRsT_NaMe to getFirstName
		return firstName;
	}


	//public void AdD_FiNe(double fine) {
	public void addFine(double fine) { //changing method name AdD_FiNe to addFine
		finesOwing += fine;
	}
	
	//public double PaY_FiNe(double AmOuNt) {
	public double payFine(double amount) { //changing method name PaY_FiNe to payFine and variable name to amount
		// adding single consistent bracketing style 
		if (amount < 0) {
			throw new RuntimeException("Member.payFine: amount must be positive");
		}
		
		double change = 0;
		// adding single consistent bracketing style 
		if (amount > finesOwing) {
			change = amount - finesOwing;
			finesOwing = 0;
		} else {
			finesOwing -= amount;
		}
		
		return change;
	}


	//public void dIsChArGeLoAn(Loan LoAn) {
	public void dischargeLoan(Loan loan) { //changing LoAn to loan and method name to dischargeLoan
		// adding single consistent bracketing style 
		if (currentLoans.containsKey(loan.getId())) { //changing LoAn to loan
			currentLoans.remove(loan.getId()); //changing LoAn to loan
		} else {
			throw new RuntimeException("No such loan held by member");
		}
				
	}

}
