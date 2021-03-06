package library;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import library.borrowbook.BorrowBookUI;
//import library.borrowbook.bORROW_bOOK_cONTROL;
import library.borrowbook.BorrowBookControl;	//bORROW_bOOK_cONTROL to BorrowBookControl
import library.entities.Book;
import library.entities.Calendar;
import library.entities.Library;
import library.entities.Loan;
import library.entities.Member;
import library.fixbook.FixBookUI;
//import library.fixbook.fIX_bOOK_cONTROL; 
import library.fixbook.FixBookControl; 	//fIX_bOOK_cONTROL to FixBookControl
import library.payfine.PayFineUI;
//import library.payfine.pAY_fINE_cONTROL;
import library.payfine.PayFineControl;	//pAY_fINE_cONTROL to PayFineControl
import library.returnBook.ReturnBookUI;
//import library.returnBook.rETURN_bOOK_cONTROL;
import library.returnBook.ReturnBookControl;	//rETURN_bOOK_cONTROL to ReturnBookControl

// Author - Poornima
// Reviewer - Rovidu
// Mediator - Sankalpa

//Change 1 - Change variables IN, LIB, MENU, CAL, SDF to in, lib, menu, cal, sdf
//Change 2 - Change function names Get_menu to getMenu abd GeTiNsTaNcE, gEtInStAnCe to getInstance
//Change 3 - Change function names lIsT_MeMbErS, lIsT_BoOkS, gEt_DaTe to listMembers, listBooks, getDate
//Change 4 - Change function names ADD_MEMBER, ADD_BOOK, FIX_BOOKS to addMember, addBook, fixBooks
//			 Change BORROW_BOOK, RETURN_BOOK, LIST_CURRENT_LOANS to borrowBook, returnBook, listCurrentLoans
//			 Change PAY_FINES, INCREMENT_DATE to payFines, incrementDate
//Change 5 - Change function names lISt_CuRrEnT_LoAnS, lIsT_BoOkS, RuN to listCurrentLoans, listBooks, run
//			 Change object class names pAY_fINE_cONTROL, bORROW_bOOK_cONTROL to PayFineControl BorrowBookControl
//			 Change object class names rETURN_bOOK_cONTROL, fIX_bOOK_cONTROL to ReturnBookControl, FixBookControl
//Change 6 - Change cHeCk_CuRrEnT_LoAnS, aDd_BoOk, aDd_MeMbEr to checkCurrentLoans, addBook, addMember
//			 Change variables AuThOr, TiTlE, CaLl_NuMbEr to author, title, callNumber
//			 Change variables LaSt_NaMe, FiRsT_NaMe, EmAiL_AdDrEsS, PhOnE_NuMbEr to lastName, firstName, emailAddress, phoneNumber
//			 Change Book object name BoOk to book and Member object name MeMbEr to member
public class Main {
	
	//private static Scanner IN;
	private static Scanner in;		//change IN to in
	//private static Library LIB;
	private static Library lib;		//change LIB to lib
	//private static String MENU;
	private static String menu;		//change MENU to menu
	//private static Calendar CAL;
	private static Calendar cal;	//change CAL to cal
	//private static SimpleDateFormat SDF;
	private static SimpleDateFormat sdf;	//change SDF to sdf
	
	//private static String Get_menu() {
	private static String getMenu() {		//Change Get_menu to getMenu
		StringBuilder sb = new StringBuilder();
		
		sb.append("\nLibrary Main Menu\n\n")
		  .append("  M  : add member\n")
		  .append("  LM : list members\n")
		  .append("\n")
		  .append("  B  : add book\n")
		  .append("  LB : list books\n")
		  .append("  FB : fix books\n")
		  .append("\n")
		  .append("  L  : take out a loan\n")
		  .append("  R  : return a loan\n")
		  .append("  LL : list loans\n")
		  .append("\n")
		  .append("  P  : pay fine\n")
		  .append("\n")
		  .append("  T  : increment date\n")
		  .append("  Q  : quit\n")
		  .append("\n")
		  .append("Choice : ");
		  
		return sb.toString();
	}


