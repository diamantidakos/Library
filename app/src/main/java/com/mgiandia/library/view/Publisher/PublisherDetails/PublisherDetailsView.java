package com.mgiandia.library.view.Publisher.PublisherDetails;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public interface PublisherDetailsView
{
    int getAttachedPublisherID();

    /**
     * Θέτει το id του εκδότη.
     * @param value Tο id του εκδότη.
     */
    void setID(String value);

    /**
     * Θέτει το όνομα του εκδότη.
     * @param value Το όνομα του εκδότη
     */
    void setName(String value);

    /**
     * Θέτει τον αρι8μό του εκδότη.
     * @param value Ο αρι8μός του εκδότη
     */
    void setPhone(String value);

    /**
     * Θέτει τον αριθμό ηλεκτρονικού ταχυδρομείου του εκδότη.
     * @param value Ο αρι8μός του ηλεκτρονικού ταχυδρομείου του εκδότη.
     */
    void setEmail(String value);

    /**
     * Θέτει τα βιβλία που έχουν εκδοθεί από τον εκδότη αυτό.
     * @param value Τα βιβλία που έχουν εκδοθεί από τον εκδότη αυτό
     */
    void setBooksPublished(String value);

    /**
     * Θέτει την χώρα του εκδότη.
     * @param value Η χώρα του εκδότη
     */
    void setCountry(String value);

    /**
     * Θέτει την πόλη του εκδότη.
     * @param value Η πόλη του εκδότη
     */
    void setAddressCity(String value);

    /**
     * Θέτει την οδό του εκδότη.
     * @param value Η οδός του εκδότη
     */
    void setAddressStreet(String value);

    /**
     * Θέτει τον αριθμό του εκδότη.
     * @param value Ο αριθμός του εκδότη
     */
    void setAddressNumber(String value);

    /**
     * Θέτει τον ταχυδρομικό κώδικα.
     * @param value Ο ταχυδρομικός κώδικας
     */
    void setAddressPostalCode(String value);

    /**
     * Θέτει το όνομα της σελίδας.
     * @param value το όνομα της σελίδας
     */
    void setPageName(String value);

    /**
     * Εμφανίζει το κουμπί της επεξεργασίας του
     * εκδότη με id publisherID.
     * @param publisherID Το μοναδικό id του εκδότη
     */
    void startEdit(int publisherID);

    /**
     * Εμφανίζει το κουμπί της εμφάνισης του
     * εκδότη με id publisherID.
     * @param publisherID Το μοναδικό id του εκδότη
     */
    void startShowBooks(int publisherID);

    /**
     * Εμφανίζει ένα Toast.
     * @param value Το περιεχόμενο που θα εμφανιστεί
     */
    void showToast(String value);
}
