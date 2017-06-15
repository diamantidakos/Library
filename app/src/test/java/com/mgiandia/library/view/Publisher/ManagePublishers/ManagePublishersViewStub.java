package com.mgiandia.library.view.Publisher.ManagePublishers;

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

public class ManagePublishersViewStub implements ManagePublishersView
{
    private Map<Integer, Integer> clicksCount;
    private boolean addedNew;
    private List<Quadruple> setSource;
    private String toastShown;

    ManagePublishersViewStub()
    {
        clicksCount = new HashMap<Integer, Integer>();
        addedNew = false;
        setSource = new ArrayList<Quadruple>();
        toastShown = "";
    }

    @Override
    public void clickItem(int uid)
    {
        clicksCount.put(uid, (clicksCount.containsKey(uid) ? clicksCount.get(uid) : 0)+1);
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

    public String getToast()
    {
        return toastShown;
    }
}