	public static void main(String[] args) {		
		try {			
			//IN = new Scanner(System.in);
			in = new Scanner(System.in);		//change IN to in
			//LIB = Library.GeTiNsTaNcE();
			lib = Library.getInstance();		//change LIB to lib, GeTiNsTaNcE to getInstance
			//CAL = Calendar.gEtInStAnCe();
			cal = Calendar.getInstance();		//change CAL to cal, gEtInStAnCe to getInstance
			//SDF = new SimpleDateFormat("dd/MM/yyyy");
			sdf = new SimpleDateFormat("dd/MM/yyyy");	//change SDF to sdf
	
			//for (Member m : LIB.lIsT_MeMbErS()) {
			for (Member m : lib.listMembers()) {	//chamge LIB, lIsT_MeMbErS to lib, listMembers
				output(m);
			}
			output(" ");
			//for (Book b : LIB.lIsT_BoOkS()) {
			for (Book b : lib.listBooks()) {		//change LIB, lIsT_BoOkS to lib, listBooks
				output(b);
			}
			
			//MENU = Get_menu();			
			menu = getMenu();		//change MENU, Get_menu to menu, getMenu
			
			boolean e = false;
			
			while (!e) {
				
				//output("\n" + SDF.format(CAL.gEt_DaTe()));
				output("\n" + sdf.format(cal.getDate()));		//change SDF, CAL, gEt_DaTe
				//String c = input(MENU);
				String c = input(menu);		//chaange MENU to menu
				
				switch (c.toUpperCase()) {
				
				case "M": 
					//ADD_MEMBER();
					addMember();		//change ADD_MEMBER to addMember
					break;
					
				case "LM": 
					//LIST_MEMBERS();
					listMembers();		//change LIST_MEMBERS to listMembers
					break;
					
				case "B": 
					//ADD_BOOK();
					addBook();			//change ADD_BOOK to addBook
					break;
					
				case "LB": 
					//LIST_BOOKS();
					listBooks();		//change LIST_BOOKS to listBooks
					break;
					
				case "FB": 
					//FIX_BOOKS();
					fixBooks();			//change FIX_BOOKS to fixBooks
					break;
					
				case "L": 
					//BORROW_BOOK();
					borrowBook();		//change BORROW_BOOK to borrowBook
					break;
					
				case "R": 
					//RETURN_BOOK();
					returnBook();		//change RETURN_BOOK to returnBook
					break;
					
				case "LL": 
					//LIST_CURRENT_LOANS();
					listCurrentLoans();	//change LIST_CURRENT_LOANS to listCurrentLoans
					break;
					
				case "P": 
					//PAY_FINES();
					payFines();		//change PAY_FINES to payFines
					break;
					
				case "T": 
					//INCREMENT_DATE();
					incrementDate();		//change INCREMENT_DATE to incrementDate
					break;
					
				case "Q": 
					e = true;
					break;
					
				default: 
					output("\nInvalid option\n");
					break;
				}
				
				Library.SaVe();
			}			
		} catch (RuntimeException e) {
			output(e);
		}		
		output("\nEnded\n");
	}	

