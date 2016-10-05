package com.mgiandia.library.domain;

import javax.persistence.*;

import com.mgiandia.library.contacts.Address;
import com.mgiandia.library.contacts.EmailAddress;
import com.mgiandia.library.contacts.TelephoneNumber;


/**
 * Ο εκδοτικός οίκος.
 * @author Νίκος Διαμαντίδης
 *
 */
@Entity
@Table(name="publishers")
public class Publisher {
    @Id 
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(name="name",length=50, nullable = false)
    private String name;
    
    @org.hibernate.annotations.Type(
            type="com.mgiandia.library.persistence.TelphoneNumberCustomType")
    @Column(name="phonenumber")
    private TelephoneNumber telephone;
    
    @org.hibernate.annotations.Type(
            type="com.mgiandia.library.persistence.EMailCustomType")
    @Column(name="email")
    private EmailAddress eMail;
    
    @Embedded
    private Address address;

    /**
     * Προκαθορισμένος κατασκευαστής.
     */
    public Publisher() { }

    /**
     * Βοηθητικός κατασκευαστής. 
     * Αρχικοποιεί την κατάσταση ενός εκδοτικού οίκου.
     * @param name Όνομα εκδοτικού οίκου
     * @param address Ταχυδρομική διεύθυνση εκδοτικού οίκου
     * @param eMail Διεύθυνση ηλεκτρονικού ταχυδρομείου
     * @param telephone Αριθμός τηλεφώνου
     */
    public Publisher(String name, Address address,
            EmailAddress eMail, TelephoneNumber telephone) {
        this.name = name;
        this.address = address == null ? null : new Address(address);
        this.eMail = eMail;
        this.telephone = telephone;
    }

    
    public Integer getId() {
        return id;
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

}
