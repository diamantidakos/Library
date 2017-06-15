package com.mgiandia.library.view.Borrower.AddEditBorrower;

import java.util.List;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public interface AddEditBorrowerView
{
    String getFirstName();
    String getLastName();
    Integer getCategoryPosition();
    String getPhone();
    String getEmail();
    Integer getCountryPosition();
    String getAddressCity();
    String getAddressStreet();
    String getAddressNumber();
    String getAddressPostalCode();

    Integer getAttachedBorrowerID();

    void setFirstName(String value);
    void setLastName(String value);
    void setCategoryPosition(Integer value);
    void setPhone(String value);
    void setEmail(String value);
    void setCountryPosition(Integer value);
    void setAddressCity(String value);
    void setAddressStreet(String value);
    void setAddressNumber(String value);
    void setAddressPostalCode(String value);

    void setPageName(String value);

    void setCategoryList(List<String> names);
    void setCountryList(List<String> names);

    void successfullyFinishActivity(String message);
    void showErrorMessage(String title, String message);
}
