package com.mgiandia.library.view.Borrower.ManageBorrowers;

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

public class ManageBorrowerPresenterTest
{
    private Initializer dataHelper;
    private ManageBorrowersPresenter presenter;
    private ManageBorrowerViewStub view;

    @Before
    public void setUp()
    {
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        view = new ManageBorrowerViewStub();
        presenter = new ManageBorrowersPresenter(view, dataHelper.getBorrowerDAO());
    }

    @Test
    public void itemListLoansClicks()
    {
        view.setShouldLoadLoansOnClick(true);
        presenter.onClickItem(12);
        presenter.onClickItem(12);
        presenter.onClickItem(18);
        presenter.onClickItem(17);

        Assert.assertEquals(2, view.getClickItemLoans(12));
        Assert.assertEquals(1, view.getClickItemLoans(17));
        Assert.assertEquals(1, view.getClickItemLoans(18));

        Assert.assertEquals(0, view.getClickItemReturns(12));
        Assert.assertEquals(0, view.getClickItemReturns(17));
        Assert.assertEquals(0, view.getClickItemReturns(18));

        Assert.assertEquals(0, view.getTimesClickedItem(12));
        Assert.assertEquals(0, view.getTimesClickedItem(17));
        Assert.assertEquals(0, view.getTimesClickedItem(18));
    }

    @Test
    public void itemListReturnsClicks()
    {
        view.setShouldLoadReturnsOnClick(true);
        presenter.onClickItem(12);
        presenter.onClickItem(12);
        presenter.onClickItem(18);
        presenter.onClickItem(17);

        Assert.assertEquals(2, view.getClickItemReturns(12));
        Assert.assertEquals(1, view.getClickItemReturns(17));
        Assert.assertEquals(1, view.getClickItemReturns(18));

        Assert.assertEquals(0, view.getClickItemLoans(12));
        Assert.assertEquals(0, view.getClickItemLoans(17));
        Assert.assertEquals(0, view.getClickItemLoans(18));

        Assert.assertEquals(0, view.getTimesClickedItem(12));
        Assert.assertEquals(0, view.getTimesClickedItem(17));
        Assert.assertEquals(0, view.getTimesClickedItem(18));
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

        Assert.assertEquals(0, view.getClickItemLoans(12));
        Assert.assertEquals(0, view.getClickItemLoans(17));
        Assert.assertEquals(0, view.getClickItemLoans(18));

        Assert.assertEquals(0, view.getClickItemReturns(12));
        Assert.assertEquals(0, view.getClickItemReturns(17));
        Assert.assertEquals(0, view.getClickItemReturns(18));
    }

    @Test
    public void showToast()
    {
        Assert.assertEquals("", view.getToast());
        presenter.onShowToast("hello");
        Assert.assertEquals("hello", view.getToast());
    }

    @Test
    public void sourceTestEmpty()
    {
        presenter.onLoadSource();
        Assert.assertEquals(8, view.getSource().size());
    }

    @Test
    public void sourceTestAuthor()
    {
        presenter.onLoadSource();
        Assert.assertEquals(8, view.getSource().size());
    }

    @Test
    public void sourceTestPublisher()
    {
        presenter.onLoadSource();
        Assert.assertEquals(8, view.getSource().size());
    }

    @Test
    public void startAdd()
    {
        Assert.assertFalse(view.isNewAdded());
        presenter.onStartAddNew();
        Assert.assertTrue(view.isNewAdded());
    }
}
