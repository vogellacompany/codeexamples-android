package com.vogella.android.test.juntexamples;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.runner.AndroidJUnit4;
import android.view.LayoutInflater;
import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class ContextInstrumentationTests {
    Context appContext;

    @Before
    public void setup(){
        // Context of the app under test.
        appContext = InstrumentationRegistry.getTargetContext();
    }
    @Test
    public void packageNameShouldBeCorrect() throws Exception {
        // ensure tha the package of the appContext is "com.vogella.android.test.juntexamples"
        assertEquals("com.vogella.android.test.juntexamples", appContext.getPackageName());
    }
    @Test
    public void appNameShouldBeCorrect() throws Exception {
        // ensure R.string.app_name is set to "JUnit Examples"
        assertEquals(appContext.getResources().getString(R.string.app_name), "JUnit Examples" );

    }

    @Test
    public void mainActivityLayoutShouldHaveConstraintLayoutAsRoot() throws Exception {
        //
        LayoutInflater layoutInflater = appContext.getSystemService(LayoutInflater.class);
        View inflate = layoutInflater.inflate(R.layout.activity_main, null, false);
        assertThat(inflate, instanceOf(ConstraintLayout.class));
    }
}