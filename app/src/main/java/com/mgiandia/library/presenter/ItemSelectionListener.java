package com.mgiandia.library.presenter;

/**
 * This interface should be implemented by the Activity that hosts the RecyclerView
 * in order to be notified of item selection
 *
 * @param <T>
 */

public interface ItemSelectionListener<T> {

    void onItemSelected(T item);

}