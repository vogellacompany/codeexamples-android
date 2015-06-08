package testing.android.vogella.com.simpleactivity;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class SecondActivityFunctionalTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    @SmallTest
    public void validateSecondActivity() {
        // check that the button is there
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.resultText))
                .check(matches(withText(("Started"))));
        pressBack();
        onView(withId(R.id.button))
                .check(matches(withText(("Start new activity"))));

    }

    @Test
    @MediumTest
    public void validateSecondActivityAgain() {
        // check that the button is there
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.resultText))
                .check(matches(withText(("Started"))));
        pressBack();
        onView(withId(R.id.button))
                .check(matches(withText(("Start new activity"))));

    }

}
