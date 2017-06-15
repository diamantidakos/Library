package com.mgiandia.library.view.Returns.ManageReturns;

import java.util.ArrayList;
import java.util.List;

import com.mgiandia.library.util.Quadruple;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class ManageReturnsViewStub implements ManageReturnsView
{
    private int attachedBorrowerID, refreshCount, selectedId;
    private List<Quadruple> setSource;
    private String toastShown, pageName, alertTitle, alertMessage;

    ManageReturnsViewStub()
    {
        setSource = new ArrayList<Quadruple>();
        toastShown = pageName = "";
    }

    @Override
    public void loadSource(List<Quadruple> input)
    {
        setSource = input;
    }

    @Override
    public void newLoanStateSelectAlert(int uid, String title, String message) {
        selectedId = uid;
        alertTitle = title;
        alertMessage = message;
    }

    @Override
    public void showAlert(String title, String message) {
        alertTitle = title;
        alertMessage = message;
    }

    public String getAlertTitle()
    {
        return alertTitle;
    }

    public String getAlertMessage()
    {
        return alertMessage;
    }

    public int getSelectedId()
    {
        return selectedId;
    }

    @Override
    public void showToast(String value)
    {
        toastShown = value;
    }

    public int getRefreshCount()
    {
        return refreshCount;
    }

    @Override
    public void refresh() {
        refreshCount++;
    }

    public void setAttachedBorrowerID(int id) {
        attachedBorrowerID = id;
    }

    @Override
    public int getAttachedBorrowerID() {
        return attachedBorrowerID;
    }

    public String getPageName() {
        return pageName;
    }

    @Override
    public void setPageName(String value) {
        pageName = value;
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
