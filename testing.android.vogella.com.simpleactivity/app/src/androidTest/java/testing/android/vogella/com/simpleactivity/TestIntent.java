package testing.android.vogella.com.simpleactivity;


import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNull.notNullValue;


@RunWith(AndroidJUnit4.class)
public class TestIntent {

    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule<>(MainActivity.class);

    @Test
    public void triggerIntentTest() {
        // check that the button is there
        onView(withId(R.id.button)).check(matches(notNullValue() ));
        onView(withId(R.id.button)).check(matches(withText("Start new activity")));
        onView(withId(R.id.button)).perform(click());
        intended(toPackage("testing.android.vogella.com.simpleactivity"));
        intended(hasExtra("URL", "http://www.vogella.com"));
    }

}
