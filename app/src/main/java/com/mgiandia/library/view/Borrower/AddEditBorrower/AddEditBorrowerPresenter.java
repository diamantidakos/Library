package com.mgiandia.library.view.Borrower.AddEditBorrower;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mgiandia.library.contacts.Address;
import com.mgiandia.library.contacts.EmailAddress;
import com.mgiandia.library.contacts.TelephoneNumber;
import com.mgiandia.library.contacts.ZipCode;
import com.mgiandia.library.dao.BorrowerCategoryDAO;
import com.mgiandia.library.dao.BorrowerDAO;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.domain.BorrowerCategory;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class AddEditBorrowerPresenter {

    private AddEditBorrowerView view;

    private BorrowerDAO borrowers;
    private BorrowerCategoryDAO borrowerCategories;
    private List<String> countries;

    private Borrower attachedBorrower;

    /**
     * Επαληθεύει την διεύθυνση ηλεκτρονικού ταχυδρομείου.
     * @param email Η διεύθυνση ηλεκτρονικού ταχυδρομείου
     * @return Ένα boolean αναλόγα με το αν ήταν
     * η διεύθυνση ηλεκτρονικού ταχυδρομείου
     */
    private boolean validateEmail(String email)
    {
        Pattern p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * Επαληθεύει αν η παράμετρος in αποτελείται
     * μόνο απο αριθμούς.
     * @param in Το κείμενο προς επαλήθευση
     * @return Επιστρέφει ενα boolean αναλόγα με το
     * αν ήταν αριθμός ή όχι
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
     * αργότερα να προσθέθει ή να τροποποιήθει
     * ο δανειζόμενος.
     * @param view Ένα instance του view
     * @param borrowers Ένα instance του borrower
     * @param borrowerCategories Ένα instance του borrowerCategorie
     * @param countries Ένα instance του countrie
     */
    public AddEditBorrowerPresenter(AddEditBorrowerView view, BorrowerDAO borrowers, BorrowerCategoryDAO borrowerCategories, List<String> countries)
    {
        this.view = view;
        this.borrowers = borrowers;
        this.borrowerCategories = borrowerCategories;
        this.countries = countries;

        view.setCountryList(countries);

        List<String> borrowerCategoryNames = new ArrayList<>();
        for(BorrowerCategory borrowerCategory : borrowerCategories.findAll()) borrowerCategoryNames.add(borrowerCategory.getDescription());
        view.setCategoryList(borrowerCategoryNames);

        Integer attachedBorrowerID = view.getAttachedBorrowerID();
        attachedBorrower = attachedBorrowerID == null ? null : borrowers.find(attachedBorrowerID);

        if(attachedBorrower != null)//edit mode
        {
            view.setPageName("Δανειζόμενος #" + attachedBorrower.getBorrowerNo());

            view.setFirstName(attachedBorrower.getFirstName());
            view.setLastName(attachedBorrower.getLastName());
            view.setCategoryPosition(attachedBorrower.getCategory().getId());
            view.setPhone(attachedBorrower.getTelephone().getTelephoneNumber());
            view.setEmail(attachedBorrower.getEmail().getAddress());
            view.setCountryPosition(countries.indexOf(attachedBorrower.getAddress().getCountry()));
            view.setAddressCity(attachedBorrower.getAddress().getCity());
            view.setAddressStreet(attachedBorrower.getAddress().getStreet());
            view.setAddressNumber(attachedBorrower.getAddress().getNumber());
            view.setAddressPostalCode(attachedBorrower.getAddress().getZipCode().getCode());
        }
    }

    /**
     * Κατα την αποθήκευση του δανειζόμενου ελέγχει
     * αν το όνομα, η πόλη, η όδος και το επώνυμο ειναι πάνω από
     * 2 χαρακτήρες και κάτω απο 15. Επίσης ελέγχει
     * αν ήταν ορθή η διεύθυνση ηλεκτρονικού ταχυδρομείου
     * και υπαρχούν 2 εώς 10 αριθμητικά ψηφία στον αριθμό και
     * στον ταχυδρομικό κώδικα. Τέλος μφανίζει μήνυμα ότι
     * ήταν επιτυχείς η εγγραφή ή η τροποποίηση αν πληρήθηκαν
     * οι από πάνω έλεγχοι.
     */
    public void onSaveBorrower()
    {
        String
                firstName = view.getFirstName(),
                lastName = view.getLastName(),
                phone = view.getPhone(),
                email = view.getEmail(),
                addressCity = view.getAddressCity(),
                addressStreet = view.getAddressStreet(),
                addressNumber = view.getAddressNumber(),
                addressPostalCode = view.getAddressPostalCode();

        Integer
                categoryPosition = view.getCategoryPosition(),
                countryPosition = view.getCountryPosition();

        if(firstName.length() < 2 || firstName.length() > 15)
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε 2 έως 15 χαρακτήρες στο Όνομα.");
        else if(lastName.length() < 2 || lastName.length() > 15)
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε 2 έως 15 χαρακτήρες στο Επώνυμο.");
        else if(phone.length() < 2 || phone.length() > 15 || !verifyOnlyDigits(phone))
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε ορθά το Τηλέφωνο.");
        else if(email.length() < 2 || email.length() > 100 || !validateEmail(email))
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε ορθά το Email.");
        else if(addressCity.length() < 2 || addressCity.length() > 15)
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε 2 έως 15 χαρακτήρες στην Πόλη.");
        else if(addressStreet.length() < 2 || addressStreet.length() > 15)
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε 2 έως 15 χαρακτήρες στην Οδό.");
        else if(addressNumber.length() < 2 || addressNumber.length() > 10 || !verifyOnlyDigits(addressNumber))
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε 2 έως 10 αριθμητικά ψηφία στον Αριθμό.");
        else if(addressPostalCode.length() < 2 || addressPostalCode.length() > 10 || !verifyOnlyDigits(addressPostalCode))
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε 2 έως 10 αριθμητικά ψηφία στον Τ.Κ.");
        else
        {
            Address addr = new Address(addressStreet, addressNumber, addressCity, new ZipCode(addressPostalCode), countries.get(countryPosition));

            if(attachedBorrower == null)//add
            {
                borrowers.save(new Borrower(borrowers.nextId(), firstName, lastName, addr, new EmailAddress(email), new TelephoneNumber(phone), borrowerCategories.find(categoryPosition)));
                view.successfullyFinishActivity("Επιτυχής Εγγραφή του '"+lastName+" "+firstName+"'!");

            }
            else//update
            {
                attachedBorrower.setFirstName(firstName);
                attachedBorrower.setLastName(lastName);
                attachedBorrower.setAddress(addr);
                attachedBorrower.setEmail(new EmailAddress(email));
                attachedBorrower.setTelephone(new TelephoneNumber(phone));
                attachedBorrower.setCategory(borrowerCategories.find(categoryPosition));

                view.successfullyFinishActivity("Επιτυχής Τροποποίηση του '"+lastName+" "+firstName+"'!");
            }
        }
    }
}
