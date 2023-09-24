package com.mgiandia.library.domain;

import jakarta.persistence.*;

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
            value=com.mgiandia.library.persistence.TelphoneNumberCustomType.class)
    @Column(name="phonenumber")
    private TelephoneNumber telephone;
    
    @org.hibernate.annotations.Type(
            value=com.mgiandia.library.persistence.EMailCustomType.class)
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
    

}
