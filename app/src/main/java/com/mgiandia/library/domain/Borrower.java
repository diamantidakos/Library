package com.mgiandia.library.domain;

import java.util.HashSet;
import java.util.Set;

import com.mgiandia.library.contacts.Address;
import com.mgiandia.library.contacts.EmailAddress;
import com.mgiandia.library.contacts.TelephoneNumber;
import com.mgiandia.library.util.Money;
import com.mgiandia.library.util.SimpleCalendar;
/**
 * Ο δανειζόμενος.
 * @author Νίκος Διαμαντίδης
 *
 */


public class Borrower  {
    private int borrowerNo;
    private TelephoneNumber telephone;
    private EmailAddress eMail;
    private Address address;
    private BorrowerCategory category;
    private Person person = new Person();
    private Set<Loan> loans = new HashSet<Loan>();


    /**
     * Ο προκαθορισμένος κατασκευαστής.
     */
    public Borrower() { }


    /**
     * Βοηθητικός κατασκευαστής που αρχικοποιεί
     * τα βασικά στοιχεία του δανειζόμενου.
     * @param borrowerNo Αριθμός δανειζομένου
     * @param firstName Όνομα
     * @param lastName Επώνυμο
     * @param address Ταχυδρομική διεύθυνση
     * @param eMail Διεύθυνση ηλεκτρονικού ταχυδρομείου
     * @param telephone Αριθμός Τηλεφώνου
     * @param category Κατηγορία
     */
    public Borrower(int borrowerNo, String firstName, String lastName, Address address, EmailAddress eMail, TelephoneNumber telephone, BorrowerCategory category)
    {
        this.borrowerNo = borrowerNo;
        this.person.setFirstName(firstName);
        this.person.setLastName(lastName);
        this.address = address == null ? null : new Address(address);
        this.eMail = eMail;
        this.telephone = telephone;
        this.category = category;
    }


    /**
     * Επιστρέφει τον αριθμό δανειζομένου. Ο αριθμός δανειζομένου
     * προσδιορίζει μοναδικά κάθε δανειζόμενο.
     * @return Τον αριθμό δανειζομένου
     */
    public int getBorrowerNo() {
        return borrowerNo;
    }


    /**
     * Θέτει τον αριθμό δανειζομένου.  Ο αριθμός δανειζομένου
     * προσδιορίζει μοναδικά κάθε δανειζόμενο
     * @param borrowerNo Ο αριθμός δανειζομένου
     */
    public void setBorrowerNo(int borrowerNo) {
        this.borrowerNo = borrowerNo;
    }


    /**
     * Θέτει το όνομα του δανειζομένου.
     * @param firstName Το όνομα του δανειζομένου
     */
    public void setFirstName(String firstName) {
        person.setFirstName(firstName);
    }

    /**
     * Επιστρέφει το όνομα του δανειζομένου.
     * @return Το όνομα του δανειζομένου
     */
    public String getFirstName() {
        return person.getFirstName();
    }


    /**
     * Θέτει το επώνυμο του δανειζομένου.
     * @param lastName Το επώνυμο του δανειζομένου
     */

    public void setLastName(String lastName) {
        person.setLastName(lastName);
    }

    /**
     * Επιστρέφει το επώνυμο του δανειζομένου.
     * @return Το επώνυμο του δανειζομένου
     */
    public String getLastName() {
        return person.getLastName();
    }

    /**
     * Θέτει τον αριθμό τηλεφώνου του δανειζομένου.
     * @param telephone Ο αριθμός τηλεφώνου
     */
    public void setTelephone(TelephoneNumber telephone) {
        this.telephone = telephone;
    }


    /**
     * Επιστρέφει τον αριθμό τηλεφώνου του δανειζομένου.
     * @return Ο αριθμός τηλεφώνου
     */
    public TelephoneNumber getTelephone() {
        return telephone;
    }

    /**
     * Θέτει το e-mail του δανειζομένου.
     * @param eMail Το e-mail του δανειζομένου
     */
    public void setEmail(EmailAddress eMail) {
        this.eMail = eMail;
    }


    /**
     * Επιστρέφει το e-mail του δανειζομένου.
     * @return Το e-mail του δανειζομένου
     */
    public EmailAddress getEmail() {
        return eMail;
    }

    /**
     * Θέτει την κατηγορία δανειζομένου.
     * @param category Η κατηγορία δανειζομένου
     */
    public void setCategory(BorrowerCategory category) {
        this.category = category;
    }


    /**
     * Επιστρέφει την κατηγορία δανειζομένου.
     * @return Η κατηγορία δανειζομένου
     */
    public BorrowerCategory getCategory() {
        return category;
    }

