package com.mgiandia.library.ui;

/**
 * Βασική διεπαφή για κάθε όψη (view).
 * @author Νίκος Διαμαντίδης
 *
 */
public interface View {
    /**
     * Ανοίγει την όψη. 
     */
    void open();
    
    /**
     * Κλείνει την όψη. 
     */
    void close();
    
    /**
     * Εμφανίζει μήνυμα σφάλματος.
     * @param message Το μήνυμα που εμφανίζεται
     */
    void showError(String message);
    
    /**
     * Εμφανίζει πληροφοριακό μήνυμα.
     * @param message Το μήνυμα που εμφανίζεται
     */
    void showInfo(String message);
}
