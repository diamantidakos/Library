package com.mgiandia.library.service;

import com.mgiandia.library.contacts.EmailMessage;

/**
 * Παροχή υπηρεσίας ηλεκτρονικού ταχυδρομείου.
 * @author Νίκος Διαμαντίδης
 *
 */
public interface EmailProvider {
    /**
     * Αποστέλλει μήνυμα ηλεκτρονικού ταχυδρομείου.
     * @param message Το μήνυμα ηλεκτρονικού ταχυδρομείου.
     */
    void sendEmail(EmailMessage message);
}
