package library;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import library.borrowbook.BorrowBookUI;
import library.borrowbook.bORROW_bOOK_cONTROL;
import library.entities.Book;
import library.entities.Calendar;
import library.entities.Library;
import library.entities.Loan;
import library.entities.Member;
import library.fixbook.FixBookUI;
import library.fixbook.fIX_bOOK_cONTROL;
import library.payfine.PayFineUI;
import library.payfine.pAY_fINE_cONTROL;
import library.returnBook.ReturnBookUI;
import library.returnBook.rETURN_bOOK_cONTROL;

// Author - Poornima
// Reviewer - Rovidu
// Mediator - Sankalpa

//Change 1 - Change variables IN, LIB, MENU, CAL, SDF to in, lib, menu, cal, sdf

public class Main {
	
	//private static Scanner IN;
	private static Scanner in;		//change IN
	//private static Library LIB;
	private static Library lib;		//change LIB
	//private static String MENU;
	private static String menu;		//change MENU
	//private static Calendar CAL;
	private static Calendar cal;	//change CAL
	//private static SimpleDateFormat SDF;
	private static SimpleDateFormat sdf;	//change SDF
	
	
	private static String Get_menu() {
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
			in = new Scanner(System.in);		//change IN
			//LIB = Library.GeTiNsTaNcE();
			lib = Library.GeTiNsTaNcE();		//change LIB
			//CAL = Calendar.gEtInStAnCe();
			cal = Calendar.gEtInStAnCe();		//change CAL
			//SDF = new SimpleDateFormat("dd/MM/yyyy");
			sdf = new SimpleDateFormat("dd/MM/yyyy");	//change SDF
	
			//for (Member m : LIB.lIsT_MeMbErS()) {
			for (Member m : lib.lIsT_MeMbErS()) {	//chamge LIB
				output(m);
			}
			output(" ");
			//for (Book b : LIB.lIsT_BoOkS()) {
			for (Book b : lib.lIsT_BoOkS()) {		//change LIB
				output(b);
			}
			
			//MENU = Get_menu();			
			menu = Get_menu();		//change MENU
			
			boolean e = false;
			
			while (!e) {
				
				//output("\n" + SDF.format(CAL.gEt_DaTe()));
				output("\n" + sdf.format(cal.gEt_DaTe()));		//change SDF, CAL
				//String c = input(MENU);
				String c = input(menu);		//chaange MENU
				
				switch (c.toUpperCase()) {
				
				case "M": 
					ADD_MEMBER();
					break;
					
				case "LM": 
					LIST_MEMBERS();
					break;
					
				case "B": 
					ADD_BOOK();
					break;
					
				case "LB": 
					LIST_BOOKS();
					break;
					
				case "FB": 
					FIX_BOOKS();
					break;
					
				case "L": 
					BORROW_BOOK();
					break;
					
				case "R": 
					RETURN_BOOK();
					break;
					
				case "LL": 
					LIST_CURRENT_LOANS();
					break;
					
				case "P": 
					PAY_FINES();
					break;
					
				case "T": 
					INCREMENT_DATE();
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

		private static void PAY_FINES() {
		new PayFineUI(new pAY_fINE_cONTROL()).RuN();		
	}


	private static void LIST_CURRENT_LOANS() {
		output("");
		//for (Loan loan : LIB.lISt_CuRrEnT_LoAnS()) {
		for (Loan loan : lib.lISt_CuRrEnT_LoAnS()) {	//change LIB
			output(loan + "\n");
		}		
	}



	private static void LIST_BOOKS() {
		output("");
		//for (Book book : LIB.lIsT_BoOkS()) {
		for (Book book : lib.lIsT_BoOkS()) {	//chane LIB
			output(book + "\n");
		}		
	}



	private static void LIST_MEMBERS() {
		output("");
		//for (Member member : LIB.lIsT_MeMbErS()) {
		for (Member member : lib.lIsT_MeMbErS()) {		//change LIB
			output(member + "\n");
		}		
	}



	private static void BORROW_BOOK() {
		new BorrowBookUI(new bORROW_bOOK_cONTROL()).RuN();		
	}


	private static void RETURN_BOOK() {
		new ReturnBookUI(new rETURN_bOOK_cONTROL()).RuN();		
	}


	private static void FIX_BOOKS() {
		new FixBookUI(new fIX_bOOK_cONTROL()).RuN();		
	}


	private static void INCREMENT_DATE() {
		try {
			int days = Integer.valueOf(input("Enter number of days: ")).intValue();
			//CAL.incrementDate(days);
			cal.incrementDate(days);		//change CAL
			//LIB.cHeCk_CuRrEnT_LoAnS();
			lib.cHeCk_CuRrEnT_LoAnS();		//change LIB
			//output(SDF.format(CAL.gEt_DaTe()));
			output(sdf.format(cal.gEt_DaTe()));		//change SDF, CAL
			
		} catch (NumberFormatException e) {
			 output("\nInvalid number of days\n");
		}
	}


	private static void ADD_BOOK() {
		
		String AuThOr = input("Enter author: ");
		String TiTlE  = input("Enter title: ");
		String CaLl_NuMbEr = input("Enter call number: ");
		//Book BoOk = LIB.aDd_BoOk(AuThOr, TiTlE, CaLl_NuMbEr);
		Book BoOk = lib.aDd_BoOk(AuThOr, TiTlE, CaLl_NuMbEr);	//change LIB
		output("\n" + BoOk + "\n");
		
	}

	
	private static void ADD_MEMBER() {
		try {
			String LaSt_NaMe = input("Enter last name: ");
			String FiRsT_NaMe  = input("Enter first name: ");
			String EmAiL_AdDrEsS = input("Enter email address: ");
			int PhOnE_NuMbEr = Integer.valueOf(input("Enter phone number: ")).intValue();
			//Member MeMbEr = LIB.aDd_MeMbEr(LaSt_NaMe, FiRsT_NaMe, EmAiL_AdDrEsS, PhOnE_NuMbEr);
			Member MeMbEr = lib.aDd_MeMbEr(LaSt_NaMe, FiRsT_NaMe, EmAiL_AdDrEsS, PhOnE_NuMbEr); 	//change LIB
			output("\n" + MeMbEr + "\n");
			
		} catch (NumberFormatException e) {
			 output("\nInvalid phone number\n");
		}
		
	}


	private static String input(String prompt) {
		System.out.print(prompt);
		//return IN.nextLine();
		return in.nextLine();	//change IN
	}
	
	
	
	private static void output(Object object) {
		System.out.println(object);
	}

	
}
