package com.example.android.rssreader;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

public class RssfeedActivity extends Activity implements
        MyListFragment.OnItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(tb);

        if (getResources().getBoolean(R.bool.twoPaneMode)) {
// all good, we use the fragments defined in the layout
            return;
        }
// if savedInstanceState is null we do some cleanup
        if (savedInstanceState != null) {
// cleanup any existing fragments in case we are in detailed mode ①
            getFragmentManager().executePendingTransactions();
            Fragment fragmentById = getFragmentManager().
                    findFragmentById(R.id.fragment_container);
            if (fragmentById != null) {
                getFragmentManager().beginTransaction()
                        .remove(fragmentById).commit();
            }
        }
        MyListFragment listFragment = new MyListFragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, listFragment).commit();


    }

    @Override
    public void onRssItemSelected(String link) {
        if (getResources().getBoolean(R.bool.twoPaneMode)) {
            DetailFragment fragment = (DetailFragment) getFragmentManager()
                    .findFragmentById(R.id.detailFragment);
            fragment.setText(link);
        } else {
// replace the fragment
// Create fragment and give it an argument for the selected article
            DetailFragment newFragment = new DetailFragment();
            Bundle args = new Bundle();
            args.putString(DetailFragment.EXTRA_URL, link);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
// Commit the transaction
            transaction.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        tb.inflateMenu(R.menu.mainmenu);
        tb.setOnMenuItemClickListener(
                new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return onOptionsItemSelected(item);
                    }
                });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                Intent i = new Intent(this, RssDownloadService.class);
                i.putExtra("url", "http://www.vogella.com/article.rss");
                startService(i);
                return true;
            case R.id.action_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_network:
                Intent wirelessIntent = new Intent("android.settings.WIRELESS_SETTINGS");
                startActivity(wirelessIntent);
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
