package com.mgiandia.library.view.Items.ManageItems;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

import android.content.Context;
import android.content.Intent;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.Until;

import com.mgiandia.library.view.Book.ManageBooks.ManageBooksActivityObject;
import com.mgiandia.library.view.SystemTest;

import org.junit.Before;
import org.junit.Test;

public class ManageItemsActivityTest extends SystemTest
{
    private UiDevice mDevice = UiDevice.getInstance(getInstrumentation());
    private final int LAUNCH_TIMEOUT = super.launchTimeOut;
    public final String BASIC_SAMPLE_PACKAGE = super.appPackage;
    private ManageItemsActivityObject manageItemsActivityObject = new ManageItemsActivityObject(mDevice);
    private Context context = getApplicationContext();


    @Before
    public void startAddEditAuthorActivityFromHomeScreen()
    {
        mDevice.pressHome();

        // Launch the app from the home page
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
    public void testManageItemsScreen() throws UiObjectNotFoundException
    {
        manageItemsActivityObject.clickItem("The Odyssey", "10");
    }
}
