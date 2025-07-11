package com.mgiandia.library.view.Items.ManageItems;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.mgiandia.library.dao.BookDAO;
import com.mgiandia.library.dao.ItemDAO;
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
    private final MutableLiveData<Integer> selectedBookID = new MutableLiveData<>();
    private final MutableLiveData<String> selectedBookTitle = new MutableLiveData<>();
    private final MutableLiveData<Integer> selectedItemID = new MutableLiveData<>();
    private final MutableLiveData<String> textOnSearchBar = new MutableLiveData<>();

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

    public List<Item> getItemsByBookTitle()
    {
        return itemDAO.findByBookTitle(selectedBookTitle.getValue());
    }

    public List<Item> findItemsByTitle(String title)
    {
        return itemDAO.findByBookTitle(title, selectedBookTitle.getValue());
    }

    public String getBookTitle(int bookID)
    {
        return bookDAO.find(bookID).getTitle();
    }

    public void setSelectedBookID(int selectedItemID)
    {
        this.selectedBookID.setValue(selectedItemID);
    }

    public MutableLiveData<Integer> getSelectedBookID()
    {
        return selectedBookID;
    }

    public void setSelectedItemID(int selectedItemID)
    {
        this.selectedItemID.setValue(selectedItemID);
    }

    public MutableLiveData<Integer> getSelectedItemID()
    {
        return selectedItemID;
    }

    public void setSelectedBookTitle(String selectedBookTitle)
    {
        this.selectedBookTitle.setValue(selectedBookTitle);
    }

    public MutableLiveData<String> getSelectedBookTitle()
    {
        return selectedBookTitle;
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