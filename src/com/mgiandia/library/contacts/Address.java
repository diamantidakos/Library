package com.mgiandia.library.contacts;

import javax.persistence.*;

/**
 * Η ταχυδρομική διεύθυνση.
 * @author Νίκος Διαμαντίδης
 *
 */
@Embeddable
public class Address {
    @Column(name="street", length=50)
    private String street;
    
    @Column(name="number", length = 10)
    private String number;
    
    @Column(name="city", length = 50)
    private String city;
    
    @org.hibernate.annotations.Type(
            type="com.mgiandia.library.persistence.ZipCodeCustomType")
    private ZipCode zipcode;
    
    @Column(name="country", length=50)
    private String country = "Ελλάδα";

    /**
     * Προκαθορισμένος κατασκευαστής.
     */
    public Address() { }

    /**
     * Βοηθητικός κατασκευαστής που αντιγράφει κάποιας άλλη διεύθυνση.
     * @param address Η άλλη διεύθυνση
     */
    public Address(Address address) {
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.city = address.getCity();
        this.zipcode = address.getZipCode();
        this.country = address.getCountry();
    }

    /**
     * Θέτει την οδό.
     * @param street Η οδός
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Επιστρέφει την οδό.
     * @return Η οδός
     */
    public String getStreet() {
        return street;
    }

    /**
     * Θέτει τον αριθμό.
     * @param number Ο αριθμός
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Επιστρέφει τον αριθμό.
     * @return Ο αριθμός
     */
    public String getNumber() {
        return number;
    }

    /**
     * Θέτει την πόλη.
     * @param city Η πόλη
     */
    public void setCity(String city) {
        this.city = city;
    }


    /**
     * Επιστρέφει την πόλη.
     * @return Η πόλη
     */
    public String getCity() {
        return city;
    }

    /**
     * Θέτει τον ταχυδρομικό κώδικα.
     * @param zipcode Ο ταχυδρομικός κώδικας
     */
    public void setZipCode(ZipCode zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * Επιστρέφει τον ταχυδρομικό κώδικα.
     * @return Ο ταχυδρομικός κώδικας
     */
    public ZipCode getZipCode() {
        return zipcode;
    }

    /**
     * Θέτει τη χώρα.
     * @param country Η χώρα
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Επιστρέφει τη χώρα.
     * @return Η χώρα
     */
    public String getCountry() {
        return country;
    }

    /**
     * Η ισότητα βασίζεται σε όλα τα πεδία της διεύθυνσης.
     * @param other Το άλλο αντικείμενο προς έλεγχο
     * @return  {@code true} αν τα αντικείμενα είναι ίσα
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (this == other) {
            return true;
        }
        if (!(other instanceof Address)) {
            return false;
        }

        Address theAddress = (Address) other;
        if (!(street == null ? theAddress.street 
                == null : street.equals(theAddress.street))) {
            return false;
        }
        if (!(number == null ? theAddress.number 
                == null : number.equals(theAddress.number))) {
            return false;
        }
        if (!(city == null ? theAddress.city 
                == null : city.equals(theAddress.city))) {
            return false;
        }
        if (!(zipcode == null ? theAddress.zipcode
                == null : zipcode.equals(theAddress.zipcode))) {
            return false;
        }
        if (!(country == null ? theAddress.country
                == null : country.equals(theAddress.country))) {
            return false;
        }
        return true;
    }


    @Override
    public int hashCode() {
        if (street == null && number == null && city == null
                && zipcode == null && country == null) {
            return 0;
        }

        int result = 0;
        result = street == null ? result : 13 * result + street.hashCode(); 
        result = number == null ? result : 13 * result + number.hashCode();
        result = city == null ? result : 13 * result + city.hashCode();
        result = zipcode == null ? result : 13 * result + zipcode.hashCode();
        result = country == null ? result : 13 * result + country.hashCode();
        return result;
    }

}
