package com.mgiandia.library.view.Items.ManageItems;

import androidx.lifecycle.ViewModel;

import com.mgiandia.library.memorydao.BookDAOMemory;
import com.mgiandia.library.memorydao.ItemDAOMemory;
import com.mgiandia.library.ui.model.ButtonClicked;

public class ManageItemsViewModel extends ViewModel implements ButtonClicked
{
    private ManageItemsPresenter presenter;

    public ManageItemsPresenter getPresenter(ManageItemsView view)
    {
        presenter = new ManageItemsPresenter(view, new BookDAOMemory(), new ItemDAOMemory());
        return presenter;
    }
}
