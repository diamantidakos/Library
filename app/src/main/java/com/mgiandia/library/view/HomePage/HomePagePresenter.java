package com.mgiandia.library.view.HomePage;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class HomePagePresenter
{
    private HomePageView view;

    public HomePagePresenter(HomePageView view)
    {
        this.view = view;
    }

    void onManageBorrowers()
    {
        view.manageBorrowers();
    }

    void onManageBooks()
    {
        view.manageBooks();
    }

    void onManageAuthors()
    {
        view.manageAuthors();
    }

    void onManagePublishers()
    {
        view.managePublishers();
    }

    void onManageItems()
    {
        view.manageItems();
    }

    void onManageLoans()
    {
        view.manageLoans();
    }

    void onManageReturns()
    {
        view.manageReturns();
    }
}
