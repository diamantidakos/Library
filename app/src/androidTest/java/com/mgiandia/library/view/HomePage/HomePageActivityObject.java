package com.mgiandia.library.view.HomePage;

import static org.junit.Assert.assertTrue;
import android.content.Context;
import androidx.test.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiScrollable;
import androidx.test.uiautomator.UiSelector;
import com.mgiandia.library.R;
import com.mgiandia.library.view.AbstractActivityObject;
import org.junit.Before;


public class HomePageActivityObject extends AbstractActivityObject
{
    private final Context context = super.context;
    private final String APP_NAME = super.appName;
    public final String APP_PACKAGE = super.appPackage;
    private UiDevice mDevice;
    UiObject libraryAppMainActivity;

    public HomePageActivityObject(UiDevice mDevice)
    {
        this.mDevice = mDevice;
    }

    public String getAppName()
    {
        return APP_NAME;
    }

    @Before
    public void setUp() throws Exception {
        navigateToApp();
    }

    /*
    public void navigateToApp() throws UiObjectNotFoundException
    {
        mDevice.pressHome();
        UiObject allAppsButton = mDevice.findObject(new UiSelector().text("Apps").descriptionContains("Apps"));

        if (!allAppsButton.exists())
        {
            throw new RuntimeException("Apps button not found");
        }
        allAppsButton.clickAndWaitForNewWindow();

        UiScrollable appDrawer = new UiScrollable(new UiSelector().scrollable(true));
        appDrawer.scrollIntoView(new UiSelector().text(APP_NAME));

        UiObject libraryApp = mDevice.findObject(new UiSelector().className(android.widget.TextView.class).text(APP_NAME));
        libraryApp.clickAndWaitForNewWindow();

        mDevice.wait(Until.hasObject(By.pkg(APP_PACKAGE)), 5000);
    }
    */


    public void navigateToApp() throws UiObjectNotFoundException
    {
        // Simulate a short press on the HOME button.
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mDevice.pressHome();

        // Bring up the All Apps screen (requires English locale)
        UiObject allAppsButton = null;
        allAppsButton = mDevice.findObject(new UiSelector().text("Apps"));

        // In some devices "Apps" is part of contentDescription
        if (!allAppsButton.exists()) {
            UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));
            appViews.swipeUp(10);
        }

        // Create a UiSelector to find the Library app and simulate
        // a user click to launch the app.
        UiObject libraryApp = mDevice.findObject(new UiSelector().className(android.widget.TextView.class.getName()).text(APP_NAME));
        libraryApp.clickAndWaitForNewWindow();

        // Validate that the package name is the expected one
        libraryAppMainActivity = mDevice.findObject(new UiSelector().packageName(APP_PACKAGE));
        assertTrue("Unable to detect Library App", libraryAppMainActivity.exists());
    }

    public void getBackToHomePage()
    {
        mDevice.pressHome();
    }

    public void verifyHomePageVisible()
    {
        UiObject title = mDevice.findObject(
                new UiSelector()
                        .text(APP_NAME)
                        .className(android.widget.TextView.class));
        assertTrue(title.exists());
    }

    public void verifyBorrowerVisible(String publisher)
    {
        UiObject publisherObj = mDevice.findObject(new UiSelector().textContains(publisher));
        assertTrue(publisherObj.exists());
    }

    public void clickBorrowersButton() throws UiObjectNotFoundException
    {
        UiObject borrowersBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.manage_borrowers)));
        borrowersBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void verifyBookVisible(String book)
    {
        UiObject bookObj = mDevice.findObject(new UiSelector().textContains(book));
        assertTrue(bookObj.exists());
    }

    public void clickBooksButton() throws UiObjectNotFoundException
    {
        UiObject booksBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.manage_books)));
        booksBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void verifyAuthorVisible(String author)
    {
        UiObject authorObj = mDevice.findObject(new UiSelector().textContains(author));
        assertTrue(authorObj.exists());
    }

    public void clickAuthorsButton() throws UiObjectNotFoundException
    {
        UiObject authorsBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.manage_authors)));
        authorsBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void verifyLoanVisible(String loan)
    {
        UiObject loanObj = mDevice.findObject(new UiSelector().textContains(loan));
        assertTrue(loanObj.exists());
    }

    public void clickLoansButton() throws UiObjectNotFoundException
    {
        UiObject borrowersBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.manage_loans)));
        borrowersBtn.clickAndWaitForNewWindow();
        UiObject loansBtn = mDevice.findObject(new UiSelector().textContains("Ακρίδας"));
        loansBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void verifyItemsVisible(String item)
    {
        UiObject itemObj = mDevice.findObject(new UiSelector().textContains(item));
        assertTrue(itemObj.exists());
    }

    public void clickItemsButton() throws UiObjectNotFoundException
    {
        UiObject borrowersBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.manage_items)));
        borrowersBtn.clickAndWaitForNewWindow();
        UiObject loansBtn = mDevice.findObject(new UiSelector().textContains("Don Quixote"));
        loansBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void verifyReturnsVisible(String returns)
    {
        UiObject returnsObj = mDevice.findObject(new UiSelector().textContains(returns));
        assertTrue(returnsObj.exists());
    }

    public void clickReturnsButton() throws UiObjectNotFoundException
    {
        UiObject borrowersBtn = mDevice.findObject(new UiSelector().textContains(context.getString(R.string.manage_returns)));
        borrowersBtn.clickAndWaitForNewWindow();
        UiObject loansBtn = mDevice.findObject(new UiSelector().textContains("Δραγούμης"));
        loansBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }

    public void verifyPublisherVisible(String publisher)
    {
        UiObject publisherText = mDevice.findObject(
                new UiSelector()
                        .descriptionContains(publisher));
        assertTrue(publisherText.exists());
    }

    public void clickPublishersButton() throws UiObjectNotFoundException
    {
        UiObject publishersBtn = mDevice.findObject(
                new UiSelector()
                        .descriptionContains(context.getString(R.string.manage_publishers)));
        publishersBtn.clickAndWaitForNewWindow();
        mDevice.waitForIdle();
    }
}