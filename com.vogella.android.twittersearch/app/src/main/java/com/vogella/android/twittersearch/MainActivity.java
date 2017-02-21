package com.vogella.android.twittersearch;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getActionBar();
//        actionBar.setHomeAsUpIndicator(null);
// add the custom view to the action bar
        actionBar.setCustomView(R.layout.toolbarsearch);
        EditText search = (EditText) actionBar.getCustomView().findViewById(
                R.id.searchfield);
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                onClick(null);
                return false;
            }
        });
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meinmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       if (item.getItemId()== R.id.action_search) {
           Toast.makeText(this, "Searching....", Toast.LENGTH_SHORT).show();
            onClick(null);
       }
        return true;
    }

    public void onClick(View view) {
//        https://twitter.com/search?q=Android
        Intent i = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://twitter.com/search?q=Android"));
        startActivity(i);
        WebView webview = (WebView) findViewById(R.id.webview);
        webview.loadUrl("http://www.vogella.com");
//        WebSettings settings = webview.getSettings();
//        settings.setJavaScriptEnabled(true);
//        webview.setWebViewClient(new SSLTolerentWebViewClient());
//        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
//
//        webview.loadUrl("https://twitter.com/search?q=Android");
////        viewById.
//        String url = "https://paul.kinlan.me/";
//        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
//        CustomTabsIntent customTabsIntent = builder.build();
//        customTabsIntent.launchUrl(this, Uri.parse(url));
    }

    // SSL Error Tolerant Web View Client
    private class SSLTolerentWebViewClient extends WebViewClient {

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed(); // Ignore SSL certificate errors
        }


    }
}
