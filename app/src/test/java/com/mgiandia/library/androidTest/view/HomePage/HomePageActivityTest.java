package com.mgiandia.library.androidTest.view.HomePage;

import static org.junit.Assert.assertTrue;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiSelector;
import com.mgiandia.library.R;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class HomePageActivityTest
{
    private UiDevice mDevice;
    private HomePageActivityObject homePage;

    @Before
    public void setUp() throws Exception
    {
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        homePage = new HomePageActivityObject(mDevice);
        homePage.navigateToApp();
    }

    @Test
    public void testBorrowersButtonNavigation() throws Exception
    {
        homePage.verifyHomePageVisible();
        homePage.clickBorrowersButton();
        UiObject borrowersTitle = mDevice.findObject(new UiSelector().text(String.valueOf(R.string.manage_borrowers)));
        assertTrue(borrowersTitle.exists());
    }
}
