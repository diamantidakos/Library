package com.mgiandia.library.service;

import java.util.ArrayList;
import java.util.List;


import com.mgiandia.library.contacts.EmailMessage;

/**
 * Η κλάση είναι ένα στέλεχος που χρησιμοποιείται αντί
 * μίας πραγματικής υπηρεσίας ηλεκτρονικού ταχυδρομείου.
 * Υπάρχει ένας κατάλογος μηνυμάτων. Σε κάθε κλήσης 
 * της μεθόδου {@code sendEmail} προστίθεται το μήνυμα
 * στον κατάλογο. Ο κατάλογος επιστρέφεται με τη 
 * μέθοδο {@code getAllEmails} για την εκτέλεση ανάλογων
 * ισχυρισμός κατά τον έλεγχο.
 *  
 * @author Νίκος Διαμαντίδης
 */
public class EmailProviderStub implements EmailProvider{

    List<EmailMessage> allMessages = new ArrayList<EmailMessage>(); 
    
   
    public List<EmailMessage> getAllEmails() {
        return allMessages;
    }

    public void sendEmail(EmailMessage message) {        
        allMessages.add(message);
    }
}
