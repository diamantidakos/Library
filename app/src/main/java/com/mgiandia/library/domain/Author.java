package com.mgiandia.library.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Ο Συγγραφέας.
 * @author Νίκος Διαμαντίδης
 *
 */

public class Author {
    private int uid;
    private Person person = new Person();
    private Set<Book> books = new HashSet<Book>();

    /**
     * Προκαθορισμένος κατασκευαστής.
     */
    public Author() { }

    /**
     * Βοηθητικός κατασκευαστής.
     * @param uid Ο μοναδικός κωδικός συγγραφέα.
     * @param firstName Το μικρό όνομα.
     * @param lastName Το επώνυμο.
     */
    public Author(int uid, String firstName, String lastName) {
         this.uid = uid;
         person.setFirstName(firstName);
         person.setLastName(lastName);
    }

    /**
     * Επιστρέφει τον μοναδικό κωδικό του συγγραφέα.
     * @return Ο μοναδικός κωδικός του συγγραφέα
     */
    public int getId() {
        return uid;
    }

    /**
     * Θέτει το όνομα του συγγραφέα.
     * @param firstName Το όνομα του συγγραφέα
     */
    public void setFirstName(String firstName) {
        person.setFirstName(firstName);
    }

    /**
     * Επιστρέφει το όνομα του συγγραφέα.
     * @return Το όνομα του συγγραφέα
     */
    public String getFirstName() {
        return person.getFirstName();
    }

    /**
     * Θέτει το επώνυμο του συγγραφέα.
     * @param lastName Το όνομα του συγγραφέα
     */
    public void setLastName(String lastName) {
        person.setLastName(lastName);
    }

    /**
     * Επιστρέφει το επώνυμο του συγγραφέα.
     * @return Το επώνυμο του συγγραφέα
     */
    public String getLastName() {
        return person.getLastName();
    }

    /**
     * Επιστρέφει τα βιβλία στα οποία συμμετέχει κάποιος συγγραφέας.
     * Η συλλογή των βιβλίων είναι αντίγραφο. Για την προσθήκη κάποιου βιβλίου
     * στη συλλογή χρησιμοποιείστε τη μέθοδο {@link Author#addBook(Book)}
     * και για την  απομάκρυνση ενός βιβλίου τη
     * μέθοδο {@link Author#removeBook(Book)}.
     * @return Αντίγραφο της συλλογής των βιβλίων του συγγραφέα
     */
    public Set<Book> getBooks() {
        return new HashSet<Book>(books);
    }

    /**
     * Προσθέτει ένα βιβλίο στη συλλογή των βιβλίων
     * στα οποία συμμετέχει ο συγγραφέας.
     * @param book Το βιβλίο
     */
    public void addBook(Book book) {
        if (book != null) {
            book.friendAuthors().add(this);
            this.books.add(book);
        }
    }

    /**
     * Απομακρύνει ένα βιβλίο από τη συλλογή των βιβλίων στα
     * οποία συμμετέχει ο συγγραφέας.
     * @param book Το βιβλίο
     */
    public void removeBook(Book book) {
        if (book != null) {
            book.friendAuthors().remove(this);
            this.books.remove(book);
        }
    }
}
