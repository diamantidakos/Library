package com.mgiandia.library.view.Author.ManageAuthors;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mgiandia.library.dao.AuthorDAO;
import com.mgiandia.library.domain.Author;
import com.mgiandia.library.memorydao.AuthorDAOMemory;
import com.mgiandia.library.ui.model.ButtonClicked;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ManageAuthorsViewModel extends ViewModel implements ButtonClicked
{
    private AuthorDAO authorDAO = new AuthorDAOMemory();
    private ManageAuthorsPresenter presenter;
    private final MutableLiveData<Integer> selectedAuthorID = new MutableLiveData<>();
    private final MutableLiveData<ArrayList<Author>> authors = new MutableLiveData<>();
    private final MutableLiveData<String> textOnSearchBar = new MutableLiveData<>();

    public ManageAuthorsPresenter getPresenter(ManageAuthorsView view)
    {
        presenter = new ManageAuthorsPresenter(view, authorDAO);
        return presenter;
    }

    public List<Author> getAllAuthors()
    {
        return authorDAO.findAll();
    }

    public Set<Author> findAuthors(String name)
    {
        return authorDAO.findByName(name);
    }

    public void setSelectedAuthorID(int selectedAuthorID)
    {
        this.selectedAuthorID.setValue(selectedAuthorID);
    }

    public MutableLiveData<Integer> getSelectedAuthorID()
    {
        return selectedAuthorID;
    }

    public void setAuthors(ArrayList<Author> authors)
    {
        this.authors.setValue(authors);
    }

    public MutableLiveData<ArrayList<Author>> getAuthors()
    {
        return authors;
    }

    public void setTextOnSearchBar(String textOnSearchBar)
    {
        this.textOnSearchBar.setValue(textOnSearchBar);
    }

    public MutableLiveData<String> getTextOnSearchBar()
    {
        return textOnSearchBar;
    }
}