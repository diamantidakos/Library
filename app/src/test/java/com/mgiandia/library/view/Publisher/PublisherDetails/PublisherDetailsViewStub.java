package com.mgiandia.library.view.Publisher.PublisherDetails;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class PublisherDetailsViewStub implements PublisherDetailsView {
    private String name, id, toast, phone, email, country,
            addressCity, addressStreet, addressNumber, addressPostal, deleteMessage, booksPublished;

    private int attachedPublisherID, editID, booksId;

    public PublisherDetailsViewStub()
    {
        id = toast = phone = email = country = booksPublished =
                addressCity = addressStreet = addressNumber = addressPostal = deleteMessage = "";

    }

    public String getID() {
        return id;
    }

    public String getDeleteMessage()
    {
        return deleteMessage;
    }

    public void setAttachedPublisherID(int id) {
        attachedPublisherID = id;
    }

    @Override
    public int getAttachedPublisherID() {
        return attachedPublisherID;
    }

    @Override
    public void setID(String value) {
        id = value;
    }

    @Override
    public void setName(String value) {
        name = value;
    }

    public String getName() {
        return name;
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

    @Override
    public void setBooksPublished(String value) {
        booksPublished = value;
    }

    public String getBooksPublished(String value) {
        return booksPublished;
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
        name = value;
    }

    @Override
    public void startEdit(int id) {
        editID = id;
    }

    public int getEditId() {
        return editID;
    }

    @Override
    public void startShowBooks(int id) {
        booksId = id;
    }

    public int getShowBooks() {
        return booksId;
    }

    public String getToast() {
        return toast;
    }

    @Override
    public void showToast(String value) {
        toast = value;
    }
}
