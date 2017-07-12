package com.mgiandia.library.view.Publisher.PublisherDetails;

import com.mgiandia.library.dao.PublisherDAO;
import com.mgiandia.library.domain.Publisher;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class PublisherDetailsPresenter
{
    private PublisherDetailsView view;
    private Publisher attachedPublisher;

    /**
     * Αρχικοποεί τον Presenter.
     * @param view Ένα instance του view
     * @param publishers Ένα instance του publisher
     */
    public PublisherDetailsPresenter(PublisherDetailsView view, PublisherDAO publishers)
    {
        this.view = view;

        attachedPublisher = publishers.find(view.getAttachedPublisherID());

        view.setPageName("Εκδοτικός Οίκος #" + attachedPublisher.getId());

        view.setID("#"+attachedPublisher.getId());
        view.setName(attachedPublisher.getName());
        view.setPhone(attachedPublisher.getTelephone().getTelephoneNumber());
        view.setEmail(attachedPublisher.getEMail().getAddress());

        int booksPublished = attachedPublisher.getBooks().size();
        view.setBooksPublished(booksPublished+" "+(booksPublished == 1 ? "Βιβλίο" : "Βιβλία"));

        view.setCountry(attachedPublisher.getAddress().getCountry());
        view.setAddressCity(attachedPublisher.getAddress().getCity());
        view.setAddressStreet(attachedPublisher.getAddress().getStreet());
        view.setAddressNumber(attachedPublisher.getAddress().getNumber());
        view.setAddressPostalCode(attachedPublisher.getAddress().getZipCode().getCode());
    }

    /**
     * Εμφανίζει το κουμπί της επεξεργασίας του
     * εκδότη.
     */
    void onStartEditButtonClick()
    {
        view.startEdit(attachedPublisher.getId());
    }

    /**
     * Εμφανίζει το κουμπί της εμφάνισης του
     * εκδότη.
     */
    void onStartShowBooksButtonClick()
    {
        view.startShowBooks(attachedPublisher.getId());
    }

    /**
     * Εμφανίζει ενα μήνυμα με περιεχόμενο value.
     * @param value To περιεχόμενο του μηνύματος
     */
    void onShowToast(String value)
    {
        view.showToast(value);
    }
}
