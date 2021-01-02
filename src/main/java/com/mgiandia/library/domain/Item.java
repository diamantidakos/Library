package com.mgiandia.library.domain;

/**
 * Το αντίτυπο ενός βιβλίου.
 * @author Νίκος Διαμαντίδης
 *
 */
public class Item {

    private int itemNumber = 0;
    private Book book;
    private ItemObjectState objectState = new NewState();

    /**
     * Προκαθορισμένος κατασκευαστής.
     */
    public Item() { }


    /**
     * Βοηθητικός κατασκευαστής που δέχεται τον αριθμό εισαγωγής ως παράμετρο.
     * @param itemNumber Ο αριθμός εισαγωγής
     */
    public Item(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    /**
     * Θέτει τον αριθμό εισαγωγής του αντιτύπου.
     * Ο αριθμός εισαγωγής προσδιορίζει μοναδικά κάθε αντίτυπο.
     * @param itemNumber Ο αριθμός εισαγωγής.
     */
    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    /**
     * Επιστρέφει τον αριθμό εισαγωγής του αντιτύπου.
     * Ο αριθμός εισαγωγής προσδιορίζει μοναδικά κάθε αντίτυπο.
     * @return Ο αριθμός εισαγωγής
     */
    public int getItemNumber() {
        return itemNumber;
    }


    /**
     * Θέτει το βιβλίο του αντιτύπου.
     * @param book  Το βιβλίο του αντιτύπου
     * @see Book#addItem(Item)
     */
    public void setBook(Book book) {
        if (this.book != null) {
            this.book.friendItems().remove(this);
        }
        this.book = book;
        if (this.book != null) {
            this.book.friendItems().add(this);
        }
    }

    /**
     * Επιστρέφει το βιβλίο του αντιτύπου.
     * @return Το βιβλίο του αντιτύπου
     */
    public Book getBook() {
        return book;
    }
    

    
    /**
     * Επιστρέφει την κατάσταση του αντιτύπου.
     * @return Η κατάσταση του αντιτύπου
     */
    public ItemState getState() {
        return objectState.getState();
    }
    


    /**
     * θέτει την κατάσταση του αντιτύπου.
     * Προσοχή στην ορατότητα.
     * Δεν είναι δημόσια και δεν μπορεί οποιαδήποτε
     * κλάση να αλλάξει την κατάσταση
     * του αντιτύπου.
     * @param state Η κατάσταση του αντιτύπου.
     */
    void setObjectState(ItemObjectState objectState) {
        this.objectState = objectState;
    }
    



    /**
     * Πραγματοποιεί το δανεισμό του αντιτύπου για
     * κάποιο δανειζόμενο και επιστρέφει το δανεισμό.
     * Εάν ο δανειζόμενος είναι {@code null} τότε επιστρέφει{@code null}.
     * Εάν ο δανειζόμενος δεν δικαιούται να δανειστεί
     * κάποιο αντίτυπο τότε δεν πραγματοποιείται ο δανεισμός
     * και επιστρέφει {@code null}.
     * Επιστρέφει {@code null} εάν η κατάσταση του
     * αντιτύπου δεν είναι {@code AVAILABLE}
     * Η κατάσταση του αντιτύπου γίνεται {@code LOANED}
     * @param borrower Ο δανειζόμενος
     * @return Το αντικείμενο του δανεισμού.
     */
    public Loan borrow(Borrower borrower) {
        return objectState.borrow(this, borrower);
    }

    /**
     * Αλλάζει την κατάσταση του αντιτύπου σε διαθέσιμο ({@code AVAILABLE}).
     */

    public void available() {
        objectState.available(this);
    }

  


    /**
     * Το αντίτυπο αποσύρεται και δεν είναι διαθέσιμο για δανεισμό.
     */
    
    public void withdraw() {
        objectState.withdraw(this);
    }

  
    /**
     * Το αντίτυπο έχει χαθεί και δεν είναι διαθέσιμο για δανεισμό.
     */
    public void lost() {
        objectState.lost(this);
    }


    @Override
    public String toString() {
        return String.valueOf(itemNumber);
    }

}
