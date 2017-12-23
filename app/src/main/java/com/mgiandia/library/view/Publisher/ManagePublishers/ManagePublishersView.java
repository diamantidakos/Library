package com.mgiandia.library.view.Publisher.ManagePublishers;

import java.util.List;

import com.mgiandia.library.util.Quadruple;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public interface ManagePublishersView
{
    /**
     * Μεταφέρει τον χρήστη στο activity PublisherDetailsActivity
     * όταν γίνει click πάνω στον εκδότη με id uid.
     * @param uid Το μοναδικό id του εκδότη
     */
    void clickItem(int uid);

    /**
     * Ξεκινάει το activity AddEditPublisherActivity
     */
    void startAddNew();

    /**
     * Φορτώνει την λίστα με τους εκδότες.
     * @param input Η λίστα που θα φορτώσει
     */
    void loadSource(List<Quadruple> input);

    /**
     * Εμφανίζει ένα Toast.
     * @param value Το περιεχόμενο που θα εμφανιστεί
     */
    void showToast(String value);
}
