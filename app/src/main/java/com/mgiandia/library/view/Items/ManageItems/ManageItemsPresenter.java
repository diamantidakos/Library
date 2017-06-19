package com.mgiandia.library.view.Items.ManageItems;

import java.util.ArrayList;
import java.util.List;

import com.mgiandia.library.dao.BookDAO;
import com.mgiandia.library.dao.ItemDAO;
import com.mgiandia.library.domain.Book;
import com.mgiandia.library.domain.Item;
import com.mgiandia.library.domain.ItemState;
import com.mgiandia.library.util.ItemStateString;
import com.mgiandia.library.util.Quadruple;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class ManageItemsPresenter
{
    private ManageItemsView view;
    private ItemDAO items;
    private Book book;

    private List<Quadruple> createDataSource(List<Item> items)
    {
        List<Quadruple> triplets = new ArrayList<>();

        for(Item item : items)
            triplets.add(new Quadruple(item.getItemNumber(),  ItemStateString.convert(item.getState())+" Αντίτυπο", item.getBook().getTitle(), "Μοναδικός Κωδικός: "+item.getItemNumber()));

        return triplets;
    }

    public ManageItemsPresenter(ManageItemsView view, BookDAO books, ItemDAO items)
    {
        this.view = view;
        this.items = items;
        book = books.find(view.getAttachedBookID());
        view.setPageName("Αντίτυπα Βιβλίου #"+book.getId());

        onLoadSource();
    }

    private void addOneBookCopy(Book book)
    {
        Item itemTmp = new Item(items.nextId());
        itemTmp.setBook(book);
        items.save(itemTmp);
    }

    private void showStateChange(int uid, ItemState oldState, ItemState newState)
    {
        view.refresh();
        view.showToast("Η κατάσταση του αντιτύπου #"+uid+" τροποποιήθηκε ("+ItemStateString.convert(oldState)+" -> "+ItemStateString.convert(newState)+")");
    }

    void onClickItem(int uid)
    {
        String dialogTitle = "Τροποποίηση κατάστασης αντιτύπου";
        ItemState itemState = items.find(uid).getState();

        if(itemState == ItemState.AVAILABLE)
            view.newItemStateSelectAlert(uid, dialogTitle, "Θέλετε να τροποποιήσετε την κατάσταση του αντιτύπου ("+ItemStateString.convert(itemState)+" -> "+ItemStateString.convert(ItemState.WITHDRAWN)+");");
        else if(itemState == ItemState.NEW)
            view.newItemStateSelectAlert(uid, dialogTitle, "Θέλετε να τροποποιήσετε την κατάσταση του αντιτύπου ("+ItemStateString.convert(itemState)+" -> "+ItemStateString.convert(ItemState.AVAILABLE)+");");
        else if(itemState == ItemState.LOANED)
            view.showAlert(dialogTitle, "Η κατάσταση του αντιτύπου είναι ("+ItemStateString.convert(ItemState.LOANED)+") και μπορεί να τροποποιηθεί μέσω της Περίπτωσης Χρήσης Επιστροφές.");
        else if(itemState == ItemState.LOST)
            view.showAlert(dialogTitle, "Η κατάσταση του αντιτύπου είναι ("+ItemStateString.convert(ItemState.LOST)+") και δεν μπορεί να τροποποιηθεί περαιτέρω.");
        else// if(itemState == ItemState.WITHDRAWN)
            view.showAlert(dialogTitle, "Η κατάσταση του αντιτύπου είναι ("+ItemStateString.convert(ItemState.WITHDRAWN)+") και δεν μπορεί να τροποποιηθεί περαιτέρω.");
    }

    void onChangeItemState(int uid)
    {
        Item item = items.find(uid);

        if (item.getState() == ItemState.AVAILABLE)
        {
            item.withdraw();
            showStateChange(uid, ItemState.AVAILABLE, item.getState());
        }
        else if (item.getState() == ItemState.NEW)
        {
            item.available();
            showStateChange(uid, ItemState.NEW, item.getState());
        }
    }

    void doAddNewItem()
    {
        addOneBookCopy(book);
        view.refresh();
        view.showToast("Προστέθηκε με επιτυχία ένα καινούργιο αντίτυπο του βιβλίου στη συλλογή!");
    }

    void onAddNewItem()
    {
        view.newItemAddAlert("Προσθήκη Αντιτύπου", "Επιθυμείτε να προσθέσετε ένα καινούργιο αντίτυπο;");
    }

    void onLoadSource()
    {
        view.loadSource(createDataSource(new ArrayList<>(book.getItems())));
    }
}
