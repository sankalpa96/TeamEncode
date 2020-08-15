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

	
	public int GeT_ID() {
		return memberId;
	}

	
	public List<Loan> GeT_LoAnS() {
		return new ArrayList<Loan>(currentLoans.values());
	}

	
	public int gEt_nUmBeR_Of_CuRrEnT_LoAnS() {
		return currentLoans.size();
	}

	
	public double FiNeS_OwEd() {
		return finesOwing;
	}

	
	//public void TaKe_OuT_LoAn(Loan lOaN) {
	public void TaKe_OuT_LoAn(Loan loan) { //changing LoAn to loan 
		if (!currentLoans.containsKey(loan.GeT_Id())) //changing LoAn to loan
			currentLoans.put(loan.GeT_Id(), loan); //changing LoAn to loan
		
		else 
			throw new RuntimeException("Duplicate loan added to member");
				
	}

	
	public String GeT_lastName() {
		return lastName;
	}

	
	public String GeT_FiRsT_NaMe() {
		return firstName;
	}


	public void AdD_FiNe(double fine) {
		finesOwing += fine;
	}
	
	public double PaY_FiNe(double AmOuNt) {
		if (AmOuNt < 0) 
			throw new RuntimeException("Member.payFine: amount must be positive");
		
		double change = 0;
		if (AmOuNt > finesOwing) {
			change = AmOuNt - finesOwing;
			finesOwing = 0;
		}
		else 
			finesOwing -= AmOuNt;
		
		return change;
	}


	//public void dIsChArGeLoAn(Loan LoAn) {
	public void dIsChArGeLoAn(Loan loan) { //changing LoAn to loan
		if (currentLoans.containsKey(loan.GeT_Id())) //changing LoAn to loan
			currentLoans.remove(loan.GeT_Id()); //changing LoAn to loan
		
		else 
			throw new RuntimeException("No such loan held by member");
				
	}

}
