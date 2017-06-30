package com.mgiandia.library.view.Author.AuthorDetails;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public interface AuthorDetailsView
{
    int getAttachedAuthorID();

    /**
     * Θέτει το id.
     * @param value To id value
     */
    void setID(String value);

    /**
     * Θέτει το πρώτο όνομα του συγγραφέα.
     * @param value Το πρώτο όνομα του συγγραφέα
     */
    void setFirstName(String value);

    /**
     * Θέτει το επώνυμο του συγγραφέα.
     * @param value Το επώνυμο του συγγραφέα
     */
    void setLastName(String value);

    /**
     * Θέτει τα βιβλία.
     * @param value Το βιβλίο.
     */
    void setBooksWritten(String value);

    /**
     * Θέτει το όνομα της σελίδας.
     * @param value το όνομα της σελίδας
     */
    void setPageName(String value);

    /**
     * Ξεκινάει να τροποποιεί τον συγγραφέα με
     * id authorID.
     * @param authorID To id του συγγραφέα
     */
    void startEdit(int authorID);

    /**
     * Εμφανίζει τα βιβλία του συγγραφέα με
     * id authorID.
     * @param authorID To id του συγγραφέα
     */
    void startShowBooks(int authorID);

    /**
     * Εμφανίζει ένα μήνυμα με περιεχόμενο
     * value.
     * @param value To περιεχόμενο που θα εμφανιστεί
     */
    void showToast(String value);
}
