package com.mgiandia.library.service;

import java.time.LocalDate;

import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import com.mgiandia.library.Fixture;
import com.mgiandia.library.Fixture.Items;
import com.mgiandia.library.IntegrationBase;
import com.mgiandia.library.contacts.EmailMessage;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.domain.Item;
import com.mgiandia.library.domain.Loan;
import com.mgiandia.library.persistence.BorrowerRepository;
import com.mgiandia.library.persistence.ItemRepository;
import com.mgiandia.library.persistence.LoanRepository;
import com.mgiandia.library.util.SystemDateStub;

@QuarkusTest
public class NotificationServiceTest extends IntegrationBase {
	@Inject
	BorrowerRepository borrowerRepository;
	
	@Inject
	ItemRepository itemRepository;
	
	@Inject
	LoanRepository loanRepository;
	
	
	@Inject
	NotificationService notificationService;
	
	private EmailProviderStub provider;

	@BeforeEach
	protected void beforeDatabasePreparation() {
		provider = new EmailProviderStub();
	}

	@AfterEach
	protected void afterTearDown() {
		SystemDateStub.reset();
	}

	/**
	 * Πραγματοποιούμε δύο δανεισμούς. Για τον πρώτο έχει παρέλθει η προθεσμία
	 * επιστροφής, ενώ για το δεύτερο όχι. Περιμένουμε την αποστολή μόνο ενός
	 * μηνύματος καθυστέρησης για τον πρώτο δανεισμό.
	 */
	@Test
	@TestTransaction 
	public void sendMessageOnOverdueJpa() {
		// Ρυθμίζουμε την ημερομηνία του συστήματος για
		// την 1η Μαρτίου 2007 και δανείζουμε ένα αντίτυπο

		setSystemDateTo1stMarch2007();
		borrowUMLUserGuideToDiamantidis();

		// Ρυθμίζουμε την ημερομηνία του συστήματος για
		// την 1η Σεπτεμβρίου 20007 και δανείζουμε ένα αντίτυπο

		setSystemDateTo1stSeptember2007();
		borrowRefactoringToGiakoumakis();

		// ρυθμίζουμε την ημερομηνία για την 1η Νοεμβρίου
		setSystemDateTo1stNovember2007();
		notificationService.setProvider(provider);
		notificationService.notifyBorrowers();

		Assertions.assertEquals(2, loanRepository.activeLoans().size());

		Borrower diamantidis = borrowerRepository.findById(Fixture.Borrowers.DIAMANTIDIS_ID);
		Assertions.assertEquals(1, provider.allMessages.size());
		EmailMessage message = provider.getAllEmails().get(0);
		Assertions.assertEquals(diamantidis.getEmail(), message.getTo());
	}

	
	public void sendMessageOnOverdue() {

	}

	private void setSystemDateTo1stMarch2007() {
		SystemDateStub.setStub(LocalDate.of(2007, 3, 1));
	}

	private void setSystemDateTo1stNovember2007() {
		 SystemDateStub.setStub(LocalDate.of(2007, 11, 1));
	}

	private void setSystemDateTo1stSeptember2007() {
		 SystemDateStub.setStub(LocalDate.of(2007, 9, 1));
	}

	private void borrowUMLUserGuideToDiamantidis() {
		Borrower borrower = borrowerRepository.findById(Fixture.Borrowers.DIAMANTIDIS_ID);
		Item item = itemRepository.findById(Items.UML_USER_GUIDE_ID1);
		Loan loan = item.borrow(borrower);
		loanRepository.persist(loan);
		
		loanRepository.flush();
	}

	private void borrowRefactoringToGiakoumakis() {
		Borrower borrower = borrowerRepository.findById(Fixture.Borrowers.GIAKOUMAKIS_ID);
		Item item = itemRepository.findById(Fixture.Items.REFACTORING_ID);
		Loan loan = item.borrow(borrower);
		loanRepository.persist(loan);
		
		loanRepository.flush();
	}


}
