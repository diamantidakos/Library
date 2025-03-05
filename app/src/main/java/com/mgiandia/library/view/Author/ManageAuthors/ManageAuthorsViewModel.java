package com.mgiandia.library.view.Author.ManageAuthors;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mgiandia.library.dao.AuthorDAO;
import com.mgiandia.library.domain.Author;
import com.mgiandia.library.memorydao.AuthorDAOMemory;
import com.mgiandia.library.ui.model.ButtonClicked;

import java.util.List;

public class ManageAuthorsViewModel extends ViewModel implements ButtonClicked
{
    private AuthorDAO authorDAO = new AuthorDAOMemory();
    private ManageAuthorsPresenter presenter;
    private final MutableLiveData<Integer> selectedAuthorID = new MutableLiveData<>();

    public ManageAuthorsPresenter getPresenter(ManageAuthorsView view)
    {
        presenter = new ManageAuthorsPresenter(view, authorDAO);
        return presenter;
    }

    public List<Author> getAllAuthors()
    {
        return authorDAO.findAll();
    }

    public void setSelectedAuthorID(int selectedAuthorID)
    {
        this.selectedAuthorID.setValue(selectedAuthorID);
    }

    public MutableLiveData<Integer> getSelectedAuthorID()
    {
        return selectedAuthorID;
    }
}