package com.mgiandia.library.view.Book.AddEditBook;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import com.mgiandia.library.dao.Initializer;
import com.mgiandia.library.memorydao.AuthorDAOMemory;
import com.mgiandia.library.memorydao.BookDAOMemory;
import com.mgiandia.library.memorydao.ItemDAOMemory;
import com.mgiandia.library.memorydao.MemoryInitializer;
import com.mgiandia.library.memorydao.PublisherDAOMemory;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class AddEditBookPresenterTest
{
    private Initializer dataHelper;
    private AddEditBookPresenter presenter;
    private AddEditBookViewStub view;

    @Before
    public void setUp()
    {
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        view = new AddEditBookViewStub();
    }

    @Test
    public void testAddNew()
    {
        presenter = new AddEditBookPresenter(view, new BookDAOMemory(), new PublisherDAOMemory(), new AuthorDAOMemory(), new ItemDAOMemory());

        presenter.onSaveBook();
        view.setBookTitle("1");
        Assert.assertEquals(view.getErrorTitle(), "Σφάλμα!");
        Assert.assertEquals(view.getErrorMessage(), "Συμπληρώστε 2 έως 15 χαρακτήρες στον Τίτλο.");

        view.setBookTitle("title");
        view.setISBN("1");
        presenter.onSaveBook();
        Assert.assertEquals(view.getErrorTitle(), "Σφάλμα!");
        Assert.assertEquals(view.getErrorMessage(), "Συμπληρώστε 2 έως 15 χαρακτήρες στο ISBN.");

        view.setISBN("123456789");
        view.setPublication("1");
        presenter.onSaveBook();
        Assert.assertEquals(view.getErrorTitle(), "Σφάλμα!");
        Assert.assertEquals(view.getErrorMessage(), "Συμπληρώστε 2 έως 15 χαρακτήρες στη Δημοσίευση.");

        view.setPublication("123456789");
        view.setYear("123A");
        presenter.onSaveBook();
        Assert.assertEquals(view.getErrorTitle(), "Σφάλμα!");
        Assert.assertEquals(view.getErrorMessage(), "Συμπληρώστε ακριβώς 4 αριθμητικά ψηφία το Έτος.");

        view.setYear("1234");
        view.setPublisherPosition(null);
        presenter.onSaveBook();
        Assert.assertEquals(view.getErrorTitle(), "Σφάλμα!");
        Assert.assertEquals(view.getErrorMessage(), "Επιλέξτε Εκδοτικό Οίκο.");

        view.setPublisherPosition(1);
        presenter.onSaveBook();
        Assert.assertEquals(view.getErrorTitle(), "Σφάλμα!");
        Assert.assertEquals(view.getErrorMessage(), "Επιλέξτε τουλάχιστον ένα Συγγραφέα.");

        view.setAuthorPositions(Arrays.asList(1));
        presenter.onSaveBook();

        Assert.assertEquals(view.getFinishMessage(), "Επιτυχής Προσθήκη του Βιβλίου 'title'!");
    }

    @Test
    public void testUpdateExisting()
    {
        view.setAttachedBookID(1);
        presenter = new AddEditBookPresenter(view, dataHelper.getBookDAO(), dataHelper.getPublisherDAO(), dataHelper.getAuthorDAO(), dataHelper.getItemDAO());

        presenter.onSaveBook();

        Assert.assertEquals(view.getFinishMessage(), "Επιτυχής Τροποποίηση του Βιβλίου '"+(new BookDAOMemory().find(1).getTitle())+"'!");
    }
}
