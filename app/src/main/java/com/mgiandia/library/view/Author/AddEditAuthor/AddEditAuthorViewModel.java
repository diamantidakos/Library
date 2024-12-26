package com.mgiandia.library.view.Author.AddEditAuthor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.mgiandia.library.ui.model.ButtonClicked;

public class AddEditAuthorViewModel extends ViewModel implements ButtonClicked
{
    private final MutableLiveData<String> firstName = new MutableLiveData<>();
    private final MutableLiveData<String> lastName = new MutableLiveData<>();

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
