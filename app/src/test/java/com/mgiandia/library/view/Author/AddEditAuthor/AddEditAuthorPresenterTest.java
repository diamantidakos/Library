package com.mgiandia.library.view.Author.AddEditAuthor;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.mgiandia.library.dao.Initializer;
import com.mgiandia.library.memorydao.AuthorDAOMemory;
import com.mgiandia.library.memorydao.MemoryInitializer;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class AddEditAuthorPresenterTest
{
    private Initializer dataHelper;
    private AddEditAuthorPresenter presenter;
    private AddEditAuthorViewStub view;

    @Before
    public void setUp()
    {
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        view = new AddEditAuthorViewStub();
    }

    @Test
    public void testAddNew()
    {
        presenter = new AddEditAuthorPresenter(view, new AuthorDAOMemory());

        presenter.onSaveAuthor();
        view.setFirstName("1");
        Assert.assertEquals(view.getErrorTitle(), "Σφάλμα!");
        Assert.assertEquals(view.getErrorMessage(), "Συμπληρώστε 2 έως 15 χαρακτήρες στο όνομα.");

        view.setFirstName("first");
        view.setLastName("1");
        presenter.onSaveAuthor();
        Assert.assertEquals(view.getErrorTitle(), "Σφάλμα!");
        Assert.assertEquals(view.getErrorMessage(), "Συμπληρώστε 2 έως 15 χαρακτήρες στο επώνυμο.");

        view.setLastName("last");
        presenter.onSaveAuthor();

        Assert.assertEquals(view.getFinishMessage(), "Επιτυχής Εγγραφή του 'last first'!");
    }

    @Test
    public void testUpdateExisting()
    {
        view.setAttachedAuthorID(1);
        presenter = new AddEditAuthorPresenter(view, new AuthorDAOMemory());

        presenter.onSaveAuthor();

        Assert.assertEquals(view.getFinishMessage(), "Επιτυχής Τροποποίηση του '"+(new AuthorDAOMemory().find(1).getLastName())+" "+(new AuthorDAOMemory().find(1).getFirstName())+"'!");
    }
}
