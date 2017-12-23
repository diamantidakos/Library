package com.mgiandia.library.view.Book.BookDetails;

import java.util.List;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public interface BookDetailsView
{
    int getAttachedBookID();

    /**
     * Θέτει το id του βιβλίου.
     * @param value Το id του βιβλίου.
     */
    void setID(String value);

    /**
     * Θέτει τον τίτλο του βιβλίου.
     * @param value Ο τίτλος του βιβλίου.
     */
    void setBookTitle(String value);

    /**
     * Θέτει τον εκδότη του βιβλίου.
     * @param value Ο εκδότης του βιβλίου.
     */
    void setPublisher(String value);

    /**
     * Θέτει το ISBN του βιβλίου.
     * @param value Το ISBN του βιβλίου.
     */
    void setISBN(String value);

    /**
     * Θέτει το έτος δημοσίευσης του βιβλίου.
     * @param value Το έτος δημοσίευσης του βιβλίου
     */
    void setPublication(String value);

    /**
     * Θέτει το έτος δημοσίευσης του βιβλίου.
     * @param value Το έτος δημοσίευσης του βιβλίου
     */
    void setYear(String value);

    /**
     * Θέτει τον αριθμό του βιβλίου.
     * @param value Ο αριθμός του βιβλίου
     */
    void setItemsNo(String value);

    /**
     * Θέτει τους συγγραφείς του βιβλίου.
     * @param author_ids Τα id των συγγραφέων
     * @param author_names Τα ονόματα των συγγραφέων
     */
    void setAuthors(List<String> author_ids, List<String> author_names);

    /**
     * Θέτει το όνομα της σελίδας.
     * @param value Το όνομα της σελίδας
     */
    void setPageName(String value);

    /**
     * Ξεκινάει να τροποποιεί το βιβλίο
     * με id bookID.
     * @param bookID Το id του βιβλίου που θα τροποποιηθεί
     */
    void startEdit(int bookID);

    /**
     * Εμφανίζει ένα Toast.
     * @param value Το περιεχόμενο που θα εμφανιστεί
     */
    void showToast(String value);
}
