package com.vogella.android.test.juntexamples;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@MediumTest
@RunWith(AndroidJUnit4.class)
public class SecondActivityTest {

    @Rule
   public ActivityTestRule<SecondActivity> rule  = new  ActivityTestRule<SecondActivity>(SecondActivity.class)
    {
        @Override
        protected Intent getActivityIntent() {
            InstrumentationRegistry.getTargetContext();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.putExtra("MYKEY", "Hello");
            return intent;
        }
    };

    @Test
    public void ensureIntentDataIsDisplayed() throws Exception {
        SecondActivity activity = rule.getActivity();

        View viewById = activity.findViewById(R.id.target);

        assertThat(viewById,notNullValue());
        assertThat(viewById, instanceOf(TextView.class));
        TextView textView = (TextView) viewById;
        assertThat(textView.getText().toString(),is("Hello"));
    }
}