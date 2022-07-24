package com.mgiandia.library.service;

import java.time.LocalDate;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import com.mgiandia.library.Fixture;
import com.mgiandia.library.contacts.EmailMessage;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.util.SystemDateStub;

@QuarkusTest
public class NotificationServiceTest  {
	@Inject
	LoanService loanService;
	
	@Inject
	EntityManager em;
	
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
		// την 1η Σεπτεμβρίου 20007 και δανειζουμε ένα αντίτυπο

		setSystemDateTo1stSeptember2007();
		borrowRefactoringToGiakoumakis();

		// ρυθμίζουμε την ημερομηνία για την 1η Νοεμβρίου
		setSystemDateTo1stNovember2007();
		notificationService.setProvider(provider);
		notificationService.notifyBorrowers();


		Borrower diamantidis = em.find(Borrower.class, Fixture.DIAMANTIDIS_ID);
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
		loanService.findBorrower(Fixture.DIAMANTIDIS_ID);
		loanService.borrow(Fixture.UML_USER_GUIDE_ID1);
	}

	private void borrowRefactoringToGiakoumakis() {
		loanService.findBorrower(Fixture.GIAKOUMAKIS_ID);
		loanService.borrow(Fixture.UML_REFACTORING_ID);
	}


}
