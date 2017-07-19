package com.mgiandia.library.view.Items.ManageItems;

import java.util.List;

import com.mgiandia.library.util.Quadruple;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public interface ManageItemsView
{
    /**
     * Φορτώνει την λίστα με τους δανειζομένους.
     * @param input Η λίστα που θα φορτώσει
     */
    void loadSource(List<Quadruple> input);

    /**
     * Δημιουργεί ένα μήνυμα τύπου alert
     * @param uid Το id του αντικειμένου
     * @param title Ο τίτλος του μηνύματος
     * @param message Το περιεχόμενο του μηνύματος
     */
    void newItemStateSelectAlert(int uid, String title, String message);

    /**
     * Δημιουργεί ένα μήνυμα τύπου alert
     * @param title Ο τίτλος του μηνύματος
     * @param message Το περιεχόμενο του μηνύματος
     */
    void newItemAddAlert(String title, String message);

    /**
     * Εμφανίζει ένα μήνυμα σε μορφή alert
     * με περιεχόμενο message και τίτλο title.
     * @param title Ο τίτλος του μηνύματος
     * @param message Το περιεχόμενο του μηνύματος
     */
    void showAlert(String title, String message);

    /**
     * Εμφανίζει ένα μήνυμα σε μορφή Toast
     * με περιεχόμενο message.
     * @param value Το περιεχόμενο του μηνύματος
     */
    void showToast(String value);

    /**
     * Αδειάζει το κείμενο που βρίσκεται
     * μέσα στην μπάρα αναζήτησης κατά την
     * ανανέωση της σελίδας
     */
    void refresh();

    /**
     * Επιστρέφει το id του βιβλίου.
     * @return Το id του βιβλίου
     */
    int getAttachedBookID();

    /**
     * Θέτει το όνομα της σελίδας.
     * @param value το όνομα της σελίδας
     */
    void setPageName(String value);
}
