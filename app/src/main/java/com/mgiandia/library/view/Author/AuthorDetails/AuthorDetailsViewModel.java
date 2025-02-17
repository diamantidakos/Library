package com.mgiandia.library.view.Author.AuthorDetails;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mgiandia.library.dao.AuthorDAO;
import com.mgiandia.library.domain.Author;
import com.mgiandia.library.memorydao.AuthorDAOMemory;
import com.mgiandia.library.ui.model.ButtonClicked;

public class AuthorDetailsViewModel extends ViewModel implements ButtonClicked
{
    private final MutableLiveData<String> authorID = new MutableLiveData<>();
    private final MutableLiveData<String> name = new MutableLiveData<>();
    private final MutableLiveData<String> surname = new MutableLiveData<>();
    private final MutableLiveData<Integer> booksNumber = new MutableLiveData<>();

    private AuthorDetailsPresenter presenter;
    AuthorDAO authorDAO = new AuthorDAOMemory();

    public AuthorDetailsPresenter getPresenter(AuthorDetailsView view)
    {
        presenter = new AuthorDetailsPresenter(view, authorDAO);
        return presenter;
    }

    public Author findAuthor(int authorID)
    {
        return authorDAO.find(authorID);
    }

    public void setAuthorID(String authorID)
    {
        this.authorID.setValue(authorID);
    }

    public MutableLiveData<String> getAuthorID()
    {
        return authorID;
    }

    public void setName(String name)
    {
        this.name.setValue(name);
    }

    public MutableLiveData<String> getName()
    {
        return name;
    }

    public void setSurname(String surname)
    {
        this.surname.setValue(surname);
    }

    public MutableLiveData<String> getSurname()
    {
        return surname;
    }

    public void setBooksNumber(int booksNumber)
    {
        this.booksNumber.setValue(booksNumber);
    }

    public MutableLiveData<Integer> getBooksNumber()
    {
        return booksNumber;
    }
}
