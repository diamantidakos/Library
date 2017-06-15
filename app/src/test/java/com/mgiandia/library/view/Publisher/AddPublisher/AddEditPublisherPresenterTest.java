package com.mgiandia.library.view.Publisher.AddPublisher;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.mgiandia.library.dao.Initializer;
import com.mgiandia.library.memorydao.MemoryInitializer;
import com.mgiandia.library.memorydao.PublisherDAOMemory;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class AddEditPublisherPresenterTest
{
    private Initializer dataHelper;
    private AddEditPublisherPresenter presenter;
    private AddEditPublisherViewStub view;

    @Before
    public void setUp()
    {
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        view = new AddEditPublisherViewStub();
    }

    @Test
    public void testAddNew()
    {
        presenter = new AddEditPublisherPresenter(view, new PublisherDAOMemory(), dataHelper.getCountryDAO().getCountries());

        view.setName("1");
        presenter.onSavePublisher();
        Assert.assertEquals(view.getErrorTitle(), "Σφάλμα!");
        Assert.assertEquals(view.getErrorMessage(), "Συμπληρώστε 2 έως 15 χαρακτήρες στο Όνομα.");

        view.setName("name");
        view.setPhone("aaa");
        presenter.onSavePublisher();
        Assert.assertEquals(view.getErrorTitle(), "Σφάλμα!");
        Assert.assertEquals(view.getErrorMessage(), "Συμπληρώστε ορθά το Τηλέφωνο.");

        view.setPhone("123456789");
        view.setEmail("1");
        presenter.onSavePublisher();
        Assert.assertEquals(view.getErrorTitle(), "Σφάλμα!");
        Assert.assertEquals(view.getErrorMessage(), "Συμπληρώστε ορθά το Email.");

        view.setEmail("user@user.com");
        view.setAddressCity("1");
        presenter.onSavePublisher();
        Assert.assertEquals(view.getErrorTitle(), "Σφάλμα!");
        Assert.assertEquals(view.getErrorMessage(), "Συμπληρώστε 2 έως 15 χαρακτήρες στην Πόλη.");

        view.setAddressCity("city");
        view.setAddressStreet("1");
        presenter.onSavePublisher();
        Assert.assertEquals(view.getErrorTitle(), "Σφάλμα!");
        Assert.assertEquals(view.getErrorMessage(), "Συμπληρώστε 2 έως 15 χαρακτήρες στην Οδό.");

        view.setAddressStreet("street");
        view.setAddressNumber("1");
        presenter.onSavePublisher();
        Assert.assertEquals(view.getErrorTitle(), "Σφάλμα!");
        Assert.assertEquals(view.getErrorMessage(), "Συμπληρώστε 2 έως 10 αριθμητικά ψηφία στον Αριθμό.");

        view.setAddressNumber("123");
        view.setAddressPostalCode("1");
        presenter.onSavePublisher();
        Assert.assertEquals(view.getErrorTitle(), "Σφάλμα!");
        Assert.assertEquals(view.getErrorMessage(), "Συμπληρώστε 2 έως 10 αριθμητικά ψηφία στον Τ.Κ.");

        view.setAddressPostalCode("123");
        view.setCountryPosition(0);
        presenter.onSavePublisher();

        Assert.assertEquals(view.getFinishMessage(), "Επιτυχής Εγγραφή του 'name'!");
    }

    @Test
    public void testUpdateExisting()
    {
        view.setAttachedPublisherID(1);

        presenter = new AddEditPublisherPresenter(view, new PublisherDAOMemory(), dataHelper.getCountryDAO().getCountries());
        view.setName("name");
        view.setPhone("123456789");
        view.setEmail("user@user.com");
        view.setAddressCity("city");
        view.setAddressStreet("street");
        view.setAddressNumber("123");
        view.setAddressPostalCode("12");
        view.setCountryPosition(0);

        presenter.onSavePublisher();
        Assert.assertEquals(view.getFinishMessage(), "Επιτυχής Τροποποίηση του '"+(new PublisherDAOMemory().find(1).getName())+"'!");
    }
}