    /**
     * Θέτει την ταχυδρομική διεύθυνση του δανειζομένου.
     * Αποθηκεύεται αντίγραφο της διεύθυνση. Για να αλλάξετε τη διεύθυνση
     * δημιουργήστε ένα νέο αντικείμενο της κλάσης {@link Address}
     * @param address Η ταχυδρομική διεύθυνση
     */
    public void setAddress(Address address) {
        this.address = address == null ? null : new Address(address);
    }


    /**
     * Επιστρέφει την ταχυδρομική διεύθυνση του δανειζομένου
     * Επιστρέφει αντίγραφο της διεύθυνσης. Για να αλλάξετε τη διεύθυνση
     * Δημιουργήστε ένα αντικείμενο της κλάσης {@link Address}
     * και χρησιμοποιήστε τη μέθοδο {@link Borrower#setAddress(Address)}
     * @return Η διεύθυνση του δανειζομένου
     */
    public Address getAddress() {
        return address == null ? null : new Address(address);
    }


    /**
     * Επιστέφει τη συλλογή των δανεισμών του δανειζομένου.
     * Η συλλογή είναι αντίγραφο της συλλογής των δανεισμών.
     * @return Η συλλογή των δανεισμών
     */
    public Set<Loan> getLoans() {
        return new HashSet<Loan>(loans);
    }

    /**
     * Μη ενθυλακωμένη συλλογή των δανεισμών.
     * @return Η συλλογή των δανεισμών
     */
    Set<Loan> friendLoans() {
        return loans;
    }

    /**
     * Επιστρέφει τον αριθμό των αντιτύπων που έχει δανειστεί ο
     * δανειζόμενος και δεν έχει επιστρέψει.
     * @return Ο αριθμός των αντιτύπων που δεν έχουν επιστραφεί.
     */
    private int countPendingItems() {
        int pendingItems = 0;
        for (Loan loan : loans) {
            if (loan.isPending()) {
                pendingItems++;
            }
        }
        return pendingItems;
    }


    /**
     * Επιστέφει {@code true} αν ο δανειζόμενος μπορεί
     * να δανειστεί κάποιο αντίτυπο.
     * Το αν ο δανειζόμενος μπορεί να δανειστεί κάποιο
     * αντίτυπο εξαρτάται από το μέγιστο αριθμό αντιτύπων
     * που μπορεί να δανειστεί και από τον αριθμό των
     * αντιτύπων που έχει δανειστεί και δεν έχει επιστρέψει
     * Επιστρέφει {@code false} εάν η κατηγορία δανειζομένου
     * είναι {@code null}
     * @return {@code true} εάν ο δανειζόμενος μπορεί
     * να δανειστεί κάποιο αντίτυπο.
     */
    public boolean canBorrow() {
        int pendingItems;

        if (getCategory() == null) {
            return false;
        }
        pendingItems = countPendingItems();
        return getCategory().canBorrow(pendingItems);
    }


    /**
     * Επιστρέφει την προθεσμία επιστροφής για κάποια ημερομηνία δανεισμού.
     * Η προθεσμία επιστροφής εκτός από την ημερομηνία δανεισμού
     * εξαρτάται και το μέγιστο
     * αριθμό ημερών που μπορεί να δανειστεί ο δανειζόμενος κάποιο αντίτυπο.
     * Επιστρέφει {@code null} αν δεν υπάρχει
     * κατηγορία δανειζομένου ({@link BorrowerCategory}).
     * @param loanDate Η ημερομηνία δανεισμού.
     * @return Η προθεσμία επιστροφής.
     */
    public SimpleCalendar getLoanDue(SimpleCalendar loanDate) {
        if (loanDate == null) {
            return null;
        }
        if (getCategory() == null) {
            return loanDate;
        }
        return category.getLoanDue(loanDate);

    }

    /**
     * Επιστρέφει ο ημερήσιο πρόστιμο για το δανειζόμενο.
     * Επιστρέφει {@code null} αν δεν υπάρχει
     * κατηγορία δανειζομένου ({@link BorrowerCategory})
     * @return Το ημερήσιο πρόστιμο
     */
    public Money getDailyFine() {
        if (getCategory() == null) {
            return null;
        }
        return getCategory().getDailyFine();
    }

    boolean hasPendingLoan(Book book) {
        boolean isCurrentBorrower = false;

        for(Loan loan: loans){
            if (loan.isPending() && loan.getItem().getBook().equals(book)){
                isCurrentBorrower = true;
                break;
            }
        }
        return isCurrentBorrower;
    }


}
