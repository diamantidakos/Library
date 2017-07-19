package com.mgiandia.library.view.Author.ManageAuthors;

import java.util.ArrayList;
import java.util.List;

import com.mgiandia.library.dao.AuthorDAO;
import com.mgiandia.library.domain.Author;
import com.mgiandia.library.util.Quadruple;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class ManageAuthorsPresenter
{
    private ManageAuthorsView view;
    private AuthorDAO authors;

    /**
     * Δημιουργεί την λίστα με τους συγγραφείς
     * @param authors Η λίστα με τους συγγραφείς
     * @return Επιστρέφει την λίστα
     */
    private List<Quadruple> createDataSource(List<Author> authors)
    {
        List<Quadruple> triplets = new ArrayList<>();

        for(Author author : authors)
            triplets.add(new Quadruple(author.getId(), author.getFirstName(), author.getLastName(), "Σύνολο βιβλίων "+author.getBooks().size()));

        return triplets;
    }

    /**
     * Αρχικοποιεί τον presenter και θέτει ως
     * ManageAuthorsView το view και AuthorDAO
     * το authors. Τέλος φορτώνει την λίστα με
     * τους συγγραφείς.
     * @param view Το ManageAuthorsView
     * @param authors Οι authors
     */
    public ManageAuthorsPresenter(ManageAuthorsView view, AuthorDAO authors)
    {
        this.view = view;
        this.authors = authors;

        onLoadSource();
    }

    /**
     * Μεταφέρει τον χρήστη στο activity AuthorDetailsActivity
     * όταν γίνει click πάνω στον συγγραφέα με id uid.
     * @param uid Το μοναδικό id του συγγραφέα
     */
    void onClickItem(int uid)
    {
        view.clickItem(uid);
    }

    /**
     * Ξεκινάει το activity AddEditAuthorActivity
     */
    void onStartAddNew()
    {
        view.startAddNew();
    }

    /**
     * Εμφανίζει ένα Toast.
     * @param value Το περιεχόμενο που θα εμφανιστεί
     */
    void onShowToast(String value)
    {
        view.showToast(value);
    }

    /**
     * Φορτώνει την λίστα με τους συγγραφείς
     */
    void onLoadSource()
    {
        view.loadSource(createDataSource(authors.findAll()));
    }
}
