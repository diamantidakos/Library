package com.mgiandia.library.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;




/**
 * Ο Συγγραφέας.
 * @author Νίκος Διαμαντίδης
 *
 */
@Entity
@Table(name="authors")
public class Author {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Embedded
    private Person person = new Person();
    
    @ManyToMany(mappedBy="authors",fetch=FetchType.LAZY, 
            cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<Book> books = new HashSet<Book>();

    /**
     * Προκαθορισμένος κατασκευαστής.
     */
    public Author() { }

    /**
     * Βοηθητικός κατασκευαστής.
     * @param firstName Το μικρό όνομα.
     * @param lastName Το επώνυμο.
     */
    public Author(String firstName, String lastName) {
         person.setFirstName(firstName);
         person.setLastName(lastName);
    }


    public Integer getId() {
        return id;
    }
    
    /**
     * Θέτει το όνομα του συγγραφέα.
     * @param firstName Το όνομα του συγγραφέα
     */
    public void setFirstName(String firstName) {
        person.setFirstName(firstName);
    }

    /**
     * Επιστρέφει το όνομα του συγγραφέα.
     * @return Το όνομα του συγγραφέα
     */
    public String getFirstName() {
        return person.getFirstName();
    }

    /**
     * Θέτει το επώνυμο του συγγραφέα.
     * @param lastName Το όνομα του συγγραφέα
     */
    public void setLastName(String lastName) {
        person.setLastName(lastName);
    }

    /**
     * Επιστρέφει το επώνυμο του συγγραφέα.
     * @return Το επώνυμο του συγγραφέα
     */
    public String getLastName() {
        return person.getLastName();
    }

    /**
     * Επιστρέφει τα βιβλία στα οποία συμμετέχει κάποιος συγγραφέας.
     * Η συλλογή των βιβλίων είναι αντίγραφο. Για την προσθήκη κάποιου βιβλίου
     * στη συλλογή χρησιμοποιείστε τη μέθοδο {@link Author#addBook(Book)}
     * και για την  απομάκρυνση ενός βιβλίου τη
     * μέθοδο {@link Author#removeBook(Book)}.
     * @return Αντίγραφο της συλλογής των βιβλίων του συγγραφέα
     */
    public Set<Book> getBooks() {
        return new HashSet<Book>(books);
    }

    /**
     * Προσθέτει ένα βιβλίο στη συλλογή των βιβλίων που συμμετέχει ο συγγραφέας.
     * @param book Το βιβλίο
     */
    public void addBook(Book book) {
        if (book != null) {
            book.friendAuthors().add(this);
            this.books.add(book);
        }
    }

    /**
     * Απομακρύνει ένα βιβλίο από τη συλλογή των βιβλίων του συγγραφέα.    
     * @param book Το βιβλίο
     */
    public void removeBook(Book book) {
        if (book != null) {
            book.friendAuthors().remove(this);
            this.books.remove(book);
        }
    }
    
    public boolean equals(Object other) {       
        if ( other == null) {
            return false;
        }
        if (this == other) {
            return true;
        }
        if (! (other instanceof Author)) {
            return false;
        }
        
        Author theAuthor = (Author ) other;
        if (! (getLastName() == null ? theAuthor.getLastName() 
                == null : getLastName().equals(theAuthor.getLastName()))) {
            return false;
        }   
        if (! (getFirstName() == null ? theAuthor.getFirstName()
                == null : getFirstName().equals(theAuthor.getFirstName()))) {
            return false;
        }
        
        return true;
    }
    
    
    public int hashCode() { 
        if (getLastName() ==null && getFirstName() == null) {
            return 0;
        }
        int result = 0;
        result = getLastName() == null ? result : 13 * result + getLastName().hashCode(); 
        result = getFirstName() == null ? result : 13 * result + getFirstName().hashCode();
        return result;
    }
}
