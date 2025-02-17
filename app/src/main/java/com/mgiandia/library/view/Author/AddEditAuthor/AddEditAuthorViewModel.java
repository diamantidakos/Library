package com.mgiandia.library.view.Author.AddEditAuthor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mgiandia.library.memorydao.AuthorDAOMemory;
import com.mgiandia.library.ui.model.ButtonClicked;

public class AddEditAuthorViewModel extends ViewModel implements ButtonClicked
{
    private AddEditAuthorPresenter presenter;
    private final MutableLiveData<String> firstName = new MutableLiveData<>();
    private final MutableLiveData<String> lastName = new MutableLiveData<>();

    public AddEditAuthorPresenter getPresenter(AddEditAuthorView view)
    {
        presenter = new AddEditAuthorPresenter(view, new AuthorDAOMemory());
        return presenter;
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
