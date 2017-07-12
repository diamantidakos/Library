package com.mgiandia.library.view.Book.AddEditBook;

import java.util.List;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public interface AddEditBookView
{
    String getBookTitle();
    Integer getPublisherPosition();
    String getISBN();
    String getPublication();
    String getYear();
    List<Integer> getAuthorPositions();

    Integer getAttachedBookID();

    /**
     * Θέτει τον τίτλο του βιβλίου
     * @param value Ο τίτλος του βιβλίου
     */
    void setBookTitle(String value);

    /**
     * Θέτει την θέση του συγγραφέα.
     * @param value Η θέση του συγγραφέα.
     */
    void setPublisherPosition(Integer value);

    /**
     * Θέτει το ISBN του βιβλίου
     * @param value Το ISBN του βιβλίου
     */
    void setISBN(String value);

    /**
     * Θέτει το ημερομηνία εξέδωσης του βιβλίου
     * @param value Η ημερομηνία εξέδωσης του βιβλίου
     */
    void setPublication(String value);

    /**
     * Θέτει το ετος του βιβλίου
     * @param value Η το ετος του βιβλίου
     */
    void setYear(String value);

    /**
     * Θέτει τις θέσεις των συγγραφέων
     * @param value Οι θέσεις των συγγραφέων.
     */
    void setAuthorPositions(List<Integer> value);

    /**
     * Θέτει το όνομα της σελίδας.
     * @param value το όνομα της σελίδας
     */
    void setPageName(String value);

    /**
     * Θέτει την λίστα των συγγραφέων.
     * @param names Τα ονόματα των συγγραφέων
     */
    void setAuthorList(List<String> names);

    /**
     * Θέτει την λίστα των εκδοτών.
     * @param names Τα ονόματα των εκδοτών
     * @param defaultName Το προκαθορισμένο όνομα
     */
    void setPublisherList(List<String> names, String defaultName);

    /**
     * Το μήνυμα πoυ εμφανίζεται όταν τελειώνει
     * επιτυχώς ένα activity.
     * @param message Το μήνυμα που θα εμφανίσει
     */
    void successfullyFinishActivity(String message);

    /**
     * Το μηνύμα που εμφανίζεται σε
     * περίπτωση error.
     * @param title O τίτλος του μηνύματος
     * @param message Το περιεχόμενο του μηνύματος
     */
    void showErrorMessage(String title, String message);
}
