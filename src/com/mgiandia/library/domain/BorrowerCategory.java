package com.mgiandia.library.domain;

import com.mgiandia.library.util.Money;
import com.mgiandia.library.util.SimpleCalendar;

import javax.persistence.*;

import org.hibernate.annotations.Columns;

/**
 * Η κατηγορία δανειζομένου.
 * @author Νίκος Διαμαντίδης
 *
 */
@Entity
@Table(name="borrowercategories")
public class BorrowerCategory {
    
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id; 
    
    @Column(name="description", length = 50, nullable = false)
    private String description;
    
    @Column(name="lendingitems")
    private int maxLendingItems;
    
    @Column(name="lendingdays")
    private int maxLendingDays;
    

    @org.hibernate.annotations.Type(
            type="com.mgiandia.library.persistence.MoneyCustomType")
    @Columns(columns = {
            @Column(name="dailyfineamount"),
            @Column(name="dailyfinecurrency", length=5)
    })
    private Money dailyFine;

    /**
     * Ο προκαθορισμένος κατασκευαστής.
     */
    public BorrowerCategory() { }

    /**
     * Βοηθητικός κατασκευαστής.
     * Aρχικοποιεί τα βασικά στοιχεία της κατηγορίας δανειζομένου.
     * @param description Περιγραφή κατηγορίας
     * @param maxLendingDays Μέγιστος αριθμός ημερών δανεισμού
     * @param maxLendingItems Μέγιστος αριθμός αντιτύπων
     * προς (ταυτόχρονο) δανεισμό
     * @param dailyFine Ημερήσιο πρόστιμο καθυστέρησης
     */
    public BorrowerCategory(String description, int maxLendingDays,
            int maxLendingItems, Money dailyFine) {
        this.description = description;
        this.maxLendingDays = maxLendingDays;
        this.maxLendingItems = maxLendingItems;
        this.dailyFine = dailyFine;
    }


    public Integer getId() {
        return id;
    }
    
    
    /**
     * Θέτει την περιγραφή της κατηγορίας δανειζομένου.
     * @param description Η περιγραφή της κατηγορίας δανειζομένου.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Επιστρέφει την περιγραφή της κατηγορίας δανειζομένου.
     * @return Η κατηγορία δανειζομένου.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Θέτει τον μέγιστο αριθμό ημερών δανεισμού ενός
     * αντιτύπου για την κατηγορία δανειζομένου.
     * @param maxLendingDays Ο μέγιστος αριθμός ημερών δανεισμού.
     */
    public void setMaxLendingDays(int maxLendingDays) {
        this.maxLendingDays = maxLendingDays;
    }

    /**
     * Επιστρέφει τον μέγιστο αριθμό ημερών δανεισμού
     * ενός αντιτύπου για την κατηγορία δανειζομένου.
     * @return Ο μέγιστος αριθμός ημερών δανεισμού.
     */
    public int getMaxLendingDays() {
        return maxLendingDays;
    }

    /**
     * Θέτει τον μέγιστο αριθμό αντιτύπων που μπορεί
     * ταυτόχρονα να δανειστεί κάποιος δανειζόμενος για
     * την κατηγορία δανειζομένου.
     * @param maxLendingItems Ο μέγιστος αριθμός αντιτύπων.
     */
    public void setMaxLendingItems(int maxLendingItems) {
        this.maxLendingItems = maxLendingItems;
    }


    /**
     * Επιστρέφει τον μέγιστο αριθμό αντιτύπων που
     * μπορεί ταυτόχρονα να δανειστεί κάποιος δανειζόμενος για
     * την κατηγορία δανειζομένου.
     * @return Ο μέγιστος αριθμός αντιτύπων
     */
    public int getMaxLendingItems() {
        return maxLendingItems;
    }


    /**
     * Θέτει το ημερήσιο πρόστιμο για την περίπτωση της
     * καθυστέρησης επιστροφής κάποιου αντιτύπου
     * για την κατηγορία δανειζομένου.
     * @param dailyFine Το ημερήσιο πρόστιμο.
     */
    public void setDailyFine(Money dailyFine) {
        this.dailyFine = dailyFine;
    }


    /**
     * Επιστρέφει το ημερήσιο πρόστιμο για την
     * περίπτωση της καθυστέρησης επιστροφής κάποιου αντιτύπου
     * για την κατηγορία δανειζομένου.
     * @return Το ημερήσιο πρόστιμο
     */
    public Money getDailyFine() {
        return dailyFine;
    }

    /**
     * Επιστρέφει την προθεσμία επιστροφής κάποιου
     * αντιτύπου για την κατηγορία δανειζομένου βάσει κάποιας
     * ημερομηνίας δανεισμού.
     * Επιστρέφει {@code null} αν η ημερομηνία δανεισμού είναι {@code null}.
     * @param loanDate Η ημερομηνία δανεισμού
     * @return Η προθεσμία επιστροφής
     */
    public SimpleCalendar getLoanDue(SimpleCalendar loanDate) {
        if (loanDate == null) {
            return null;
        }

        return loanDate.addDays(maxLendingDays);
    }

    /**
     * Επιστρέφει {@code true} εάν ο δανειζόμενος
     * της συγκεκριμένης κατηγορίας δανειζομένου
     * Μπορεί να δανειστεί κάποιο αντίτυπο.
     * @param pendingItems Ο αριθμός των αντιτύπων
     * που έχουν δανειστεί και δεν έχουν επιστραφεί.
     * @return {@code true} εάν μπορεί να δανειστεί κάποιο αντίτυπο.
     */
    public boolean canBorrow(int pendingItems) {
        return maxLendingItems > pendingItems;
    }

}
