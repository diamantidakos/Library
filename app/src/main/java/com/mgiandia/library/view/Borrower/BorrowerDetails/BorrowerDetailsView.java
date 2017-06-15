package com.mgiandia.library.view.Borrower.BorrowerDetails;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public interface BorrowerDetailsView
{
    int getAttachedBorrowerID();

    void setID(String value);
    void setFirstName(String value);
    void setLastName(String value);
    void setCategory(String value);
    void setPhone(String value);
    void setEmail(String value);

    void setCountry(String value);
    void setAddressCity(String value);
    void setAddressStreet(String value);
    void setAddressNumber(String value);
    void setAddressPostalCode(String value);

    void setPageName(String value);

    void startEdit(int borrowerID);
    void startDelete(String title, String message);
    void doDeleteAndFinish(String message);

    void showToast(String value);
}
