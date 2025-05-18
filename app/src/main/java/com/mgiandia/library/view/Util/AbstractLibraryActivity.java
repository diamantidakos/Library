package com.mgiandia.library.view.Util;

import androidx.appcompat.app.AppCompatActivity;

public class AbstractLibraryActivity extends AppCompatActivity {

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
    }

}