	//private static void PAY_FINES() {	private static void payFines() {		//change PAY_FINES to payFines
		//new PayFineUI(new pAY_fINE_cONTROL()).RuN();
		new PayFineUI(new PayFineControl()).RuN();		//change pAY_fINE_cONTROL to PayFineControl	
	}


	//private static void LIST_CURRENT_LOANS() {
	private static void listCurrentLoans() {		//change LIST_CURRENT_LOANS tp listCurrentLoans
		output("");
		//for (Loan loan : LIB.lISt_CuRrEnT_LoAnS()) {
		for (Loan loan : lib.listCurrentLoans()) {	//change LIB, lISt_CuRrEnT_LoAnS to lib, listCurrentLoans
			output(loan + "\n");
		}		
	}


	//private static void LIST_BOOKS() {
	private static void listBooks() {		//change LIST_BOOKS to listBooks
		output("");
		//for (Book book : LIB.lIsT_BoOkS()) {
		for (Book book : lib.listBooks()) {	//chane LIB, lIsT_BoOkS to lib, listBooks
			output(book + "\n");
		}		
	}


	//private static void LIST_MEMBERS() {
	private static void listMembers() { 		//change LIST_MEMBERS to listMembers
		output("");
		//for (Member member : LIB.lIsT_MeMbErS()) {
		for (Member member : lib.listMembers()) {		//change LIB, lIsT_MeMbErS to lib, listMembers
			output(member + "\n");
		}		
	}



	//private static void BORROW_BOOK() {
	private static void borrowBook() {		//change BORROW_BOOK to borrowBook
		//new BorrowBookUI(new bORROW_bOOK_cONTROL()).RuN();
		new BorrowBookUI(new BorrowBookControl()).run();		//change bORROW_bOOK_cONTROL to BorrowBookControl
	}


	//private static void RETURN_BOOK() {
	private static void returnBook() {		//change RETURN_BOOK to returnBook
		//new ReturnBookUI(new rETURN_bOOK_cONTROL()).RuN();	
		new ReturnBookUI(new ReturnBookControl()).run();	//change rETURN_bOOK_cONTROL to ReturnBookControl		
	}

	//private static void FIX_BOOKS() {
	private static void fixBooks() {		//change FIX_BOOKS to fixBooks
		//new FixBookUI(new fIX_bOOK_cONTROL()).run();
		new FixBookUI(new FixBookControl()).run();		//change fIX_bOOK_cONTROL to FixBookControl
	}

	//private static void INCREMENT_DATE() {
	private static void incrementDate() {		//change INCREMENT_DATE to incrementDate
		try {
			int days = Integer.valueOf(input("Enter number of days: ")).intValue();
			//CAL.incrementDate(days);
			cal.incrementDate(days);		//change CAL
			//LIB.cHeCk_CuRrEnT_LoAnS();
			lib.checkCurrentLoans();		//change LIB, cHeCk_CuRrEnT_LoAnS to lib, checkCurrentLoans
			//output(SDF.format(CAL.gEt_DaTe()));
			output(sdf.format(cal.getDate()));		//change SDF, CAL, gEt_DaTe
			
		} catch (NumberFormatException e) {
			 output("\nInvalid number of days\n");
		}
	}

	//private static void ADD_BOOK() {
	private static void addBook() {		//change ADD_BOOK to addBook
		
		//String AuThOr = input("Enter author: ");	
		String author = input("Enter author: ");	//change AuThOr to author
		//String TiTlE  = input("Enter title: ");
		String title  = input("Enter title: ");		//change TiTlE to title
		//String CaLl_NuMbEr = input("Enter call number: ");
		String callNumber = input("Enter call number: ");	//change CaLl_NuMbEr to callNumber
		//Book BoOk = LIB.aDd_BoOk(AuThOr, TiTlE, CaLl_NuMbEr);
		Book book = lib.addBook(author, title, callNumber);	//change LIB, BoOk, author, title, callNumber, aDd_BoOk
		//output("\n" + BoOk + "\n");
		output("\n" + book + "\n");		//change BoOk to book
		
	}

	//private static void ADD_MEMBER() {
	private static void addMember() {		//change ADD_MEMBER to addMember
		try {
			//String LaSt_NaMe = input("Enter last name: ");
			String lastName = input("Enter last name: ");		//change LaSt_NaMe to lastName
			//String FiRsT_NaMe  = input("Enter first name: ");
			String firstName  = input("Enter first name: ");	//change FiRsT_NaMe to firstName
			//String EmAiL_AdDrEsS = input("Enter email address: ");
			String emailAddress = input("Enter email address: ");	//change EmAiL_AdDrEsS to emailAddress
			//int PhOnE_NuMbEr = Integer.valueOf(input("Enter phone number: ")).intValue();
			int phoneNumber = Integer.valueOf(input("Enter phone number: ")).intValue();	//change Ph to phoneNumber
			//Member MeMbEr = LIB.aDd_MeMbEr(LaSt_NaMe, FiRsT_NaMe, EmAiL_AdDrEsS, PhOnE_NuMbEr);
			Member member = lib.addMember(lastName, firstName, emailAddress, phoneNumber); 	//change LIB, aDd_MeMbEr, LaSt_NaMe, FiRsT_NaMe, EmAiL_AdDrEsS, PhOnE_NuMbEr
			//output("\n" + MeMbEr + "\n");
			output("\n" + member + "\n");	//change MeMbEr to member
			
		} catch (NumberFormatException e) {
			 output("\nInvalid phone number\n");
		}
		
	}


	private static String input(String prompt) {
		System.out.print(prompt);
		//return IN.nextLine();
		return in.nextLine();	//change IN to in
	}
	
	
	
	private static void output(Object object) {
		System.out.println(object);
	}

	
}
