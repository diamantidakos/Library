package com.mgiandia.library.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.mgiandia.library.LibraryException;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.domain.Item;
import com.mgiandia.library.domain.Loan;
import com.mgiandia.library.util.SimpleCalendar;

/**
 * Η υπηρεσία του δανεισμού. Αναλαμβάνει την αναζήτηση δανειζομένων και
 * αντιτύπων και καταγράφει τους δανεισμούς
 * 
 * @author Νίκος Διαμαντίδης
 *
 */
public class LoanService {
	private Borrower borrower;
	private EntityManager em;

	public LoanService(EntityManager em) {
		this.em = em;
	}

	/**
	 * Αναζητά το δανειζόμενο με βάση τον αριθμό δανειζομένου.
	 * 
	 * @param borrowerNo
	 *            Ο αριθμός δανειζομένου
	 * @return {@code true} αν βρεθεί ο δανειζόμενος
	 */
	public Boolean findBorrower(int borrowerNo) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			borrower = em.find(Borrower.class, borrowerNo);
			tx.commit();
		} catch (NoResultException ex) {
			borrower = null;
			tx.rollback();
		}

		return borrower != null;
	}

	/**
	 * Πραγματοποιεί το δανεισμό.
	 * 
	 * @param itemNo
	 *            Ο αριθμός εισαγωγής του αντιτύπου
	 * @return Την προθεσμία επιστροφής. Επιστρέφει {@code null} εάν ο
	 *         δανειζόμενος δεν δικαιούται να δανειστεί αντίτυπο.
	 * @throws LibraryException
	 *             Εάν δεν υπάρχει δανειζόμενος
	 */
	public SimpleCalendar borrow(int itemNo) {
		if (borrower == null) {
			throw new LibraryException();
		}
		if (!borrower.canBorrow()) {
			return null;
		}

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Item item = em.find(Item.class, itemNo);
		Loan loan = item.borrow(borrower);

		em.persist(loan);
		tx.commit();
		return loan.getDue();

	}

	/**
	 * throws LibraryException with the error message
	 * @param itemId
	 * @param borrowerNo
	 * @return
	 */
	public void loanItem(int itemId, int borrowerNo) {

		boolean borrowerFound = findBorrower(borrowerNo);

		if (!borrowerFound) {
			throw new LibraryException("Borrower with id " + borrowerNo + "  does not exist.");
		}
		SimpleCalendar returnDate = borrow(itemId);
		
		if (returnDate == null){
			throw new LibraryException("Borrower with id " + borrowerNo + "  cannot borrow.");
		}

	}

	@SuppressWarnings("unchecked")
	public List<Loan> findPendingLoans(boolean overdueOnly) {
		List<Loan> allLoans = new ArrayList<Loan>();

		Query query = null;

		query = em
				.createQuery("select loan from Loan loan join fetch loan.borrower b"
						+ " join fetch loan.item i");

		allLoans = query.getResultList();

		if (!overdueOnly) {
			return allLoans;
		} else {
			List<Loan> overdueLoans = new ArrayList<Loan>();

			for (Loan l : allLoans) {

				if (l.isOverdue()) {
					overdueLoans.add(l);
				}

			}

			return overdueLoans;
		}

	}

	public Loan findPendingLoan(int itemId) {

		Loan pendingLoan = null;

		Query query = null;

		query = em
				.createQuery("select loan from Loan loan join fetch loan.borrower b "
						+ " join fetch loan.item item "
						+ " join fetch item.book book "
						+ " where loan.returnDate is null ");

		try {
			pendingLoan = (Loan) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

		return pendingLoan;
	}

	
}
