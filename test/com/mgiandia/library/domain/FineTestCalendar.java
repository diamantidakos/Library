package com.mgiandia.library.domain;

import com.mgiandia.library.util.SimpleCalendar;


/**
 * Βοηθητική κλάση που παρέχει τις ημερομηνίες για τον έλεγχο της στρατηγικής προστίμου. 
 * Ελέγχονται διαφορετικές στρατηγικές προστίμου με τις ίδιες ημερομηνίες. <p> 
 * Οι ημερομηνίες που παρέχονται βασίζονται στις αρχές της ανάλυσης των συνοριακών τιμών
 * 
 * Ως προθεσμία επιστροφής επιλέγεται η 1 Μαρτίου 2007 (ημέρα Πέμπτη).
 * Η ανάλυση συνοριακών τιμών παίρνει ως δεδομένη την προθεσμία επιστροφής και αναζητά
 * ημερομηνίες στα σύνορα του ελέγχου.
 * Στα σύνορα της 1ης Μαρτίου είναι η 2α Μαρτίου και η 28η Φεβρουαρίου. Δηλαδή ένα αντίτυπο επιστρέφεται
 * μία ημέρα πριν ή μία ημέρα μετά την προθεσμία επιστροφής. Επειδή κάποιες από τις στρατηγικές λαμβάνουν
 * υπόψη και την εβδομάδα της επιστοφής τότε επιλέγουμε δύο ακόμη ημερομηνίες για έλεγχο. Την 4η Μαρτίου, ημέρα
 * Κυριακή ως η τελευταία ημέρα της εβδομάδας που γίνεται η επιστροφή,  η 5η Μαρτίου ως η πρώτη ημέρα της 
 * επόμενης εβομάδας και τέλος η 11 Μαρτίου ως η τελευταία ημέρα της επόμενης εβδομάδας. 
 * 
 * @author Νίκος Διαμαντίδης
 * @see "Για την ανάλυση συνοριακών τιμών βλέπε ενότητα 10.2.4 του βιβλίου"
 */
public class FineTestCalendar {

    static SimpleCalendar get1stMarchOf2007() {
        return new SimpleCalendar(2007,3,1);
    }
    
    static SimpleCalendar get2ndMarchOf2007() {
        return new SimpleCalendar(2007,3,2);
    }
    
    
    static SimpleCalendar get28thFebruaryOf2007() {
        return new SimpleCalendar(2007,2,28);
    }
    

    static SimpleCalendar get4thMarchOf2007() {
        return new SimpleCalendar(2007,3,4);
    }

    static SimpleCalendar get5thMarchOf2007() {
        return new SimpleCalendar(2007,3,5);
    }


    static SimpleCalendar get11thMarchOf2007() {
        return new SimpleCalendar(2007,3,11);
    }
}
