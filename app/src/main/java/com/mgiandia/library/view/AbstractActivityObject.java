package com.mgiandia.library.view;

import androidx.appcompat.app.AppCompatActivity;

public class AbstractActivityObject extends AppCompatActivity
{
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
    }
}
