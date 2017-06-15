package com.mgiandia.library.domain;

/**
 * Η βασική κλάση του προσώπου.
 * @author Νίκος Διαμαντίδης
 */
class Person {
    private String firstName;
    private String lastName;

    /**
     * Προκαθορισμένος κατασκευαστής.
     */
    Person() { }

    /**
     * Βοηθητικός κατασκευαστής που αρχικοποιεί το πρόσωπο.
     * @param firstName Όνομα
     * @param lastName Επώνυμο
     */
    Person(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Θέτει το όνομα.
     * @param firstName Το όνομα
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Επιστρέφει το όνομα.
     * @return Το όνομα
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Θέτει το επώνυμο.
     * @param lastName Το επώνυμο
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Επιστρέφει το επώνυμο.
     * @return Το επώνυμο
     */
    public String getLastName() {
        return lastName;
    }

}
