package com.mgiandia.library.view;

import android.content.Context;

import androidx.test.InstrumentationRegistry;
import com.mgiandia.library.R;

public abstract class AbstractActivityObject
{
    public final Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    public final String appName = context.getString(R.string.app_name);
    public final String appPackage = "com.mgiandia.library";
}
