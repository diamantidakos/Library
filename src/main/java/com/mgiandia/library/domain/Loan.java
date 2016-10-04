package com.mgiandia.library.domain;


import javax.persistence.*;

import com.mgiandia.library.LibraryException;
import com.mgiandia.library.util.Money;
import com.mgiandia.library.util.SimpleCalendar;
import com.mgiandia.library.util.SystemDate;


/**
 * Ο δανεισμός ενός αντιτύπου για κάποιο δανειζόμενο.
 * Καταγράφει το γεγονός του δανεισμού και της επιστροφής.
 * @author Νίκος Διαμαντίδης
 *
 */
@Entity
@Table(name="loans")
public class Loan {
    
    @Id 
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @org.hibernate.annotations.Type(
            type="com.mgiandia.library.persistence.SimpleCalendarCustomType")
    @Column(name="loandate")
    private SimpleCalendar loanDate = SystemDate.now();
    
    @org.hibernate.annotations.Type(
            type="com.mgiandia.library.persistence.SimpleCalendarCustomType")
    @Column(name="returndate")
    private SimpleCalendar returnDate;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="borrowerno")
    private Borrower borrower;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="itemno")
    private Item item;

    /**
     * Ο κατασκευαστής της κλάσης έχει προκαθορισμένη ορατότητα.
     * Μόνο αντικείμενα εντός του πακέτου μπορούν
     * να δημιουργήσουν αντικείμενα της κλάσης.
     */
    Loan() { }

    /**
     * Βοηθητικός κατασκευαστής που αρχικοποιεί έναν δανεισμό.
     * @param borrower Δανειζόμενος
     * @param item Αντίτυπο
     * @param loanDate Ημερομηνία δανεισμού
     */
    Loan(Borrower borrower, Item item, SimpleCalendar loanDate) {
        this.borrower = borrower;
        this.item = item;
        this.loanDate = loanDate;
    }

    public Integer getId() {
        return id;
    }

    /**
     * Θέτει την ημερομηνία δανεισμού.
     * @param loanDate Η ημερομηνία δανεισμού.
     */
     protected void setLoanDate(SimpleCalendar loanDate) {
        this.loanDate = loanDate;
    }

    /**
     * Επιστρέφει την ημερομηνία δανεισμού.
     * @return Η ημερομηνία δανεισμού.
     */
    public SimpleCalendar getLoanDate() {
        return loanDate;
    }

    /**
     * Επιστρέφει την προθεσμία επιστροφής του δανεισμού.
     * Εάν δεν έχει οριστεί ημερομηνία δανεισμού επιστρέφει {@code null}.
     * Εάν δεν υπάρχει δανειζόμενος επιστρέφει {@code null}.
     * @return Η προθεσμία επιστροφής
     */
    public SimpleCalendar getDue() {
        if (loanDate == null) {
            return null;
        }

        if (borrower == null) {
            return null;
        }

        return borrower.getLoanDue(loanDate);
    }

    /**
     * Θέτει την ημερομηνία επιστροφής του αντιτύπου.
     * @param returnDate Η ημερομηνία επιστοφής.
     */
    protected void setReturnDate(SimpleCalendar returnDate) {
        this.returnDate = returnDate;
    }


    /**
     * Επιστρέφει την ημερομηνία επιστροφής του αντιτύπου.
     * @return Η ημερομηνία επιστροφής
     */
    public SimpleCalendar getReturnDate() {
        return returnDate;
    }


    /**
     * Θέτει το δανειζόμενο του δανεισμού.
     * @param borrower Ο δανειζόμενος του δανεισμού
     * @see Borrower#getLoans()
     */
    protected void setBorrower(Borrower borrower) {
        if (this.borrower != null) {
            this.borrower.friendLoans().remove(this);
        }
        this.borrower = borrower;
        if (borrower != null) {
            this.borrower.friendLoans().add(this);
        }
    }


    /**
     * Επιστρέφει το δανειζόμενο του δανεισμού.
     * @return Ο δανειζόμενος
     */
    public Borrower getBorrower() {
        return borrower;
    }


    /**
     * Θέτει το αντίτυπο του δανεισμού.
     * @param item Το αντίτυπο
     */
    protected void setItem(Item item) {
        this.item = item;
    }


    /**
     * Επιστρέφει το αντίτυπο του δανεισμού.
     * @return Το αντίτυπο
     */
    public Item getItem() {
        return item;
    }


    /**
     * Επιστρέφει {@code true} αν το αντίτυπο δεν έχει επιστραφεί.
     * @return {@code true} αν το αντίτυπο δεν έχει επιστραφεί
     */
    public boolean isPending() {
        return returnDate == null;
    }

    /**
     * Η επιστροφή του αντιτύπου.
     * Πραγματοποιείται η επιστροφή
     * και ενημερώνεται η ημερομηνία επιστροφής.
     */
    public void returnItem() {
        // πρέπει να στείλουμε μήνυμα στο item ότι είναι πλέον διαθέσιμο
        item.available();
        setReturnDate(SystemDate.now());
    }

    /**
     * Επιστρέφει {@code true} εάν έχει καθυστερήσει η επιστροφή του αντιτύπου.
     * Η καθυστέρηση εξετάζεται σε σχέση με την ημερομηνία του συστήματος.
     * Εάν το αντίτυπο έχει επιστραφεί τότε επιστρέφει
     * πάντα {@code false}
     * @return {@code true} εάν έχει καθυστερήσει η
     * επιστροφή του αντιτύπου αλλιώς {@code false}
     */
    public boolean isOverdue() {
        if (!isPending()) {
            return false;
        }

        return daysToDue() < 0;
    }


    /**
     * Επιστρέφει τις ημέρες που υπολείπονται για την επιστροφή του αντιτύπου. 
     * Είναι οι ημέρες έως την προθεσμία επιστροφής τους αντιτύπου.
     * Χρησιμοποιείται και για τον υπολογισμό των
     * ημερών κατά τον οποίο έχει γίνει
     * υπέρβαση της προθεσμίας επιστροφής.
     * Η {@link Loan#getOverdue()} είναι ότι ο αριθμός
     * των ημερών υπολογίζεται σε σχέση με την τρέχουσα
     * ημερομηνία αντί σε σχέση με την ημερομηνία
     * επιστροφής.
     * <p>
     * @return Ο αριθμός των ημερών έως την προθεσμία επιστροφής του δανεισμού.
     * Αν ο αριθμός είναι θετικός τότε είναι αριθμός που υπολείπεται
     * έως την προθεσμία επιστροφής.
     * Εάν ο αριθμός είναι αρνητικός τότε επιστρέφονται
     * οι μέρες υπέρβασης της προθεσμίας επιστροφής.
     * @throws LibraryException Εάν δεν μπορεί
     * να υπολογιστεί η προθεσμία επιστροφής
     */
    public long daysToDue() {
        SimpleCalendar due = getDue();
        if (due == null) {
            throw new LibraryException();
        }

        return SystemDate.now().durationInDays(due);
    }

    /**
     * Επιστρέφει τον αριθμό ημερών καθυστέρησης κατά την επιστροφή του αντιτύπου.
     * <p>
     * Επιστρέφει {@code 0} αν η ημερομηνία δανεισμού
     * είναι {@code null} ή η ημερομηνία
     * επιστροφής είναι {@code null} (δεν
     * έχει γίνει δηλαδή η επιστροφή).
     * Επιστρέφει {@code 0} αν δεν υπάρχει δανειζόμενος.
     * Η διαφορά με τη μέθοδο {@link Loan#daysToDue()}
     * είναι ότι επιστρέφει τον αριθμό
     * των ημερών καθυστέρησης σε σχέση με την
     * ημερομηνία επιστροφής και όχι σε σχέση με την
     * τρέχουσα ημερομηνία.
     * @return Ο αριθμός ημερών καθυστέρησης της επιστροφής
     */
    public long getOverdue() {
        if (loanDate == null || returnDate == null) {
            return 0;
        }

        if (borrower == null) {
            return 0;
        }

        if (getDue() == null) {
            return 0;
        }

        long overdue = getDue().durationInDays(returnDate);
        return overdue > 0 ? overdue : 0 ;
    }


    /**
     * Επιστέφει το πρόστιμο για την καθυστέρηση της επιστροφής αντιτύπου.  
     * Το πρόστιμο επιστρέφεται αν πράγματι υπάρχει καθυστέρηση.
     * @return Το πρόστιμο καθυστέρησης
     */
    public Money getFine() {
        if (getDue() == null || getReturnDate() == null || getBorrower() == null) {
            return Money.euros(0);
        }
        
        long overdue = getDue().durationInDays(getReturnDate());

        if (overdue <= 0) {
            return Money.euros(0);
        }
        
        return getBorrower().getDailyFine().times(overdue);
    }

}
