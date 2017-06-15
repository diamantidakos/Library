package com.mgiandia.library.view.Borrower.BorrowerDetails;

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

public class BorrowerDetailsPresenterTest
{
    private Initializer dataHelper;
    private BorrowerDetailsPresenter presenter;
    private BorrowerDetailsViewStub view;

    @Before
    public void setUp()
    {
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        view = new BorrowerDetailsViewStub();
        view.setAttachedBorrowerID(1);
        view.setPageName("page");
        presenter = new BorrowerDetailsPresenter(view, dataHelper.getBorrowerDAO(), dataHelper.getLoanDAO());
    }

    @Test
    public void toastTest()
    {
        presenter.onShowToast("test");
        Assert.assertEquals("test", view.getToast());
    }

    @Test
    public void onStartDeleteButton()
    {
        presenter.onStartEditButtonClick();
        presenter.onStartDeleteButtonClick();
        Assert.assertEquals(view.getDeleteMessage(), "Όλα τα βιβλία που δεν έχουν επιστραφεί θα σημειωθούν ως χαμένα.");
    }

    @Test
    public void onDoDeleteAndFinish()
    {
        presenter.onDoDeleteAndFinish();
        Assert.assertEquals(view.getDeleteMessage(), "Επιτυχής διαγραφή του δανειζόμενου 'Ακρίδας Αγαμέμνων'!");
    }
}
