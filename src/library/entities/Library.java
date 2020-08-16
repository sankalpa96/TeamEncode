
//constant names have been changed to proper format
//Author: Chathura
//Reviewer: Poornima
//Mediator: Rovidu

package library.entities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class Library implements Serializable {
    	
    
	//private static final String lIbRaRyFiLe = "library.obj";
	private static final String LIBRARY_FILE = "library.obj"; 	//Constant name lIbRaRyFiLe was changed to LIBRARY_FILE
	//private static final int lOaNlImIt  = 2;
	private static final int LOAN_LIMIT = 2; 	//Constant name lOaNlImIt  was changed to LOAN_LIMIT
	//private static final int loanPeriod = 2;
	private static final int LOAN_PERIOD = 2; 	//Constant name loanPeriod  was changed to LOAN_PERIOD
	//private static final double FiNe_PeR_DaY = 1.0;
	private static final double FINE_PER_DAY = 1.0; 	//Constant name FiNe_PeR_DaY was changed to FINE_PER_DAY
	//private static final double maxFinesOwed = 1.0;
	private static final double MAX_FINES_OWED = 1.0; 	//Constant name maxFinesOwed was changed to MAX_FINES_OWED
	//private static final double damageFee = 2.0;
	private static final double DAMAGE_FEE = 2.0; 	//Constant name damageFee was changed to DAMAGE_FEE
	
	//private static Library SeLf;
	private static Library self; 	//Variable name SeLf was changed to self
	//private int bOoK_Id;
	private int bookId; 	//Variable name bOoK_Id was changed to bookId
	//private int mEmBeR_Id;
	private int memberId; 	//Variable name mEmBeR_Id was changed to memberId
	//private int lOaN_Id;
	private int loanId; 	//Variable name lOaN_Id was changed to loanId
	//private Date lOaN_DaTe;
	private Date loanDate; 	//Variable name lOaN_DaTe was changed to loanDate
	
	//private Map<Integer, Book> CaTaLoG;
	private Map<Integer, Book> catalog; 	//Variable name CaTaLoG was changed to catalog
	//private Map<Integer, Member> MeMbErS;
	private Map<Integer, Member> members; 	//Variable name MeMbErS was changed to members
	//private Map<Integer, Loan> LoAnS;
	private Map<Integer, Loan> loans; 	//Variable name LoAnS was changed to loans
	//private Map<Integer, Loan> CuRrEnT_loans;
	private Map<Integer, Loan> currentLoans; 	//Variable name CuRrEnT_loans was changed to currentLoans
	//private Map<Integer, Book> DaMaGeD_BoOkS;
	private Map<Integer, Book> damagedBooks; 	//Variable name DaMaGeD_BoOkS was changed to damagedBooks
	
        
	private Library() {
		catalog = new HashMap<>();
		members = new HashMap<>();
		loans = new HashMap<>();
		currentLoans = new HashMap<>();
		damagedBooks = new HashMap<>();
		bookId = 1;
		memberId = 1;		
		loanId = 1;		
	}

	
	//public static synchronized Library GeTiNsTaNcE()
	public static synchronized Library getInstance(){ 	//Method name GeTiNsTaNcE was changed to getInstance		
		if (self == null) {
			Path PATH = Paths.get(LIBRARY_FILE);			
			if (Files.exists(PATH)) {	
				//try (ObjectInputStream LiBrArY_FiLe = new ObjectInputStream(new FileInputStream(LIBRARY_FILE));) {
				try (ObjectInputStream libraryFile = new ObjectInputStream(new FileInputStream(LIBRARY_FILE));) { 		//variable name LiBrArY_FiLe was changed to libraryFile
			    
					//self = (Library) LiBrArY_FiLe.readObject();
					self = (Library) libraryFile.readObject(); 	//variable name LiBrArY_FiLe was changed to libraryFile
					//Calendar.getInstance().SeT_DaTe(self.loanDate);
					Calendar.getInstance().setDate(self.loanDate); 	//Method name SeT_DaTe was changed to setDate
					//LiBrArY_FiLe.close();
					libraryFile.close(); 	////variable name LiBrArY_FiLe was changed to libraryFile
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			else self = new Library();
		}
		return self;
	}
                
	
	//public static synchronized void SaVe() {
	public static synchronized void save() { 	//Method name SaVe was changed to save
		if (self != null) {
			//self.loanDate = Calendar.getInstance().gEt_DaTe();
			self.loanDate = Calendar.getInstance().getDate(); 	//Method name gEt_DaTe was changed to getDate
			try (ObjectOutputStream libraryFile = new ObjectOutputStream(new FileOutputStream(LIBRARY_FILE));) {
				libraryFile.writeObject(self);
				libraryFile.flush();
				libraryFile.close();	
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	
	//public int gEt_BoOkId() {
	public int getBookId() { 	//Method name gEt_BoOkId was changed to getBookId
		return bookId;
	}
	
	
	//public int gEt_memberId() {
	public int getMemberId() { 	//Method name gEt_memberId was changed to getMemberId
		return memberId;
	}
	
	
	//private int gEt_NeXt_bookId() {
	private int getNextBookId() { 	//Method name gEt_NeXt_bookId was changed to getNextBookId
		return bookId++;
	}

	
	//private int gEt_NeXt_memberId() {
	private int getNextMemberId() { 	 //Method name gEt_NeXt_memberId was changed to getNextMemberId
		return memberId++;
	}

	
	//private int gEt_NeXt_loanId() {
	private int getNextLoanId() { 	//Method name gEt_NeXt_loanId was changed to getNextLoanId
		return loanId++;
	}

	
	//public List<Member> lIsT_members() {
	public List<Member> listMembers() { 	//Method name lIsT_members was changed to listMembers
		return new ArrayList<Member>(members.values()); 
	}


	//public List<Book> lIsT_BoOkS() {
	public List<Book> listBooks() { 	//Method name lIsT_BoOkS was changed to listBooks
		return new ArrayList<Book>(catalog.values()); 
	}


	//public List<Loan> lISt_currentLoans() {
	public List<Loan> listCurrentLoans() { 	//Method name lISt_currentLoans was changed to listCurrentLoans
		return new ArrayList<Loan>(currentLoans.values());
	}


	//public Member aDd_MeMbEr(String lastName, String firstName, String email, int phoneNo) {
	public Member addMember(String lastName, String firstName, String email, int phoneNo) { 	//Method name aDd_MeMbEr was changed to addMember
		//Member member = new Member(lastName, firstName, email, phoneNo, gEt_NeXt_memberId());
		int nextMemberId = getNextMemberId();
		Member member = new Member(lastName, firstName, email, phoneNo, nextMemberId); 		//passed method as an argument was assigned to variable before passing
		
		//members.put(member.GeT_ID(), member);
		int idOfMember = member.getId();
		members.put(idOfMember, member); 	//Method name GeT_ID was changed to getId and passed method as an argument was assigned to variable before passing
		return member;
	}

	
	//public Book aDd_BoOk(String a, String t, String c) {
	public Book addBook(String a, String t, String c) { 	//Method name aDd_BoOk was changed to addBook
		//Book b = new Book(a, t, c, getNextBookId());
		NextBookId = getNextBookId();
		Book b = new Book(a, t, c, NextBookId);
		//catalog.put(b.getId(), b);
		int bId = b.getId();
		catalog.put(bId, b); 	//passed method as an argument was assigned to variable before passing
		return b;
	}

	
	//public Member gEt_MeMbEr(int memberId) {
	public Member getMember(int memberId) { 	//Method name gEt_MeMbEr was changed to getMember
		// if (members.containsKey(memberId)) 
			// return members.get(memberId);
		if (members.containsKey(memberId)){
			return members.get(memberId); 	//if conditional statment without curly brackets were modified to with curly brackets
		}
			
		return null;
	}

	
	//public Book gEt_BoOk(int bookId) {
	public Book getBook(int bookId) { 	 	//Method name gEt_BoOk was changed to getBook
		// if (catalog.containsKey(bookId)) 
			// return catalog.get(bookId);		
		if (catalog.containsKey(bookId)){ 	//if conditional statment without curly brackets were modified to with curly brackets 
			return catalog.get(bookId);
		}
				
		return null;
	}

	
	//public int gEt_LoAn_LiMiT() {
	public int getLoanLimit() { 	 	//Method name gEt_LoAn_LiMiT was changed to getLoanLimit
		return LOAN_LIMIT;
	}

	
	//public boolean cAn_MeMbEr_BoRrOw(Member member) {
	public boolean canMemberBorrow(Member member) { 		 //Method name cAn_MeMbEr_BoRrOw was changed to canMemberBorrow
		//if (member.gEt_nUmBeR_Of_currentLoans() == LOAN_LIMIT )
			//return false;
		if (member.getNumberOfCurrentLoans() == LOAN_LIMIT ){ 	//if conditional statment without curly brackets were modified to with curly brackets
			return false;
		} 	 //Method name gEt_nUmBeR_Of_currentLoans was changed to getNumberOfCurrentLoans
			
				
		//if (member.FiNeS_OwEd() >= MAX_FINES_OWED)
			//return false;
		if (member.finesOwed() >= MAX_FINES_OWED){ 		//if conditional statment without curly brackets were modified to with curly brackets
			return false;
		} 		//Method name FiNeS_OwEd was changed to finesOwed
			
				
		//for (Loan loan : member.GeT_loans())
			//if (loan.Is_OvEr_DuE())
				//return false;
		for (Loan loan : member.getLoans()){ 	//Method name GeT_loans was changed to getLoans
			if (loan.isOverDue()){ 		//Method name Is_OvEr_DuE was changed to isOverDue
				return false;
			} 		 //for loop and if conditional statment without curly brackets were modified to with curly brackets
				
		} 		
			
			
			
		return true;
	}

	
	//public int gEt_NuMbEr_Of_loans_ReMaInInG_FoR_MeMbEr(Member MeMbEr) {
	public int getNumberOfLoansRemainingForMember(Member member) { 		//Method name gEt_NuMbEr_Of_loans_ReMaInInG_FoR_MeMbEr was changed to getNumberOfLoansRemainingForMember
		return LOAN_LIMIT - member.getNumberOfCurrentLoans();
	}

	
	//public Loan iSsUe_LoAn(Book book, Member member) {
	public Loan issueLoan(Book book, Member member) { 		//Method name iSsUe_LoAn was changed to issueLoan
		//Date dueDate = Calendar.getInstance().gEt_DuE_DaTe(LOAN_PERIOD);
		Date dueDate = Calendar.getInstance().getDueDate(LOAN_PERIOD); 	//Method name gEt_DuE_DaTe was changed to getDueDate
		//Loan loan = new Loan(gEt_NeXt_loanId(), book, member, dueDate);
		int nextLoanId = getNextLoanId();
		Loan loan = new Loan(nextLoanId, book, member, dueDate); 	//Method name gEt_NeXt_loanId was changed to getNextLoanId and passed method as an argument was assigned to variable before passing
		//member.TaKe_OuT_LoAn(loan);
		member.takeOutLoan(loan); 	//Method name TaKe_OuT_LoAn was changed to takeOutLoan
		//book.BoRrOw();
		book.borrow(); 	//Method name BoRrOw was changed to borrow
		//loans.put(loan.GeT_Id(), loan);
		int idOfLoan = loan.getId();
		loans.put(idOfLoan, loan); 	//Method name GeT_Id was changed to getId and  passed method as an argument was assigned to variable before passing
		
		//currentLoans.put(book.getId(), loan);
		int idOfBook = book.getId();
		currentLoans.put(book.getId(), loan); 	//passed method as an argument was assigned to variable before passing
		return loan;
	}
	
	
	//public Loan GeT_LoAn_By_BoOkId(int bookId) {
	public Loan getLoanByBookId(int bookId) { 	//Method name GeT_LoAn_By_BoOkId was changed to getLoanByBookId
		// if (currentLoans.containsKey(bookId)) 
			// return currentLoans.get(bookId);
		if (currentLoans.containsKey(bookId)){ 		//if conditional statment without curly brackets were modified to with curly brackets
			return currentLoans.get(bookId);
		}
		
		return null;
	}

	
	//public double CaLcUlAtE_OvEr_DuE_FiNe(Loan LoAn) {
	public double calculateOverDueFine(Loan loan) { 		//Method name CaLcUlAtE_OvEr_DuE_FiNe and variable name LoAn were changed to calculateOverDueFine and loan
		if (LoAn.isOverDue()) {
			//long DaYs_OvEr_DuE = Calendar.getInstance().GeT_DaYs_DiFfErEnCe(LoAn.getDueDate());
			Date loanDueDate = loan.getDueDate();  	 	//passed method as an argument was assigned to variable before passing
			long daysOverDue = Calendar.getInstance().getDaysDifference(loanDueDate); 	//Method name GeT_DaYs_DiFfErEnCe and variable name DaYs_OvEr_DuE were changed to getDaysDifference and daysOverDue
			//double fInE = daysOverDue * FINE_PER_DAY;
			double fine = daysOverDue * FINE_PER_DAY; 	//variable fInE was changed to fine
			return fine;
		}
		return 0.0;		
	}


	//public void DiScHaRgE_LoAn(Loan cUrReNt_LoAn, boolean iS_dAmAgEd) {
	public void dischargeLoan(Loan currentLoan, boolean isDamaged) { 	//Method name DiScHaRgE_LoAn and variable names cUrReNt_LoAn and iS_dAmAgEd were changed to dischargeLoan, currentLoan and isDamaged
		//Member mEmBeR = currentLoan.getMember();
		Member member = currentLoan.getMember(); 	//variable name mEmBeR was changed to member
		//Book bOoK  = currentLoan.getBook();
		Book book  = currentLoan.getBook(); 	//variable name bOoK was changed to book
		
		//double oVeR_DuE_FiNe = calculateOverDueFine(currentLoan);
		double overDueFine = calculateOverDueFine(currentLoan); 	//variable name oVeR_DuE_FiNe was changed to overDueFine
		//member.AdD_FiNe(overDueFine);
		member.addFine(overDueFine); 	//Method name AdD_FiNe was changed to addFine
		
		member.dischargeLoan(currentLoan);
		//book.ReTuRn(isDamaged);
		book.returnBook(isDamaged); 	//Method name ReTuRn was changed to returnBook to avoid conflicting with key words
		if (isDamaged) {
			member.addFine(DAMAGE_FEE);
			damagedBooks.put(book.getId(), book);
		}
		//currentLoan.DiScHaRgE();
		currentLoan.discharge(); 	//Method name DiScHaRgE was changed to discharge
		currentLoans.remove(book.getId());
	}


	//public void cHeCk_currentLoans() {
	public void checkCurrentLoans() { 		//Method name cHeCk_currentLoans was changed to checkCurrentLoans
		//for (Loan lOaN : currentLoans.values())
			//loan.cHeCk_OvEr_DuE();
		for (Loan loan : currentLoans.values()){ 		//variable name lOaN was changed to loan
			loan.checkOverDue(); 		//Method name cHeCk_OvEr_DuE was changed to checkOverDue
		}  		//for loop without curly brackets were modified to with curly brackets
	}


	//public void RePaIr_BoOk(Book cUrReNt_BoOk) {
		//if (damagedBooks.containsKey(currentBookId))
	public void repairBook(Book currentBook) { 	//Method name RePaIr_BoOk and variavle name cUrReNt_BoOk were  changed to repairBook and currentBook
		int currentBookId = currentBook.getId();
		if (damagedBooks.containsKey(currentBookId)) { 		//passed method as an argument was assigned to variable before passing
			//currentBook.RePaIr();
			currentBook.repair(); 		//Method name RePaIr was changed to repair
			damagedBooks.remove(currentBookId); 		//passed method as an argument was assigned to variable before passing
		}
		else 
			throw new RuntimeException("Library: repairBook: book is not damaged");
		
		
	}
	
	
}
