package de.vogella.android.tabs;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class TabTest extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab);
        TabHost host = (TabHost)findViewById(android.R.id.tabhost);
        TabSpec spec = host.newTabSpec("tab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Button1");
        
        host.addTab(spec);
        spec = host.newTabSpec("tab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Next Button");
        host.addTab(spec);
        spec = host.newTabSpec("tab3");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Just some text");
        host.addTab(spec);
        
    }
}