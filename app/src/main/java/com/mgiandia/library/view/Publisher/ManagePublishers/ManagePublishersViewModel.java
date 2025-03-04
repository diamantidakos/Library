package com.mgiandia.library.view.Publisher.ManagePublishers;

import androidx.lifecycle.ViewModel;

import com.mgiandia.library.dao.PublisherDAO;
import com.mgiandia.library.domain.Publisher;
import com.mgiandia.library.memorydao.PublisherDAOMemory;
import com.mgiandia.library.ui.model.ButtonClicked;

import java.util.List;

public class ManagePublishersViewModel extends ViewModel implements ButtonClicked
{
    private PublisherDAO publisherDAO = new PublisherDAOMemory();
    private ManagePublishersPresenter presenter;

    public ManagePublishersPresenter getPresenter(ManagePublishersView view)
    {
        presenter = new ManagePublishersPresenter(view, publisherDAO);
        return presenter;
    }

    public List<Publisher> getAllPublishers()
    {
        return publisherDAO.findAll();
    }
}
