package com.mgiandia.library.ui.model;

import androidx.annotation.StringRes;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

public interface ButtonClicked {
    final MutableLiveData<Integer> clickedButton = new MutableLiveData<Integer>();

    /**
     * Method should be called in the onClick property of composable Buttons
     * @param buttonTextResId
     */
    default void buttonClicked(@StringRes int buttonTextResId){
        clickedButton.setValue(buttonTextResId);
    }

    /**
     * Register observers (listeners) for button clicks.
     * Each button click provides the string resource id of its label.
     * @param ctx
     * @param clickObserver
     */
    default void observeClicks(LifecycleOwner ctx, Observer<Integer> clickObserver){
        clickedButton.observe(ctx, clickObserver);
    }

}
