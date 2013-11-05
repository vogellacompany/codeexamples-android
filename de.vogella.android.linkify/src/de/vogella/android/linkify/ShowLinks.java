package de.vogella.android.linkify;

import android.app.Activity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.TextView;

public class ShowLinks extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
       TextView view =  (TextView) findViewById(R.id.textview);
       Linkify.addLinks(view, Linkify.WEB_URLS| Linkify.EMAIL_ADDRESSES);
    }
}