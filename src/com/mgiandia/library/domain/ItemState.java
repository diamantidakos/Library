package com.mgiandia.library.domain;

/**
 * Οι διαφορετικές καταστάσεις ενός αντιτύπου.
 * @author Νίκος Διαμαντίδης
 */
public enum ItemState {

    /**
     * Το αντίτυπο είναι ένα νέο αντίτυπο.
     */
    NEW,

    /**
     * Το αντίτυπο είναι διαθέσιμο για δανεισμό.
     */
    AVAILABLE,
    /**
     * Το αντίτυπο έχει δανειστεί.
     */
    LOANED,
    /**
     * Το αντίτυπο έχει χαθεί και δεν είναι διαθέσιμο για δανεισμό.
     */
    LOST,
    /**
     * Το αντίτυπο έχει αποσυρθεί και δεν είναι διαθέσιμο για δανεισμό.
     */
    WITHDRAWN
}