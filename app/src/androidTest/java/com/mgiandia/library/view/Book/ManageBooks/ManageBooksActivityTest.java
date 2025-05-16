package com.mgiandia.library.view.Book.ManageBooks;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.Until;

import com.mgiandia.library.view.SystemTest;

import org.junit.Before;
import org.junit.Test;

public class ManageBooksActivityTest extends SystemTest
{
    private UiDevice mDevice = UiDevice.getInstance(getInstrumentation());
    private final int LAUNCH_TIMEOUT = super.launchTimeOut;
    public final String BASIC_SAMPLE_PACKAGE = super.appPackage;
    private ManageBooksActivityObject manageBooksActivityObject = new ManageBooksActivityObject(mDevice);
    private Context context = getApplicationContext();


    @Before
    public void startAddEditAuthorActivityFromHomeScreen()
    {
        mDevice.pressHome();

        // Launch the app directly to the Add/Edit Author screen
        final Intent intent = new Intent();
        intent.setClassName(BASIC_SAMPLE_PACKAGE, BASIC_SAMPLE_PACKAGE + ".view.HomePage.HomePageActivity");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        // Wait for activity to appear
        mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)), LAUNCH_TIMEOUT);
    }

    @Test
    public void checkPreconditions()
    {
        assertThat(mDevice, notNullValue());
    }

    @Test
    public void testBookDetailsScreen() throws UiObjectNotFoundException
    {
        manageBooksActivityObject.clickBookItem();
    }

    /**
     * Uses package manager to find the package name of the device launcher. Usually this package
     * is "com.android.launcher" but can be different at times. This is a generic solution which
     * works on all platforms.`
     */
    private String getLauncherPackageName()
    {
        // Create launcher Intent
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);

        // Use PackageManager to get the launcher package name
        PackageManager pm = getApplicationContext().getPackageManager();
        ResolveInfo resolveInfo = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return resolveInfo.activityInfo.packageName;
    }
}
