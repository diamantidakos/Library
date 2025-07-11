package com.mgiandia.library.view.Publisher.ManagePublishers;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.mgiandia.library.dao.PublisherDAO;
import com.mgiandia.library.domain.Publisher;
import com.mgiandia.library.memorydao.PublisherDAOMemory;
import com.mgiandia.library.ui.model.ButtonClicked;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ManagePublishersViewModel extends ViewModel implements ButtonClicked
{
    private PublisherDAO publisherDAO = new PublisherDAOMemory();
    private ManagePublishersPresenter presenter;
    private MutableLiveData<Integer> selectedPublisherID = new MutableLiveData<>();
    private MutableLiveData<String> textOnSearchBar = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Publisher>> publishers = new MutableLiveData<>();

    public ManagePublishersPresenter getPresenter(ManagePublishersView view)
    {
        presenter = new ManagePublishersPresenter(view, publisherDAO);
        return presenter;
    }

    public List<Publisher> getAllPublishers()
    {
        return publisherDAO.findAll();
    }

    public Set<Publisher> findByName(String name)
    {
        return publisherDAO.findByName(name);
    }

    public void setSelectedPublisherID(int selectedPublisherID)
    {
        this.selectedPublisherID.setValue(selectedPublisherID);
    }

    public MutableLiveData<Integer> getSelectedPublisherID()
    {
        return selectedPublisherID;
    }

    public void setTextOnSearchBar(String textOnSearchBar)
    {
        this.textOnSearchBar.setValue(textOnSearchBar);
    }

    public MutableLiveData<String> getTextOnSearchBar()
    {
        return textOnSearchBar;
    }

    public void setPublishers(ArrayList<Publisher> publishers)
    {
        this.publishers.setValue(publishers);
    }

    public MutableLiveData<ArrayList<Publisher>> getPublishers()
    {
        return publishers;
    }
}
