package com.mgiandia.library.view.Loans.ManageLoans;

import java.util.ArrayList;
import java.util.List;

import com.mgiandia.library.util.Quadruple;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class ManageLoansViewStub implements ManageLoansView
{
    private String pageName, alertTitle, alertMessage;
    private int borrowerID;
    private boolean addedNew;
    private List<Quadruple> setSource;
    private String toastShown;

    ManageLoansViewStub()
    {
        addedNew = false;
        setSource = new ArrayList<Quadruple>();
        toastShown = pageName = "";
    }

    public String getAlertTitle() {
        return alertTitle;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    @Override
    public void loadSource(List<Quadruple> input)
    {
        setSource = input;
    }

    @Override
    public void startAddNew(int uid) {
        addedNew = true;
    }

    @Override
    public void showAlert(String title, String message)
    {
        alertTitle = title;
        alertMessage = message;
    }

    @Override
    public void showToast(String value)
    {
        toastShown = value;
    }

    public void setAttachedBorrowerID(int id) {
        borrowerID = id;
    }

    @Override
    public int getAttachedBorrowerID() {
        return borrowerID;
    }

    public String getPageName()
    {
        return pageName;
    }

    @Override
    public void setPageName(String value)
    {
        pageName = value;
    }

    public boolean isNewAdded()
    {
        return addedNew;
    }

    public List<Quadruple> getSource()
    {
        return setSource;
    }

    public String getToast()
    {
        return toastShown;
    }
}
