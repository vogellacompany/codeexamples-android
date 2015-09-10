package com.vogella.android.test.uiautomator;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.sucom.vogella.android.test.uiautomatorpport.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MyUiAutomatorTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    private UiDevice mDevice;

    @Before
    public void setUp() {
        // Initialize UiDevice instance

        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        // Start from the home screen
        mDevice.pressHome();

        mDevice.wait(Until.hasObject(By.pkg(getLauncherPackageName()).depth(0)), 1000);
    }

    @Test
    public void checkSettings() throws UiObjectNotFoundException {

        // Simulate a short press on the HOME button.
        mDevice.pressHome();

        // We’re now in the home screen. Next, we want to simulate
        // a user bringing up the All Apps screen.
        // If you use the uiautomatorviewer tool to capture a snapshot
        // of the Home screen, notice that the All Apps button’s
        // content-description property has the value “Apps”. We can
        // use this property to create a UiSelector to find the button.
        UiObject allAppsButton = new UiObject(new UiSelector().description("Apps"));

        // Simulate a click to bring up the All Apps screen.
        allAppsButton.clickAndWaitForNewWindow();

        // In the All Apps screen, the Settings app is located in
        // the Apps tab. To simulate the user bringing up the Apps tab,
        // we create a UiSelector to find a tab with the text
        // label “Apps”.
        UiObject appsTab = new UiObject(new UiSelector().text("Apps"));

        // Simulate a click to enter the Apps tab.
        appsTab.click();

        // Next, in the apps tabs, we can simulate a user swiping until
        // they come to the Settings app icon. Since the container view
        // is scrollable, we can use a UiScrollable object.
        UiScrollable appViews = new UiScrollable(
                new UiSelector().scrollable(true));

        // Set the swiping mode to horizontal (the default is vertical)
        appViews.setAsHorizontalList();

        // create a UiSelector to find the Settings app and simulate
        // a user click to launch the app.
        UiObject settingsApp = appViews
                .getChildByText(new UiSelector()
                                .className(android.widget.TextView.class.getName()),
                        "Settings");
        settingsApp.clickAndWaitForNewWindow();

        // Validate that the package name is the expected one
        UiObject settingsValidation = new UiObject(
                new UiSelector()
                        .packageName("com.android.settings"));
        assertThat(settingsValidation.exists(), equalTo(true));
    }

    private String getLauncherPackageName() {
        // Create launcher Intent
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);

        // Use PackageManager to get the launcher package name
        PackageManager pm = InstrumentationRegistry.getContext().getPackageManager();
        ResolveInfo resolveInfo = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return resolveInfo.activityInfo.packageName;
    }
}