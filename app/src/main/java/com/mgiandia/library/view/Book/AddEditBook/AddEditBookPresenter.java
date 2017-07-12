package com.mgiandia.library.view.Book.AddEditBook;

import java.util.ArrayList;
import java.util.List;

import com.mgiandia.library.dao.AuthorDAO;
import com.mgiandia.library.dao.BookDAO;
import com.mgiandia.library.dao.ItemDAO;
import com.mgiandia.library.dao.PublisherDAO;
import com.mgiandia.library.domain.Author;
import com.mgiandia.library.domain.Book;
import com.mgiandia.library.domain.ISBN;
import com.mgiandia.library.domain.Item;
import com.mgiandia.library.domain.Publisher;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class AddEditBookPresenter {

    private AddEditBookView view;

    private BookDAO books;
    private AuthorDAO authors;
    private PublisherDAO publishers;
    private ItemDAO items;

    private Book attachedBook;

    /**
     * Επαληθεύει ότι ενα κείμενο περιέχει
     * μόνο αριθμούς.
     * @param in To text που θα επαληθευτεί
     * @return true αν περιέχει μόνο αριθμόυς
     * αλλιώς false.
     */
    private boolean verifyOnlyDigits(String in)
    {
        for(int i = 0; i < in.length(); i++)
            if(!Character.isDigit(in.charAt(i)))
                return false;

        return true;
    }

    /**
     * Αρχικοποεί τον Presenter έτσι ώστε
     * αργότερα να προσθέσει ή να τροποποιήσει.
     * @param view Ένα instance του view
     * @param books Ένα instance του book
     * @param publishers Ένα instance του publisher
     * @param authors Ένα instance του author
     * @param items Ένα instance του Item
     */
    public AddEditBookPresenter(AddEditBookView view, BookDAO books, PublisherDAO publishers, AuthorDAO authors, ItemDAO items)
    {
        this.view = view;
        this.books = books;
        this.publishers = publishers;
        this.authors = authors;
        this.items = items;

        List<String> authorNames = new ArrayList<>();
        for(Author author : authors.findAll()) authorNames.add(author.getLastName()+" "+author.getFirstName());
        view.setAuthorList(authorNames);

        List<String> publisherNames = new ArrayList<>();
        for(Publisher publisher : publishers.findAll()) publisherNames.add(publisher.getName());
        view.setPublisherList(publisherNames, "Επιλέξτε");

        Integer attachedBookID = view.getAttachedBookID();
        attachedBook = attachedBookID == null ? null : books.find(attachedBookID);

        if(attachedBook != null)//edit mode
        {
            view.setPageName("Βιβλίο #" + attachedBook.getId());

            view.setBookTitle(attachedBook.getTitle());
            view.setISBN(attachedBook.getIsbn().getValue());
            view.setPublication(attachedBook.getPublication());
            view.setYear(Integer.toString(attachedBook.getPublicationYear()));
            view.setPublisherPosition(attachedBook.getPublisher().getId());

            List<Integer> authorPositions = new ArrayList<>();

            for(Author author : attachedBook.getAuthors())
                authorPositions.add(author.getId());

            view.setAuthorPositions(authorPositions);
        }
    }

    /**
     * Αποθηκεύει το βιβλίο. Ελέγχει αν ο τίτλος, το ISBN και η δημοσίευση
     * ειναι από 2 εώς 15 χαρακτήρες. Επίσης ελέγχει αν έχει επιλεγθει
     * εκδοτικός οίκος και συγγραφέας. Τέλος ενημερώνει αν ήταν
     * επιτυχής η εισαγωγή ή η τροποποίηση του βιβλίου.
     */
    public void onSaveBook()
    {
        String
                title = view.getBookTitle(),
                isbn = view.getISBN(),
                publication = view.getPublication(),
                year = view.getYear();

        Integer publisherID = view.getPublisherPosition();
        List<Integer> authorIDs = view.getAuthorPositions();

        if(title.length() < 2 || title.length() > 15)
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε 2 έως 15 χαρακτήρες στον Τίτλο.");
        else if(isbn.length() < 2 || isbn.length() > 15)
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε 2 έως 15 χαρακτήρες στο ISBN.");
        else if(publication.length() < 2 || publication.length() > 15)
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε 2 έως 15 χαρακτήρες στη Δημοσίευση.");
        else if(year.length() != 4 || !verifyOnlyDigits(year))
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε ακριβώς 4 αριθμητικά ψηφία το Έτος.");
        else if(publisherID == null)
            view.showErrorMessage("Σφάλμα!", "Επιλέξτε Εκδοτικό Οίκο.");
        else if(authorIDs.size() == 0)
            view.showErrorMessage("Σφάλμα!", "Επιλέξτε τουλάχιστον ένα Συγγραφέα.");
        else
        {
            if(attachedBook == null)//add
            {
                Book bookTmp = new Book(books.nextId(), title, new ISBN(isbn), publication, Integer.parseInt(year), publishers.find(publisherID));
                books.save(bookTmp);

                Item itemTmp = new Item(items.nextId());
                itemTmp.setBook(bookTmp);

                items.save(itemTmp);

                for(Integer authorID : authorIDs)
                    authors.find(authorID).addBook(bookTmp);

                view.successfullyFinishActivity("Επιτυχής Προσθήκη του Βιβλίου '"+title+"'!");
            }
            else//update
            {
                attachedBook.setTitle(title);
                attachedBook.setIsbn(new ISBN(isbn));
                attachedBook.setPublication(publication);
                attachedBook.setPublicationYear(Integer.parseInt(year));
                attachedBook.setPublisher(publishers.find(publisherID));

                for(Author oldAuthor : attachedBook.getAuthors())
                    oldAuthor.removeBook(attachedBook);

                for(Integer authorID : authorIDs)
                    authors.find(authorID).addBook(attachedBook);

                view.successfullyFinishActivity("Επιτυχής Τροποποίηση του Βιβλίου '"+title+"'!");
            }
        }
    }
}
