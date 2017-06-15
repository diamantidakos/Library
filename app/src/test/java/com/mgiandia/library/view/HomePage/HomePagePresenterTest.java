package com.mgiandia.library.view.HomePage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class HomePagePresenterTest
{
    private HomePagePresenter presenter;
    private HomePageViewStub view;

    @Before
    public void setUp()
    {
        view = new HomePageViewStub();
        presenter = new HomePagePresenter(view);
        view.setPresenter(presenter);
    }

    @Test
    public void testManageBorrowers()
    {
        for(int i = 0; i < 10; i++) {
            presenter.onManageBorrowers();
        }

        Assert.assertEquals(10, view.getManageBorrowersClicks());
    }

    @Test
    public void testManageBooks()
    {
        for(int i = 0; i < 11; i++) {
            presenter.onManageBooks();
        }

        Assert.assertEquals(11, view.getManageBooksClicks());
    }

    @Test
    public void testManageAuthors()
    {
        for(int i = 0; i < 12; i++) {
            presenter.onManageAuthors();
        }

        Assert.assertEquals(12, view.getManageAuthorsClicks());
    }

    @Test
    public void testManagePublishers()
    {
        for(int i = 0; i < 13; i++) {
            presenter.onManagePublishers();
        }

        Assert.assertEquals(13, view.getManagePublishersClicks());
    }

    @Test
    public void testManageItems()
    {
        for(int i = 0; i < 14; i++) {
            presenter.onManageItems();
        }

        Assert.assertEquals(14, view.getManageItemsClicks());
    }

    @Test
    public void testManageLoans()
    {
        for(int i = 0; i < 15; i++) {
            presenter.onManageLoans();
        }

        Assert.assertEquals(15, view.getManageLoansClicks());
    }

    @Test
    public void testManageReturns()
    {
        for(int i = 0; i < 16; i++) {
            presenter.onManageReturns();
        }

        Assert.assertEquals(16, view.getManageReturnsClicks());
    }
}
