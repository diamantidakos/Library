package com.mgiandia.library.domain;

import java.util.HashSet;
import java.util.Set;


import javax.persistence.*;




/**
 * Το βιβλίο.
 * @author Νίκος Διαμαντίδης
 */
@Entity
@Table(name="books")
public class Book {
    @Id 
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;    
    
    @org.hibernate.annotations.Type(
            type="com.mgiandia.library.persistence.ISBNCustomType")
    @Column(name="isbn", length = 20, nullable=false)
    private ISBN isbn;  
    
    @Column(name="title", length=200, nullable=false)
    private String title;
    
    @Column(name="publication", length=10)
    private String publication;
    
    @Column(name="pubyear")
    private int publicationyear;
    
    @ManyToOne(fetch=FetchType.LAZY, 
            cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name="publisherid")
    private Publisher publisher;
    
    
    @OneToMany(orphanRemoval=true, 
            cascade = CascadeType.ALL, 
            mappedBy="book", fetch=FetchType.LAZY)    
    private Set<Item> items = new HashSet<Item>();
    
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, 
            fetch=FetchType.LAZY)
    @JoinTable(name="bookauthors", 
            joinColumns = {@JoinColumn(name="bookid")},
            inverseJoinColumns = {@JoinColumn(name="authorid")}
    )
    private Set<Author> authors = new HashSet<Author>();

    /**
     * Προκαθορισμένος κατασκευαστής.
     */
    public Book() { }

    /**
     * Βοηθητικός κατασκευαστής που αρχικοποιεί τα βασικά στοιχεία ενός βιβλίου.
     * @param title Τίτλος
     * @param isbn ISBN
     * @param publication Αριθμός έκδοσης
     * @param publicationYear Έτος έκδοσης
     * @param publisher Εκδοτικός οίκος
     */
    public Book(String title, ISBN isbn, String publication,
            int publicationYear, Publisher publisher) {
        this.title = title;
        this.isbn = isbn;
        this.publication = publication;
        this.publicationyear = publicationYear;
        this.publisher = publisher;
    }


    public Integer getId() {
        return id;
    }
    
    /**
     * Θέτει τον τίτλο του βιβλίου.
     * @param title Ο τίτλος του βιβλίου
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Επιστρέφει τον τίτλο του βιβλίου.
     * @return Ο Τίτλος του βιβλίου
     */
    public String getTitle() {
        return title;
    }

    /**
     * Θέτει το ISBN του βιβλίου.
     * @param isbn Το ISBN
     */

    public void setIsbn(ISBN isbn) {
        this.isbn = isbn;
    }

    /**
     * Επιστρέφει το ISBN του βιβλίου.
     * @return Το ISBN
     */
    public ISBN getIsbn() {
        return isbn;
    }

    /**
     * Θέτει τον αριθμό της έκδοσης του βιβλίου.
     * @param publication Ο αριθμός της έκδοσης
     */

    public void setPublication(String publication) {
        this.publication = publication;
    }


    /**
     * Επιστρέφει τον αριθμό της έκδοσης του βιβλίου.
     * @return Ο αριθμός της έκδοσης
     */
    public String getPublication() {
        return publication;
    }

    /**
     * Θέτει το έτος έκδοσης του βιβλίου.
     * @param publicationyear Το έτος έκδοσης
     */
    public void setPublicationYear(int publicationyear) {
        this.publicationyear = publicationyear;
    }

    /**
     * Επιστρέφει το έτος έκδοσης του βιβλίου.
     * @return Το έτος έκδοσης
     */
    public int getPublicationYear() {
        return publicationyear;
    }


    /**
     * Θέτει τον εκδότη ({@link Publisher})του βιβλίου.
     * @param publisher Ο Εκδοτικός οίκος του βιβλίου
     */
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    /**
     * Επιστρέφει τον εκδότη ({@link Publisher})του βιβλίου.
     * @return Ο Εκδοτικός οίκος του βιβλίου
     */
    public Publisher getPublisher() {
        return publisher;
    }

    /**
     * Επιστρέφει τα αντίτυπα ({@link Item}) για κάποιο βιβλίο.
     * Η συλλογή των αντιτύπων είναι αντίγραφο.
     * Για την προσθήκη κάποιου αντιτύπου
     * στη συλλογή χρησιμοποιείστε τη μέθοδο {@link Book#addItem(Item)}
     * και για την απομάκρυνση ενός αντιτύπου
     * τη μέθοδο {@link Book#removeItem(Item)}.
     * @return Αντίγραφο της συλλογής των αντιτύπων του βιβλίου
     */
    public Set<Item> getItems() {
        return new HashSet<Item>(items);
    }


    /**
     * Επιστρέφει τους συγγραφείς ({@link Author}) για κάποιο βιβλίο.
     * Η συλλογή των αντιτύπων είναι αντίγραφο. Για την
     * προσθήκη κάποιου συγγραφέα στη συλλογή χρησιμοποιείστε
     * τη μέθοδο {@link Book#addAuthor(Author)} και για την
     * απομάκρυνση ενός αντιτύπου τη μέθοδο {@link Book#removeAuthor(Author)}.
     * @return Αντίγραφο της συλλογής των συγγραφέων του βιβλίου
     */
    public Set<Author> getAuthors() {
        return new HashSet<Author>(authors);
    }

    /**
     * Προσθήκη ενός αντιτύπου ({@link Item}) στη συλλογή αντιτύπων του βιβλίου.
     * @param item Το αντίτυπο
     */
    public void addItem(Item item) {
        if (item != null) {
            item.setBook(this);
        }
    }

    /**
     * Απομάκρυνση ενός αντιτύπου ({@link Item}) από τη συλλογή αντιτύπων του βιβλίου.
     * @param item Το αντίτυπο
     */
    public void removeItem(Item item) {
        if (item != null) {
            item.setBook(null);
        }
    }

    /**
     * Μη ενθυλακωμένη συλλογή των αντιτύπων του βιβλίου.
     * @return Τα αντίτυπα του βιβλίου
     */
    Set<Item> friendItems() {
        return items;
    }

    /**
     * Προσθήκη ενός συγγραφέα ({@link Author}) στους συγγραφείς του βιβλίου.
     * @param author Ο συγγραφέας
     */
    public void addAuthor(Author author) {
        if (author != null) {
            author.addBook(this);
        }
    }

    /**
     * Απομάκρυνση ενός συγγραφέα ({@link Author}) από τους συγγραφείς του βιβλίου.
     * @param author Ο συγγραφέας
     */
    public void removeAuthor(Author author) {
        if (author != null) {
            author.removeBook(this);
        }
    }

    /**
     * Μη ενθυλακωμένη συλλογή των συγγραφέων του βιβλίου.
     * @return Οι συγγραφείς του βιβλίου
     */
    Set<Author> friendAuthors() {
        return authors;
    }
    
    public boolean equals(Object other) {
        if ( other == null) {
            return false;
        }
        if (this == other) {
            return true;
        }
        if (! (other instanceof Book)) {
            return false;
        }
        
        Book theBook = (Book) other;
        if (! (getIsbn() == null ? theBook.getIsbn()
                == null : getIsbn().equals(theBook.getIsbn()))) {
            return false;
        }   
        
        return true;
    }
    
    public int hashCode() {
        return isbn == null ? 0 : isbn.hashCode();
    }
 }