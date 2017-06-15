package com.mgiandia.library.view.Borrower.AddEditBorrower;

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

public class AddEditBorrowerPresenterTest
{
    private Initializer dataHelper;
    private AddEditBorrowerPresenter presenter;
    private AddEditBorrowerViewStub view;

    @Before
    public void setUp()
    {
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        view = new AddEditBorrowerViewStub();
    }

    @Test
    public void testAddNew()
    {
        presenter = new AddEditBorrowerPresenter(view, dataHelper.getBorrowerDAO(), dataHelper.getBorrowerCategoryDAO(), dataHelper.getCountryDAO().getCountries());

        presenter.onSaveBorrower();
        view.setFirstName("1");
        Assert.assertEquals(view.getErrorTitle(), "Σφάλμα!");
        Assert.assertEquals(view.getErrorMessage(), "Συμπληρώστε 2 έως 15 χαρακτήρες στο Όνομα.");

        view.setFirstName("first");
        view.setLastName("1");
        presenter.onSaveBorrower();
        Assert.assertEquals(view.getErrorTitle(), "Σφάλμα!");
        Assert.assertEquals(view.getErrorMessage(), "Συμπληρώστε 2 έως 15 χαρακτήρες στο Επώνυμο.");

        view.setLastName("last");
        view.setPhone("1231111111111111111");
        presenter.onSaveBorrower();
        Assert.assertEquals(view.getErrorTitle(), "Σφάλμα!");
        Assert.assertEquals(view.getErrorMessage(), "Συμπληρώστε ορθά το Τηλέφωνο.");

        view.setPhone("12a3456789");

        Assert.assertEquals(view.getErrorTitle(), "Σφάλμα!");
        Assert.assertEquals(view.getErrorMessage(), "Συμπληρώστε ορθά το Τηλέφωνο.");
        presenter.onSaveBorrower();

        view.setPhone("123456789");

        view.setEmail("1231111111111111111");
        presenter.onSaveBorrower();
        Assert.assertEquals(view.getErrorTitle(), "Σφάλμα!");
        Assert.assertEquals(view.getErrorMessage(), "Συμπληρώστε ορθά το Email.");

        view.setEmail("mail@mail.com");
        view.setAddressCity("1231111111111111111");
        presenter.onSaveBorrower();
        Assert.assertEquals(view.getErrorTitle(), "Σφάλμα!");
        Assert.assertEquals(view.getErrorMessage(), "Συμπληρώστε 2 έως 15 χαρακτήρες στην Πόλη.");

        view.setAddressCity("city");
        view.setAddressStreet("1231111111111111111");
        presenter.onSaveBorrower();
        Assert.assertEquals(view.getErrorTitle(), "Σφάλμα!");
        Assert.assertEquals(view.getErrorMessage(), "Συμπληρώστε 2 έως 15 χαρακτήρες στην Οδό.");

        view.setAddressStreet("street");
        view.setAddressNumber("1231111111111111111");
        presenter.onSaveBorrower();
        Assert.assertEquals(view.getErrorTitle(), "Σφάλμα!");
        Assert.assertEquals(view.getErrorMessage(), "Συμπληρώστε 2 έως 10 αριθμητικά ψηφία στον Αριθμό.");

        view.setAddressNumber("123");
        view.setAddressPostalCode("1231111111111111111");
        presenter.onSaveBorrower();
        Assert.assertEquals(view.getErrorTitle(), "Σφάλμα!");
        Assert.assertEquals(view.getErrorMessage(), "Συμπληρώστε 2 έως 10 αριθμητικά ψηφία στον Τ.Κ.");

        view.setAddressPostalCode("12");
        view.setCountryPosition(0);
        view.setCategoryPosition(0);
        presenter.onSaveBorrower();

        Assert.assertEquals(view.getFinishMessage(), "Επιτυχής Εγγραφή του 'last first'!");
    }

    @Test
    public void testUpdateExisting()
    {
        view.setAttachedBorrowerID(1);
        presenter = new AddEditBorrowerPresenter(view, dataHelper.getBorrowerDAO(), dataHelper.getBorrowerCategoryDAO(), dataHelper.getCountryDAO().getCountries());

        presenter.onSaveBorrower();
        Assert.assertEquals(view.getFinishMessage(), "Επιτυχής Τροποποίηση του '"+(dataHelper.getBorrowerDAO().find(1).getLastName())+" "+(dataHelper.getBorrowerDAO().find(1).getFirstName())+"'!");
    }
}
