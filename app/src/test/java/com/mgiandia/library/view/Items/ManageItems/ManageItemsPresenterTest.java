package com.mgiandia.library.view.Items.ManageItems;

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

public class ManageItemsPresenterTest
{
    private Initializer dataHelper;
    private ManageItemsPresenter presenter;
    private ManageItemsViewStub view;

    @Before
    public void setUp()
    {
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        view = new ManageItemsViewStub();
        view.setAttachedBookID(1);
        presenter = new ManageItemsPresenter(view, dataHelper.getBookDAO(), dataHelper.getItemDAO());
    }

    @Test
    public void showToastAndClick()
    {
        Assert.assertEquals("", view.getToast());
        presenter.onClickItem(1);

        Assert.assertEquals("Τροποποίηση κατάστασης αντιτύπου", view.getAlertTitle());
        Assert.assertEquals("Θέλετε να τροποποιήσετε την κατάσταση του αντιτύπου (Διαθέσιμο -> Αποσυρμένο);", view.getAlertMessage());
        Assert.assertEquals(1, view.getSelectedId());

        presenter.onClickItem(6);
        Assert.assertEquals("Τροποποίηση κατάστασης αντιτύπου", view.getAlertTitle());
        Assert.assertEquals("Θέλετε να τροποποιήσετε την κατάσταση του αντιτύπου (Καινούργιο -> Διαθέσιμο);", view.getAlertMessage());

        presenter.onClickItem(7);
        Assert.assertEquals("Τροποποίηση κατάστασης αντιτύπου", view.getAlertTitle());
        Assert.assertEquals("Η κατάσταση του αντιτύπου είναι (Δανεισμένο) και μπορεί να τροποποιηθεί μέσω της Περίπτωσης Χρήσης Επιστροφές.", view.getAlertMessage());

        dataHelper.getItemDAO().find(7).lost();
        presenter.onClickItem(7);
        Assert.assertEquals("Τροποποίηση κατάστασης αντιτύπου", view.getAlertTitle());
        Assert.assertEquals("Η κατάσταση του αντιτύπου είναι (Χαμένο) και δεν μπορεί να τροποποιηθεί περαιτέρω.", view.getAlertMessage());

        dataHelper.getItemDAO().find(2).withdraw();
        presenter.onClickItem(2);
        Assert.assertEquals("Τροποποίηση κατάστασης αντιτύπου", view.getAlertTitle());
        Assert.assertEquals("Η κατάσταση του αντιτύπου είναι (Αποσυρμένο) και δεν μπορεί να τροποποιηθεί περαιτέρω.", view.getAlertMessage());

        presenter.onClickItem(1);
    }

    @Test
    public void sourceTest()
    {
        presenter.onLoadSource();
        Assert.assertEquals(4, view.getSource().size());
    }

    @Test
    public void startAdd()
    {
        presenter.onAddNewItem();
        Assert.assertEquals("Προσθήκη Αντιτύπου", view.getAlertTitle());
        Assert.assertEquals("Επιθυμείτε να προσθέσετε ένα καινούργιο αντίτυπο;", view.getAlertMessage());
    }

    @Test
    public void itemAvailableChange()
    {
        presenter.onChangeItemState(1);
        Assert.assertEquals("Η κατάσταση του αντιτύπου #1 τροποποιήθηκε (Διαθέσιμο -> Αποσυρμένο)", view.getToast());
    }

    @Test
    public void itemNewChange()
    {
        presenter.onChangeItemState(6);
        Assert.assertEquals("Η κατάσταση του αντιτύπου #6 τροποποιήθηκε (Καινούργιο -> Διαθέσιμο)", view.getToast());
    }

    @Test
    public void addNew()
    {
        int count = dataHelper.getItemDAO().findAll().size();
        presenter.doAddNewItem();
        Assert.assertEquals("Προστέθηκε με επιτυχία ένα καινούργιο αντίτυπο του βιβλίου στη συλλογή!", view.getToast());
        Assert.assertEquals(count+1, dataHelper.getItemDAO().findAll().size());
    }
}
