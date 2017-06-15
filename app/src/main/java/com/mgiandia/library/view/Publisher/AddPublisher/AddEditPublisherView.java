package com.mgiandia.library.view.Publisher.AddPublisher;

import java.util.List;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public interface AddEditPublisherView
{
    String getName();
    String getPhone();
    String getEmail();
    Integer getCountryPosition();
    String getAddressCity();
    String getAddressStreet();
    String getAddressNumber();
    String getAddressPostalCode();

    Integer getAttachedPublisherID();

    void setName(String value);
    void setPhone(String value);
    void setEmail(String value);
    void setCountryPosition(Integer value);
    void setAddressCity(String value);
    void setAddressStreet(String value);
    void setAddressNumber(String value);
    void setAddressPostalCode(String value);

    void setPageName(String value);

    void setCountryList(List<String> names);

    void successfullyFinishActivity(String message);
    void showErrorMessage(String title, String message);
}
