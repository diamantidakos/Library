package com.mgiandia.library.view.Borrower.ManageBorrowers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mgiandia.library.util.Quadruple;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class ManageBorrowerViewStub implements ManageBorrowersView
{
    private Map<Integer, Integer> clicksCount, listClicksLoans, listClicksReturns;
    private List<Quadruple> setSource;
    private String toastShown;
    private boolean addedNew, loadLoansOnClick, loadReturnsOnClick;

    ManageBorrowerViewStub()
    {
        clicksCount = new HashMap<Integer, Integer>();
        listClicksLoans = new HashMap<Integer, Integer>();
        listClicksReturns = new HashMap<Integer, Integer>();
        addedNew = loadLoansOnClick = loadReturnsOnClick = false;
        setSource = new ArrayList<Quadruple>();
        toastShown = "";
    }

    @Override
    public void clickItem(int uid)
    {
        clicksCount.put(uid, (clicksCount.containsKey(uid) ? clicksCount.get(uid) : 0)+1);
    }

    @Override
    public void clickItemLoans(int uid) {
        listClicksLoans.put(uid, (listClicksLoans.containsKey(uid) ? listClicksLoans.get(uid) : 0)+1);
    }

    @Override
    public void clickItemReturns(int uid) {
        listClicksReturns.put(uid, (listClicksReturns.containsKey(uid) ? listClicksReturns.get(uid) : 0)+1);
    }

    @Override
    public void startAddNew()
    {
        addedNew = true;
    }

    @Override
    public void loadSource(List<Quadruple> input)
    {
        setSource = input;
    }

    public void setShouldLoadLoansOnClick(boolean val) {
        loadLoansOnClick = val;
    }

    public void setShouldLoadReturnsOnClick(boolean val) {
        loadReturnsOnClick = val;
    }

    @Override
    public boolean shouldLoadLoansOnClick() {
        return loadLoansOnClick;
    }

    @Override
    public boolean shouldLoadReturnsOnClick() {
        return loadReturnsOnClick;
    }

    @Override
    public void showToast(String value)
    {
        toastShown = value;
    }

    public boolean isNewAdded()
    {
        return addedNew;
    }

    public List<Quadruple> getSource()
    {
        return setSource;
    }

    public int getTimesClickedItem(int uid)
    {
        return clicksCount.containsKey(uid) ? clicksCount.get(uid) : 0;
    }

    public int getClickItemLoans(int uid) {
        return listClicksLoans.containsKey(uid) ? listClicksLoans.get(uid) : 0;
    }

    public int getClickItemReturns(int uid) {
        return listClicksReturns.containsKey(uid) ? listClicksReturns.get(uid) : 0;
    }

    public String getToast()
    {
        return toastShown;
    }
}
