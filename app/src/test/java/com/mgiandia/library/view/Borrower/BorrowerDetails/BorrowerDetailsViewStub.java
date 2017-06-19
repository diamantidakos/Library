package com.mgiandia.library.view.Borrower.BorrowerDetails;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class BorrowerDetailsViewStub implements BorrowerDetailsView {
    private String firstName, id, toast, pageName, lastName, category, phone, email, country,
            addressCity, addressStreet, addressNumber, addressPostal, deleteMessage;
    private int attachedBorrowerID, editBorrowerID;

    public BorrowerDetailsViewStub()
    {
        firstName = id = toast = pageName = lastName = category = phone = email = country =
                addressCity = addressStreet = addressNumber = addressPostal = deleteMessage = "";

    }

    public String getID() {
        return id;
    }

    public void setAttachedBorrowerID(int id) {
        attachedBorrowerID = id;
    }

    public String getDeleteMessage()
    {
        return deleteMessage;
    }

    @Override
    public int getAttachedBorrowerID() {
        return attachedBorrowerID;
    }

    @Override
    public void setID(String value) {
        id = value;
    }

    @Override
    public void setFirstName(String value) {
        firstName = value;
    }

    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setLastName(String value) {
        lastName = value;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public void setCategory(String value) {
        category = value;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public void setPhone(String value) {
        phone = value;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public void setEmail(String value) {
        email = value;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public void setCountry(String value) {
        country = value;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public void setAddressCity(String value) {
        addressCity = value;
    }

    public String getAddressCity() {
        return addressCity;
    }

    @Override
    public void setAddressStreet(String value) {
        addressStreet = value;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    @Override
    public void setAddressNumber(String value) {
        addressNumber = value;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    @Override
    public void setAddressPostalCode(String value) {
        addressPostal = value;
    }

    public String getAddressPostalCode() {
        return addressPostal;
    }

    @Override
    public void setPageName(String value) {
        pageName = value;
    }

    @Override
    public void startEdit(int borrowerID) {
        editBorrowerID = borrowerID;
    }

    @Override
    public void startDelete(String title, String message) {
        deleteMessage = message;
    }

    @Override
    public void doDeleteAndFinish(String message) {
        deleteMessage = message;
    }

    public String getToast() {
        return toast;
    }

    @Override
    public void showToast(String value) {
        toast = value;
    }
}
