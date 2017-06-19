package com.mgiandia.library.view.Book.BookDetails;

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

public class BookDetailsPresenterTest
{
    private Initializer dataHelper;
    private BookDetailsPresenter presenter;
    private BookDetailsViewStub view;

    @Before
    public void setUp()
    {
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        view = new BookDetailsViewStub();
        view.setAttachedBookID(1);
        view.setPageName("page");
        presenter = new BookDetailsPresenter(view, dataHelper.getBookDAO());
    }

    @Test
    public void toastTest()
    {
        presenter.onShowToast("test");
        Assert.assertEquals("test", view.getToast());
    }

    @Test
    public void editButton()
    {
        presenter.onStartEditButtonClick();
        Assert.assertEquals(view.getEditBook(), 1);
    }
}
