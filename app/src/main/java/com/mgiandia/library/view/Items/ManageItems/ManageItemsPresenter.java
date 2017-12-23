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

    /**
     * Επιστρέφει τα δεδομένα για μία λίστα από αντίτυπα.
     * @param items Τα αντίτυπα στα οποία θα επιστραφούν τα δεδομένα
     * @return Μία λίστα με τις λεπτομέρειες των αντίτυπων
     */
    private List<Quadruple> createDataSource(List<Item> items)
    {
        List<Quadruple> triplets = new ArrayList<>();

        for(Item item : items)
            triplets.add(new Quadruple(item.getItemNumber(),  ItemStateString.convert(item.getState())+" Αντίτυπο", item.getBook().getTitle(), "Μοναδικός Κωδικός: "+item.getItemNumber()));

        return triplets;
    }

    /**
     * Αρχικοποιεί τον Presenter.
     * @param view Ένα instance του view
     * @param books Ένα instance του books
     * @param items Ένα instance του item
     */
    public ManageItemsPresenter(ManageItemsView view, BookDAO books, ItemDAO items)
    {
        this.view = view;
        this.items = items;
        book = books.find(view.getAttachedBookID());
        view.setPageName("Αντίτυπα Βιβλίου #"+book.getId());

        onLoadSource();
    }

    /**
     * Προσθέτει το αντίτυπο ενός βιβλίου.
     * @param book
     */
    private void addOneBookCopy(Book book)
    {
        Item itemTmp = new Item(items.nextId());
        itemTmp.setBook(book);
        items.save(itemTmp);
    }

    /**
     * Τροποποιεί την κατάσταση ενός αντίτυπου. Έπειτα ανανεώνει
     * την κατάσταση του αντίτυπου.
     * @param uid Το μοναδικό id του αντίτυπου
     * @param oldState Η παλιά κατάσταση πριν τροποποιηθεί
     * @param newState Η νέα κατάσταση μετά την τροποποίηση
     */
    private void showStateChange(int uid, ItemState oldState, ItemState newState)
    {
        view.refresh();
        view.showToast("Η κατάσταση του αντιτύπου #"+uid+" τροποποιήθηκε ("+ItemStateString.convert(oldState)+" -> "+ItemStateString.convert(newState)+")");
    }

    /**
     * Κατά την πραγματοποίηση click επάνω
     * στο αντίτυπο εμφανίζεται μήνυμα ανάλογο
     * με την κατάσταση του.
     * @param uid Το μοναδικό id του αντιτύπου
     */
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

    /**
     * Αλλάζει την κατάσταση του αντίτυπου με
     * id uid και την θέτει σε διαθέσιμη ή όχι.
     * @param uid Το μοναδικό id του αντίτυπου προς
     * αλλαγή
     */
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

    /**
     * Κατά την πρόσθεση ενός νέου αντιτύπου
     * ανανεώνεται η λίστα και εμφανίζεται
     * κατάλληλο μήνυμα. Τέλος προστίθεται το
     * αντικείμενο.
     */
    void doAddNewItem()
    {
        addOneBookCopy(book);
        view.refresh();
        view.showToast("Προστέθηκε με επιτυχία ένα καινούργιο αντίτυπο του βιβλίου στη συλλογή!");
    }

    /**
     * Κατά την πρόσθεση ενός νέου αντιτύπου
     * εμφανίζεται κατάλληλο μήνυμα.
     */
    void onAddNewItem()
    {
        view.newItemAddAlert("Προσθήκη Αντιτύπου", "Επιθυμείτε να προσθέσετε ένα καινούργιο αντίτυπο;");
    }

    /**
     * Φορτώνεται η λίστα.
     */
    void onLoadSource()
    {
        view.loadSource(createDataSource(new ArrayList<>(book.getItems())));
    }
}
