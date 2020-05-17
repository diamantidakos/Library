package com.mgiandia.library.dao;

import com.mgiandia.library.contacts.EmailAddress;
import com.mgiandia.library.domain.*;
import com.mgiandia.library.util.Money;


/**
 * Κλάση που αναλαμβάνει να αρχικοποιήσει τα δεδομένα της βάσης δεδομένων<p>
 * Βοηθητική κλάση που παρέχει δεδομένα για τα παραδείγματα και τις δοκιμασίες ελέγχου<p>
 * Βιβλία
 * <p>
 * Booch, G.,  Rumbaugh, J., Jacobson I., The Unified Modeling Language User Guide , 2nd ed., Addison Wesley, 2005 
 * <p>
 * Fowler, M., UML Distilled, 3rd ed., Addison-Wesley, 2004
 * <p>
 * Fowler, M., Refactoring: Improving the Design of Existing Code. Addison-Wesley, 1999
 * <p>
 * Αντίτυπα 
 * <p>
 * 1 - The Unified Modeling Language User Guide
 * <p>
 * 2 -  UML Distilled
 * <p>
 * 3 - Refactoring: Improving the Design of Existing Code
 * <p>
 * 4 - The Unified Modeling Language User Guide
 * <p>
 * 5 - UML Distilled
 * <p>
 * Δανειζόμενοι
 * <p>
 * 1, Μανόλης Γιακουμάκης, Καθηγητής
 * <p>
 * 2, Νίκος Διαμαντίδης, Φοιτητής
 * <p>
 *@author Νίκος Διαμαντίδης
 *    
 */
public abstract class Initializer  {
    public static final int GIAKOUMAKIS_ID = 1;
    public static final int DIAMANTIDIS_ID = 2;
    
    public static int UML_USER_GUIDE_ID1 = 1;
    public static int UML_DISTILLED_ID1 = 2;
    public static int UML_REFACTORING_ID = 3;
    public static int UML_USER_GUIDE_ID2 = 4;
    public static int UML_DISTILLED_ID2 = 5;
    
    //διαγράφουμε όλα τα δεδομένα στη βάση δεδομένων
    protected abstract void eraseData();
    
    
  
    
    public void prepareData() {
        // πριν εισάγουμε τα δεδομένα διαγράφουμε ότι υπάρχει
        eraseData();                      
        Author booch = new Author("Booch", "Grady");
        Author rumbaugh = new Author("Rumbaugh", "James");
        Author jacobson = new Author("Jacobson", "Ivar");
        Author fowler = new Author("Fowler", "Martin");
        
        Publisher addisonWesley = new Publisher("Addison Wesley", null, null, null);
        
        Book umlUserGuide = new Book("The Unified Modeling Language User Guide", 
                new ISBN("1"), "2", 2005, addisonWesley);
        umlUserGuide.addAuthor(booch);
        umlUserGuide.addAuthor(rumbaugh);
        umlUserGuide.addAuthor(jacobson);
        
        
        Book umlDistilled = new Book("UML Distilled", new ISBN("2"), "3", 2004, addisonWesley);
        umlDistilled.addAuthor(fowler);
        
        Book refactoring = new Book("Refactoring: Improving the Design of Existing Code", 
                new ISBN("3"), "1", 1999, addisonWesley);
        
        
        Item umlUserGuideItem1 = new Item(UML_USER_GUIDE_ID1);
        umlUserGuideItem1.setBook(umlUserGuide);
        umlUserGuideItem1.available();
        
        
        Item umlDistilledItem2 = new Item(UML_DISTILLED_ID1);
        umlDistilledItem2.setBook(umlDistilled);
        umlDistilledItem2.available();
        
        Item refactoringItem3 = new Item(UML_REFACTORING_ID);
        refactoringItem3.setBook(refactoring);
        refactoringItem3.available();
        
        Item umlUserGuideItem4 = new Item(UML_USER_GUIDE_ID2);
        umlUserGuideItem4.setBook(umlUserGuide);
        umlUserGuideItem4.available();
        
        Item umlDistilledItem5 = new Item(UML_DISTILLED_ID2);
        umlDistilledItem5.setBook(umlDistilled);
        umlDistilledItem5.available();
        
        getItemDAO().save(umlUserGuideItem1);
        getItemDAO().save(umlDistilledItem2);                
        getItemDAO().save(refactoringItem3);
        getItemDAO().save(umlUserGuideItem4);
        getItemDAO().save(umlDistilledItem5);
        
        
        BorrowerCategory professor = new BorrowerCategory("Καθηγητής", 180 , 6, Money.euros(0));
        BorrowerCategory undergraduate = new BorrowerCategory("Φοιτητής", 7 , 4, Money.euros(5));
        
        Borrower mgia = new Borrower(GIAKOUMAKIS_ID,"Μανόλης", "Γιακουμάκης", null, new EmailAddress("mgia@aueb.gr"), null);
        mgia.setCategory(professor);
        
        Borrower ndia = new Borrower(DIAMANTIDIS_ID, "Νίκος", "Διαμαντίδης",null, new EmailAddress("nad@aueb.gr"), null);
        ndia.setCategory(undergraduate);
        
        getBorrowerDAO().save(mgia);
        getBorrowerDAO().save(ndia);                                
    }    
    
    
    protected abstract BorrowerDAO getBorrowerDAO();
    protected abstract ItemDAO getItemDAO();
    protected abstract LoanDAO getLoanDAO();
    
}
