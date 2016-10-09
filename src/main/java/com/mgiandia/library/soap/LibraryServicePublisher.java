package com.mgiandia.library.soap;

import javax.xml.ws.Endpoint;

import com.mgiandia.library.persistence.Initializer;


public class LibraryServicePublisher {
    public static void main(String[ ] args) {
        //Πρέπει πρώτα να θέσουμε το σωστό εργοστάσιο
        System.setProperty("daofactory", "com.mgiandia.library.jpadao.JpaDAOFactory");

        //HACK αρχικοποιούμε τη βάση δεδομένων και μάλιστα με κώδικα που ανήκει
        // στον κώδικα ελέγχου. Κανονικά δεν θα πρέπει να υπάρχει κάτι τέτοιο
        Initializer dataHelper = new Initializer();
        dataHelper.prepareData();
        
        // Η πρώτη παράμετρος είναι το URL που δημοσιεύεται η υπηρεσία
        // Η δεύτερη παράμετρος είναι το SIB δηλ. η κλάση που υλοποιεί το SEI
        Endpoint.publish("http://localhost:9877/library", new LibraryServiceImpl());
      }

}
