package com.mgiandia.library.view.Author.AddEditAuthor;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class AddEditAuthorViewStub implements AddEditAuthorView
{
    private String firstName, lastName, pageName, errorTitle, errorMessage, finishMessage;
    private Integer attachedAuthorID;

    private AddEditAuthorPresenter presenter;

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setAttachedAuthorID(Integer val)
    {
        attachedAuthorID = val;
    }

    public Integer getAttachedAuthorID()
    {
        return attachedAuthorID;
    }

    public void setFirstName(String value)
    {
        firstName = value;
    }

    public void setLastName(String value)
    {
        lastName = value;
    }

    public void setPageName(String value)
    {
        pageName = value;
    }

    public void setPresenter(AddEditAuthorPresenter presenter) {
        this.presenter = presenter;
    }

    public AddEditAuthorPresenter getPresenter() {
        return presenter;
    }

    public AddEditAuthorViewStub()
    {
        firstName = lastName = pageName = errorTitle = errorMessage = finishMessage = "";
    }

    public String getPageName()
    {
        return pageName;
    }

    public String getErrorTitle()
    {
        return errorTitle;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public String getFinishMessage()
    {
        return finishMessage;
    }

    public void successfullyFinishActivity(String message)
    {
        finishMessage = message;
    }

    public void showErrorMessage(String title, String message)
    {
        errorTitle = title;
        errorMessage = message;
    }
}
