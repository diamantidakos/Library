package com.mgiandia.library.view.Returns.ManageReturns;

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

public class ManageReturnsPresenterTest
{
    private Initializer dataHelper;
    private ManageReturnsPresenter presenter;
    private ManageReturnsViewStub view;

    @Before
    public void setUp()
    {
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        view = new ManageReturnsViewStub();
        view.setAttachedBorrowerID(1);
        presenter = new ManageReturnsPresenter(view, dataHelper.getLoanDAO(), dataHelper.getBorrowerDAO());
    }

    @Test
    public void showToast()
    {
        Assert.assertEquals("", view.getToast());
        presenter.onClickItem(1);//, "hello", "body");

        Assert.assertEquals("Τροποποίηση κατάστασης αντιτύπου", view.getAlertTitle());
        Assert.assertEquals("Θέλετε να τροποποιήσετε την κατάσταση του αντιτύπου; Αυτή τι στιγμή είναι δανεισμένο στον δανειζόμενο 'Ακρίδας Αγαμέμνων';", view.getAlertMessage());
        Assert.assertEquals(1, view.getSelectedId());
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
        presenter.onAddNewItem();
        Assert.assertEquals("Επιστροφή Αντιτύπου", view.getAlertTitle());
        Assert.assertEquals("Επιλέξτε ένα αντίτυπο από τη λίστα προκειμένου να το επιστρέψετε ή να το σημειώσετε ως χαμένο.", view.getAlertMessage());
    }

    @Test
    public void itemReturned()
    {
        presenter.onChangeItemState(1, true);
        Assert.assertEquals("Το αντίτυπο επιστράφηκε!", view.getToast());
    }

    @Test
    public void itemLost()
    {
        presenter.onChangeItemState(1, false);
        Assert.assertEquals("Το αντίτυπο σημειώθηκε ως χαμένο!", view.getToast());
    }
}
