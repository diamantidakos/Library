package com.mgiandia.library.view.Author.ManageAuthors;

import java.util.List;

import com.mgiandia.library.util.Quadruple;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public interface ManageAuthorsView
{
    /**
     * Μεταφερει τον χρήστη στο activity AuthorDetailsActivity
     * όταν γίνει click πάνω στον συγγραφέα με id uid.
     * @param uid To μοναδικό id του σθγγραφέα
     */
    void clickItem(int uid);

    /**
     * Ξεκινάει το activity AddEditAuthorActivity
     */
    void startAddNew();

    /**
     * Φορτώνει την λίστα με τους συγγραφείς
     * @param input Η λιστα που θα φορτώσει
     */
    void loadSource(List<Quadruple> input);

    /**
     * Εμφανίζει ένα μήνυμα με περιεχόμενο
     * value.
     * @param value To περιεχόμενο που θα εμφανιστεί
     */
    void showToast(String value);
}
