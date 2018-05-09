package com.mgiandia.library.view.reservation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mgiandia.library.R;
import com.mgiandia.library.domain.Book;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private List<Book> mDataset;

    /**
     * A reference to a listener for book selection events.
     * The listener is probably the Activity, but we do not desire a direct dependency
     * to a specific Activity (e.g., the Adapter could be reused by another Activity
     * that implements the interface).
     */
    private ItemSelectionListener<Book> bookSelectionListener;

    /**
     * This interface should be implemented by the Activity that hosts the RecyclerView
     * in order to be notified of item selection
     * @param <T>
     */
    public interface ItemSelectionListener<T> {

        void onItemSelected(T item);
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ViewGroup listItem;
        public TextView txtBookTitle;
        public ImageButton btnSelectBook;

        public ViewHolder(ViewGroup v) {
            super(v);
            listItem = v;
            txtBookTitle = listItem.findViewById(R.id.txt_book_title);
            btnSelectBook = listItem.findViewById(R.id.btn_select_book);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public BookAdapter(List<Book> myDataset) {
        mDataset = myDataset;
    }

    /**
     * Set a listener to be notified of book selection (click on the TextView)
     * @param bookSelectionListener
     */
    public void setBookSelectionListener(ItemSelectionListener<Book> bookSelectionListener) {
        this.bookSelectionListener = bookSelectionListener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view for the list
        ViewGroup v = (ViewGroup) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_book_result, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        // - get element from your dataset at this position
        final Book bookAtPosition = mDataset.get(position);

        // - replace the contents of the view with data from the dataset item at this position
        holder.txtBookTitle.setText(bookAtPosition.getTitle());
        holder.btnSelectBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // notify the Activity of the selected book
                if (bookSelectionListener != null) {
                    bookSelectionListener.onItemSelected(bookAtPosition);
                }
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}