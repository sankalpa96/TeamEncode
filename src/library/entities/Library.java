
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
	
	private Map<Integer, Book> CaTaLoG;
	private Map<Integer, Member> MeMbErS;
	private Map<Integer, Loan> LoAnS;
	private Map<Integer, Loan> CuRrEnT_LoAnS;
	private Map<Integer, Book> DaMaGeD_BoOkS;
	

	private Library() {
		CaTaLoG = new HashMap<>();
		MeMbErS = new HashMap<>();
		LoAnS = new HashMap<>();
		CuRrEnT_LoAnS = new HashMap<>();
		DaMaGeD_BoOkS = new HashMap<>();
		bookId = 1;
		memberId = 1;		
		loanId = 1;		
	}

	
	public static synchronized Library GeTiNsTaNcE() {		
		if (self == null) {
			Path PATH = Paths.get(LIBRARY_FILE);			
			if (Files.exists(PATH)) {	
				try (ObjectInputStream LiBrArY_FiLe = new ObjectInputStream(new FileInputStream(LIBRARY_FILE));) {
			    
					self = (Library) LiBrArY_FiLe.readObject();
					Calendar.gEtInStAnCe().SeT_DaTe(self.loanDate);
					LiBrArY_FiLe.close();
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			else self = new Library();
		}
		return self;
	}

	
	public static synchronized void SaVe() {
		if (self != null) {
			self.loanDate = Calendar.gEtInStAnCe().gEt_DaTe();
			try (ObjectOutputStream LiBrArY_fIlE = new ObjectOutputStream(new FileOutputStream(LIBRARY_FILE));) {
				LiBrArY_fIlE.writeObject(self);
				LiBrArY_fIlE.flush();
				LiBrArY_fIlE.close();	
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	
	public int gEt_BoOkId() {
		return bookId;
	}
	
	
	public int gEt_memberId() {
		return memberId;
	}
	
	
	private int gEt_NeXt_bookId() {
		return bookId++;
	}

	
	private int gEt_NeXt_memberId() {
		return memberId++;
	}

	
	private int gEt_NeXt_loanId() {
		return loanId++;
	}

	
	public List<Member> lIsT_MeMbErS() {		
		return new ArrayList<Member>(MeMbErS.values()); 
	}


	public List<Book> lIsT_BoOkS() {		
		return new ArrayList<Book>(CaTaLoG.values()); 
	}


	public List<Loan> lISt_CuRrEnT_LoAnS() {
		return new ArrayList<Loan>(CuRrEnT_LoAnS.values());
	}


	public Member aDd_MeMbEr(String lastName, String firstName, String email, int phoneNo) {		
		Member member = new Member(lastName, firstName, email, phoneNo, gEt_NeXt_memberId());
		MeMbErS.put(member.GeT_ID(), member);		
		return member;
	}

	
	public Book aDd_BoOk(String a, String t, String c) {		
		Book b = new Book(a, t, c, gEt_NeXt_bookId());
		CaTaLoG.put(b.gEtId(), b);		
		return b;
	}

	
	public Member gEt_MeMbEr(int memberId) {
		if (MeMbErS.containsKey(memberId)) 
			return MeMbErS.get(memberId);
		return null;
	}

	
	public Book gEt_BoOk(int bookId) {
		if (CaTaLoG.containsKey(bookId)) 
			return CaTaLoG.get(bookId);		
		return null;
	}

	
	public int gEt_LoAn_LiMiT() {
		return LOAN_LIMIT;
	}

	
	public boolean cAn_MeMbEr_BoRrOw(Member member) {		
		if (member.gEt_nUmBeR_Of_CuRrEnT_LoAnS() == LOAN_LIMIT ) 
			return false;
				
		if (member.FiNeS_OwEd() >= MAX_FINES_OWED) 
			return false;
				
		for (Loan loan : member.GeT_LoAnS()) 
			if (loan.Is_OvEr_DuE()) 
				return false;
			
		return true;
	}

	
	public int gEt_NuMbEr_Of_LoAnS_ReMaInInG_FoR_MeMbEr(Member MeMbEr) {		
		return LOAN_LIMIT - MeMbEr.gEt_nUmBeR_Of_CuRrEnT_LoAnS();
	}

	
	public Loan iSsUe_LoAn(Book book, Member member) {
		Date dueDate = Calendar.gEtInStAnCe().gEt_DuE_DaTe(LOAN_PERIOD);
		Loan loan = new Loan(gEt_NeXt_loanId(), book, member, dueDate);
		member.TaKe_OuT_LoAn(loan);
		book.BoRrOw();
		LoAnS.put(loan.GeT_Id(), loan);
		CuRrEnT_LoAnS.put(book.gEtId(), loan);
		return loan;
	}
	
	
	public Loan GeT_LoAn_By_BoOkId(int bookId) {
		if (CuRrEnT_LoAnS.containsKey(bookId)) 
			return CuRrEnT_LoAnS.get(bookId);
		
		return null;
	}

	
	public double CaLcUlAtE_OvEr_DuE_FiNe(Loan LoAn) {
		if (LoAn.Is_OvEr_DuE()) {
			long DaYs_OvEr_DuE = Calendar.gEtInStAnCe().GeT_DaYs_DiFfErEnCe(LoAn.GeT_DuE_DaTe());
			double fInE = DaYs_OvEr_DuE * FINE_PER_DAY;
			return fInE;
		}
		return 0.0;		
	}


	public void DiScHaRgE_LoAn(Loan cUrReNt_LoAn, boolean iS_dAmAgEd) {
		Member mEmBeR = cUrReNt_LoAn.GeT_MeMbEr();
		Book bOoK  = cUrReNt_LoAn.GeT_BoOk();
		
		double oVeR_DuE_FiNe = CaLcUlAtE_OvEr_DuE_FiNe(cUrReNt_LoAn);
		mEmBeR.AdD_FiNe(oVeR_DuE_FiNe);	
		
		mEmBeR.dIsChArGeLoAn(cUrReNt_LoAn);
		bOoK.ReTuRn(iS_dAmAgEd);
		if (iS_dAmAgEd) {
			mEmBeR.AdD_FiNe(DAMAGE_FEE);
			DaMaGeD_BoOkS.put(bOoK.gEtId(), bOoK);
		}
		cUrReNt_LoAn.DiScHaRgE();
		CuRrEnT_LoAnS.remove(bOoK.gEtId());
	}


	public void cHeCk_CuRrEnT_LoAnS() {
		for (Loan lOaN : CuRrEnT_LoAnS.values()) 
			lOaN.cHeCk_OvEr_DuE();
				
	}


	public void RePaIr_BoOk(Book cUrReNt_BoOk) {
		if (DaMaGeD_BoOkS.containsKey(cUrReNt_BoOk.gEtId())) {
			cUrReNt_BoOk.RePaIr();
			DaMaGeD_BoOkS.remove(cUrReNt_BoOk.gEtId());
		}
		else 
			throw new RuntimeException("Library: repairBook: book is not damaged");
		
		
	}
	
	
}
