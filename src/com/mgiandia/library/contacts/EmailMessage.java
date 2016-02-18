package com.mgiandia.library.contacts;

/**
 * Μήνυμα ηλεκτρονικού ταχυδρομείου.
 * Ένα μήνυμα ηλεκτρονικού ταχυδρομείου συγκροτείται από
 * τον αποστολέα, τον παραλήπτη, το θέμα και
 * το κείμενο (σώμα) του μηνύματος.
 * @author Νίκος Διαμαντίδης
 *
 */
public class EmailMessage {
    private EmailAddress from;
    private EmailAddress to;
    private String subject;
    private String body;
    
    /**
     * Θέτει ον αποστολέα του μηνύματος.
     * @param from Ο αποστολέας του μηνύματος.
     */
    public void setFrom(EmailAddress from) {
        this.from = from;
    }

    /**
     * Επιστρέφει τον αποστολέα του μηνύματος.
     * @return Ο αποστολέας του μηνύματος.
     */
    public EmailAddress getFrom() {
        return from;
    }


    /**
     * Θέτει τον παραλήπτη του μηνύματος.
     * @param to Ο παραλήπτης.
     */
    public void setTo(EmailAddress to) {
        this.to = to;
    }

    /**
     * Επιστρέφει τον παραλήπτη του μηνύματος.
     * @return Ο παραλήπτης
     */
    public EmailAddress getTo() {
        return to;
    }
    
    /**
     * Θέτει το θέμα του μηνύματος.
     * @param subject Το θέμα του μηνύματος.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }


    /**
     * Επιστρέφει το θέμα του μηνύματος.
     * @return Το θέμα του μηνύματος.
     */
    public String getSubject() {
        return subject;
    }


    /**
     * Θέτει το κείμενο του μηνύματος.
     * @param body Το κείμενο του μηνύματος.
     */
    public void setBody(String body) {
        this.body = body;
    }


    /**
     * Επιστρέφει το κείμενο του μηνύματος.
     * @return Το κείμενο του μηνύματος.
     */
    public String getBody() {
        return body;
    }

    
    /**
     * Επισυνάπτει κείμενο στο τέλος του μηνύματος.
     * @param text Το κείμενο που επισυνάπτεται στο τέλος του μηνύματος.
     */
    public void appendToBody(String text) {
        subject += text;
    }
}
