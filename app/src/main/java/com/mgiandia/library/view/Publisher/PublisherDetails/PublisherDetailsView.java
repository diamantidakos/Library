package com.mgiandia.library.view.Publisher.PublisherDetails;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public interface PublisherDetailsView
{
    int getAttachedPublisherID();

    void setID(String value);
    void setName(String value);
    void setPhone(String value);
    void setEmail(String value);
    void setBooksPublished(String value);

    void setCountry(String value);
    void setAddressCity(String value);
    void setAddressStreet(String value);
    void setAddressNumber(String value);
    void setAddressPostalCode(String value);

    void setPageName(String value);

    void startEdit(int publisherID);
    void startShowBooks(int publisherID);

    void showToast(String value);
}
