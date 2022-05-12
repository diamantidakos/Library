package com.mgiandia.library.view.Util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;

import androidx.appcompat.widget.AppCompatSpinner;

import java.util.List;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class MultiSelectSpinner extends AppCompatSpinner implements DialogInterface.OnMultiChoiceClickListener, DialogInterface.OnCancelListener
{
    private List<String> items;
    private boolean[] selectedIndexes;
    int selectedNo;

    /**
     * Κατασκευαστής με παράμετρο το Context.
     * @param arg0 Το Context που αφορά το συγκεκριμένο activity
     */
    public MultiSelectSpinner(Context arg0)
    {
        super(arg0);
    }

    /**
     * Κατασκευαστής με παράμετρο το Context και το AttributeSet.
     * @param arg0 Το Context που αφορά το συγκεκριμένο activity
     * @param arg1 Το AttributeSet
     */
    public MultiSelectSpinner(Context arg0, AttributeSet arg1) {
        super(arg0, arg1);
    }

    /**
     * Κατασκευαστής με παράμετρο το Context, το AttributeSet και έναν ακέραιο.
     * @param arg0 Το Context που αφορά το συγκεκριμένο activity
     * @param arg1 Το AttributeSet
     * @param arg2 Ένας ακέραιος
     */
    public MultiSelectSpinner(Context arg0, AttributeSet arg1, int arg2) {
        super(arg0, arg1, arg2);
    }

    /**
     * Καλείται όταν γίνει click σε κάποιο item του Spinner.
     * @param dialog Το DialogInterface
     * @param index Το index όπου έγινε το click
     * @param checked Αν το γεγονός είναι check ή un-check
     */
    @Override
    public void onClick(DialogInterface dialog, int index, boolean checked)
    {
        if(checked) selectedNo++;
        else selectedNo--;

        selectedIndexes[index] = checked;
    }

    /**
     * Καλείται όταν το Dialog γίνει cancel.
     * @param dialog Το DialogInterface
     */
    @Override
    public void onCancel(DialogInterface dialog)
    {
        setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, new String[]{
                selectedNo == 0 ? "Επιλέξτε" : selectedNo+" Επιλογές"
        }));
        this.onItemsSelected(selectedIndexes);
    }

    /**
     * Καλείται όταν γίνει click για να εμφανιστεί ο spinner με τις επιλογές.
     * @return Αν η ενεργοποίηση εκτελέστηκε επιτυχώς
     */
    @Override
    public boolean performClick()
    {
        new AlertDialog.Builder(getContext())
            .setMultiChoiceItems(items.toArray(new CharSequence[items.size()]), selectedIndexes, this)
            .setPositiveButton(android.R.string.ok,
            new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            })
            .setOnCancelListener(this)
            .show();

        return true;
    }

    /**
     * Ορίζει ως επιλεγμένα κάποια συγκεκριμένα αντικείμενα.
     * @param indexes Τα indexes των αντικειμένων
     */
    public void setSelectedItems(List<Integer> indexes)
    {

        for(int index : indexes)
            onClick(null, index, true);

        onCancel(null);
    }

    /**
     * Ορίζει τα ονόματα των αντικειμένων.
     * @param items Τα ονόματα με τη μορφή String
     */
    public void setItems(List<String> items)
    {
        this.items = items;
        selectedIndexes = new boolean[items.size()];
        this.selectedNo = 0;
    }

    /**
     * Επιστρέφει ποια από τα αντικείμενα έχουν επιλεγεί.
     * @return Για κάθε αντικείμενο ένας boolean που συμβολίζει αν είναι επιλεγμένο ή όχι το αντικείμενο
     */
    public boolean[] getItemsIndexes()
    {
        return selectedIndexes;
    }

    /**
     * Επιστρέφει πόσα αντικείμενα έχουν επιλεγεί συνολικά.
     * @return Ο αριθμός των επιλεγμένων αντικειμένων
     */
    public int getItemsIndexesNo()
    {
        return selectedNo;
    }

    /**
     * Δεν χρησιμοποιείται
     * @param selected boolean array
     */
    public void onItemsSelected(boolean[] selected)
    {

    }
}
