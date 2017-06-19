package com.mgiandia.library.view.Util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class MultiSelectSpinner extends android.support.v7.widget.AppCompatSpinner implements DialogInterface.OnMultiChoiceClickListener, DialogInterface.OnCancelListener
{
    private List<String> items;
    private boolean[] selectedIndexes;
    int selectedNo;

    public MultiSelectSpinner(Context arg0)
    {
        super(arg0);
    }

    public MultiSelectSpinner(Context arg0, AttributeSet arg1) {
        super(arg0, arg1);
    }

    public MultiSelectSpinner(Context arg0, AttributeSet arg1, int arg2) {
        super(arg0, arg1, arg2);
    }

    @Override
    public void onClick(DialogInterface dialog, int index, boolean checked)
    {
        if(checked) selectedNo++;
        else selectedNo--;

        selectedIndexes[index] = checked;
    }

    @Override
    public void onCancel(DialogInterface dialog)
    {
        setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, new String[]{
                selectedNo == 0 ? "Επιλέξτε" : selectedNo+" Επιλογές"
        }));
        this.onItemsSelected(selectedIndexes);
    }

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

    public void setSelectedItems(List<Integer> indexes)
    {

        for(int index : indexes)
            onClick(null, index, true);

        onCancel(null);
    }

    public void setItems(List<String> items)
    {
        this.items = items;
        selectedIndexes = new boolean[items.size()];
        this.selectedNo = 0;
    }

    public boolean[] getItemsIndexes()
    {
        return selectedIndexes;
    }

    public int getItemsIndexesNo()
    {
        return selectedNo;
    }

    public void onItemsSelected(boolean[] selected)
    {

    }
}
