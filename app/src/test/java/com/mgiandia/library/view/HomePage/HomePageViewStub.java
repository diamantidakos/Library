package com.mgiandia.library.view.HomePage;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class HomePageViewStub implements HomePageView
{
    private int
            manageBorrowersClicks,
            manageBooksClicks,
            manageAuthorsClicks,
            managePublishersClicks,
            manageItemsClicks,
            manageLoansClicks,
            manageReturnsClicks;

    private HomePagePresenter presenter;

    public void setPresenter(HomePagePresenter presenter) {
        this.presenter = presenter;
    }

    public HomePagePresenter getPresenter() {
        return presenter;
    }

    public HomePageViewStub()
    {

    }

    public void manageBorrowers()
    {
        manageBorrowersClicks++;
    }

    public void manageBooks()
    {
        manageBooksClicks++;
    }

    public void manageAuthors()
    {
        manageAuthorsClicks++;
    }

    public void managePublishers()
    {
        managePublishersClicks++;
    }

    public void manageItems()
    {
        manageItemsClicks++;
    }

    public void manageLoans()
    {
        manageLoansClicks++;
    }

    public void manageReturns()
    {
        manageReturnsClicks++;
    }

    public int getManageBorrowersClicks()
    {
        return manageBorrowersClicks;
    }

    public int getManageBooksClicks()
    {
        return manageBooksClicks;
    }

    public int getManageAuthorsClicks()
    {
        return manageAuthorsClicks;
    }

    public int getManagePublishersClicks()
    {
        return managePublishersClicks;
    }

    public int getManageItemsClicks()
    {
        return manageItemsClicks;
    }

    public int getManageLoansClicks()
    {
        return manageLoansClicks;
    }

    public int getManageReturnsClicks()
    {
        return manageReturnsClicks;
    }
}
