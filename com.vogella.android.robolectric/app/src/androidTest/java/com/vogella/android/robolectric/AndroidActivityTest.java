package com.vogella.android.robolectric;

import android.content.Context;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class AndroidActivityTest {

    @Rule
    public ActivityTestRule<RobolectricActivity> rule = new ActivityTestRule<RobolectricActivity>(RobolectricActivity.class);

    @Test
    public void shouldHaveDefaultMargin()  {
        RobolectricActivity activity = rule.getActivity();
        TextView textView = (TextView) activity.findViewById(R.id.hello_textview);

        int bottomMargin = ((LinearLayout.LayoutParams) textView.getLayoutParams()).bottomMargin;
        assertEquals(dpToPx(activity.getApplicationContext(),5), bottomMargin);
        int topMargin = ((LinearLayout.LayoutParams) textView.getLayoutParams()).topMargin;
        assertEquals(dpToPx(activity.getApplicationContext(),5), topMargin);
        int rightMargin = ((LinearLayout.LayoutParams) textView.getLayoutParams()).rightMargin;
        assertEquals(dpToPx(activity.getApplicationContext(),10), rightMargin);
        int leftMargin = ((LinearLayout.LayoutParams) textView.getLayoutParams()).leftMargin;
        assertEquals(dpToPx(activity.getApplicationContext(),10) , leftMargin);
    }

    public static int dpToPx(Context context, int dp) {
        int px = Math.round(dp * getPixelScaleFactor(context));
        return px;
    }
    private static float getPixelScaleFactor(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT);
    }

}