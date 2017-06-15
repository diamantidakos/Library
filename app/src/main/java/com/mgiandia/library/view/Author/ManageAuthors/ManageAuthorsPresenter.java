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

    private List<Quadruple> createDataSource(List<Author> authors)
    {
        List<Quadruple> triplets = new ArrayList<>();

        for(Author author : authors)
            triplets.add(new Quadruple(author.getId(), author.getFirstName(), author.getLastName(), "Σύνολο βιβλίων "+author.getBooks().size()));

        return triplets;
    }

    public ManageAuthorsPresenter(ManageAuthorsView view, AuthorDAO authors)
    {
        this.view = view;
        this.authors = authors;

        onLoadSource();
    }

    void onClickItem(int uid)
    {
        view.clickItem(uid);
    }

    void onStartAddNew()
    {
        view.startAddNew();
    }

    void onShowToast(String value)
    {
        view.showToast(value);
    }

    void onLoadSource()
    {
        view.loadSource(createDataSource(authors.findAll()));
    }
}
