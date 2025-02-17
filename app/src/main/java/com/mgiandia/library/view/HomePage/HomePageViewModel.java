package com.mgiandia.library.view.HomePage;

import androidx.lifecycle.ViewModel;

import com.mgiandia.library.ui.model.ButtonClicked;

public class HomePageViewModel extends ViewModel implements ButtonClicked
{
    private HomePagePresenter presenter;

    public HomePagePresenter getPresenter(HomePageView view)
    {
        presenter = new HomePagePresenter(view);
        return presenter;
    }
}