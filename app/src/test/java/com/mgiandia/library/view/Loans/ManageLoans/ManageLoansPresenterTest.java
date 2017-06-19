package com.mgiandia.library.view.Loans.ManageLoans;

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

public class ManageLoansPresenterTest
{
    private Initializer dataHelper;
    private ManageLoansPresenter presenter;
    private ManageLoansViewStub view;

    @Before
    public void setUp()
    {
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        view = new ManageLoansViewStub();

        view.setAttachedBorrowerID(1);
        presenter = new ManageLoansPresenter(view, dataHelper.getLoanDAO(), dataHelper.getBorrowerDAO());
    }

    @Test
    public void itemClicks()
    {
        presenter.onClickItem(12);

        Assert.assertEquals(view.getAlertTitle(), "Τροποποίηση κατάστασης αντιτύπου");
        Assert.assertEquals(view.getAlertMessage(), "Για να τροποποιήσετε την κατάσταση του αντιτύπου χρησιμοποιήστε την Περίπτωση Χρήσης 'Αντίτυπα'. Για να το επιστρέψετε χρησιμοποιήστε την περίπτωση χρήσης 'Επιστροφές'.");
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
        presenter.onAddNewItem();
        Assert.assertTrue(view.isNewAdded());
    }
}
