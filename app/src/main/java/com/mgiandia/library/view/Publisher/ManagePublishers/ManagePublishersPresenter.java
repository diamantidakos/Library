package com.mgiandia.library.view.Publisher.ManagePublishers;

import java.util.ArrayList;
import java.util.List;

import com.mgiandia.library.dao.PublisherDAO;
import com.mgiandia.library.domain.Publisher;
import com.mgiandia.library.util.Quadruple;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class ManagePublishersPresenter
{
    private ManagePublishersView view;
    private PublisherDAO publishers;

    /**
     * Επιστρέφει τα δεδομένα για μία λίστα από εκδότες.
     * @param publishers Οι εκδότες στους οποίους θα επιστραφούν τα δεδομένα
     * @return Μία λίστα με τις λεπτομέριες των εκδοτών
     */
    private List<Quadruple> createDataSource(List<Publisher> publishers)
    {
        List<Quadruple> triplets = new ArrayList<>();

        for(Publisher publisher : publishers)
            triplets.add(new Quadruple(publisher.getId(), publisher.getName(), null, "Σύνολο βιβλίων "+publisher.getBooks().size()));

        return triplets;
    }

    /**
     * Αρχικοποεί τον Presenter.
     * @param view Ένα instance του view
     * @param publishers Ένα instance του publisher
     */
    public ManagePublishersPresenter(ManagePublishersView view, PublisherDAO publishers)
    {
        this.view = view;
        this.publishers = publishers;

        onLoadSource();
    }

    /**
     * Μεταφερει τον χρήστη στο activity PublisherDetailsActivity
     * όταν γίνει click πάνω στον εκδότη με id uid.
     * @param uid To μοναδικό id του εκδότη
     */
    void onClickItem(int uid)
    {
        view.clickItem(uid);
    }

    /**
     * Ξεκινάει το activity AddEditPublisherActivity
     */
    void onStartAddNew()
    {
        view.startAddNew();
    }

    /**
     * Εμφανίζει ένα μήνυμα με περιεχόμενο
     * value.
     * @param value To περιεχόμενο που θα εμφανιστεί
     */
    void onShowToast(String value)
    {
        view.showToast(value);
    }

    /**
     * Φορτώνει την λίστα με τους εκδότες.
     * @param input Η λιστα που θα φορτώσει
     */
    void onLoadSource()
    {
        view.loadSource(createDataSource(publishers.findAll()));
    }
}
