package com.mgiandia.library.domain;

import java.util.HashSet;
import java.util.Set;

import com.mgiandia.library.contacts.Address;
import com.mgiandia.library.contacts.EmailAddress;
import com.mgiandia.library.contacts.TelephoneNumber;

/**
 * Ο εκδοτικός οίκος.
 * @author Νίκος Διαμαντίδης
 *
 */
public class Publisher {
    private int uid;
    private String name;
    private TelephoneNumber telephone;
    private EmailAddress eMail;
    private Address address;

    private Set<Book> books = new HashSet<Book>();

    /**
     * Προκαθορισμένος κατασκευαστής.
     */
    public Publisher() { }

    /**
     * Βοηθητικός κατασκευαστής που
     * αρχικοποιεί την κατάσταση ενός εκδοτικού οίκου.
     * @param uid Ο μοναδικός κωδικός
     * @param name Όνομα εκδοτικού οίκου
     * @param address Ταχυδρομική διεύθυνση εκδοτικού οίκου
     * @param eMail Διεύθυνση ηλεκτρονικού ταχυδρομείου
     * @param telephone Αριθμός τηλεφώνου
     */
    public Publisher(int uid, String name, Address address,
            EmailAddress eMail, TelephoneNumber telephone) {

        this.uid = uid;
        this.name = name;
        this.address = address == null ? null : new Address(address);
        this.eMail = eMail;
        this.telephone = telephone;
    }

    /**
     * Επιστρέφει τον μοναδικό κωδικό του εκδοτικού οίκου.
     * @return Ο μοναδικός κωδικός του εκδοτικού οίκου
     */
    public int getId() {
        return uid;
    }

    /**
     * Θέτει το όνομα.
     * @param name Το όνομα
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Επιστρέφει το όνομα.
     * @return Το όνομα
     */
    public String getName() {
        return name;
    }

    /**
     * Θέτει τον αριθμό τηλεφώνου.
     * @param telephone Ο αριθμός τηλεφώνου.
     */
    public void setTelephone(TelephoneNumber telephone) {
        this.telephone = telephone;
    }

    /**
     * Επιστρέφει τον αριθμό τηλεφώνου.
     * @return Ο αριθμός τηλεφώνου.
     */
    public TelephoneNumber getTelephone() {
        return telephone;
    }

    /**
     * Θέτει την διεύθυνση ηλεκτρονικού ταχυδρομείου.
     * @param eMail Η διεύθυνση ηλεκτρονικού ταχυδρομείου
     */
    public void setEMail(EmailAddress eMail) {
        this.eMail = eMail;
    }

    /**
     * Επιστρέφει τη διεύθυνση ηλεκτρονικού ταχυδρομείου.
     * @return Η διεύθυνση ηλεκτρονικού ταχυδρομείου.
     */
    public EmailAddress getEMail() {
        return eMail;
    }

    /**
     * Θέτει την ταχυδρομική διεύθυνση.
     * @param address Η διεύθυνση
     */
    public void setAddress(Address address) {
        this.address = address == null ? null : new Address(address);
    }

    /**
     * Επιστρέφει την ταχυδρομική διεύθυνση.
     * @return Η διεύθυνση
     */
    public Address getAddress() {
        return this.address == null ? null : new Address(address);
    }

    public Set<Book> getBooks() {
        return new HashSet<Book>(books);
    }

    public void addBook(Book book)
    {
        if (book != null)
        {
            books.add(book);
        }
    }

    public void removeBook(Book book)
    {
        if (book != null) {
            books.remove(book);
        }
    }
}
