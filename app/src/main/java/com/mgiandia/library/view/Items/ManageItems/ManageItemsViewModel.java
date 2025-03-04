package com.mgiandia.library.view.Items.ManageItems;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.mgiandia.library.dao.BookDAO;
import com.mgiandia.library.dao.ItemDAO;
import com.mgiandia.library.domain.Book;
import com.mgiandia.library.domain.Item;
import com.mgiandia.library.memorydao.BookDAOMemory;
import com.mgiandia.library.memorydao.ItemDAOMemory;
import com.mgiandia.library.ui.model.ButtonClicked;
import java.util.ArrayList;
import java.util.List;

public class ManageItemsViewModel extends ViewModel implements ButtonClicked
{
    private ManageItemsPresenter presenter;
    private BookDAO bookDAO = new BookDAOMemory();
    private ItemDAO itemDAO = new ItemDAOMemory();
    private final MutableLiveData<ArrayList<Item>> items = new MutableLiveData<>();

    public ManageItemsPresenter getPresenter(ManageItemsView view)
    {
        presenter = new ManageItemsPresenter(view, bookDAO, itemDAO);
        return presenter;
    }

    public void setItems(ArrayList<Item> items)
    {
        this.items.setValue(items);
    }

    public MutableLiveData<ArrayList<Item>> getItems()
    {
        return items;
    }

    public List<Item> getAllItems()
    {
        return itemDAO.findAll();
    }
}