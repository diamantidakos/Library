package com.mgiandia.library.view.Publisher.ManagePublishers;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.mgiandia.library.dao.Initializer;
import com.mgiandia.library.memorydao.MemoryInitializer;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class ManagePublishersPresenterTest
{
    private Initializer dataHelper;
    private ManagePublishersPresenter presenter;
    private ManagePublishersViewStub view;

    @Before
    public void setUp()
    {
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        view = new ManagePublishersViewStub();
        presenter = new ManagePublishersPresenter(view, dataHelper.getPublisherDAO());
    }

    @Test
    public void itemClicks()
    {
        presenter.onClickItem(12);
        presenter.onClickItem(12);
        presenter.onClickItem(18);
        presenter.onClickItem(17);

        Assert.assertEquals(2, view.getTimesClickedItem(12));
        Assert.assertEquals(1, view.getTimesClickedItem(17));
        Assert.assertEquals(1, view.getTimesClickedItem(18));
    }

    @Test
    public void showToast()
    {
        Assert.assertEquals("", view.getToast());
        presenter.onShowToast("hello");
        Assert.assertEquals("hello", view.getToast());
    }

    @Test
    public void sourceTest()
    {
        presenter.onLoadSource();
        Assert.assertEquals(2, view.getSource().size());
    }

    @Test
    public void startAdd()
    {
        Assert.assertFalse(view.isNewAdded());
        presenter.onStartAddNew();
        Assert.assertTrue(view.isNewAdded());
    }
}
