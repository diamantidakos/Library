package com.mgiandia.library.view.Author.AddEditAuthor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mgiandia.library.dao.AuthorDAO;
import com.mgiandia.library.domain.Author;
import com.mgiandia.library.memorydao.AuthorDAOMemory;
import com.mgiandia.library.ui.model.ButtonClicked;

public class AddEditAuthorViewModel extends ViewModel implements ButtonClicked
{
    private AuthorDAO authorDAO = new AuthorDAOMemory();
    private AddEditAuthorPresenter presenter;
    private final MutableLiveData<String> firstName = new MutableLiveData<>();
    private final MutableLiveData<String> lastName = new MutableLiveData<>();

    public AddEditAuthorPresenter getPresenter(AddEditAuthorView view)
    {
        presenter = new AddEditAuthorPresenter(view, authorDAO);
        return presenter;
    }

    public Author findAuthor(int authorID)
    {
        return authorDAO.find(authorID);
    }

    public LiveData<String> getFirstName()
    {
        return firstName;
    }

    public LiveData<String> getLastName()
    {
        return lastName;
    }

    public void setFirstName(String name)
    {
        firstName.setValue(name);
    }

    public void setLastName(String name)
    {
        lastName.setValue(name);
    }
}
