package com.example.android.rssreader;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class RssfeedActivity extends Activity implements
        MyListFragment.OnItemSelectedListener {

    SelectionStateFragment stateFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rssfeed);
        stateFragment =
                (SelectionStateFragment) getFragmentManager()
                        .findFragmentByTag("headless");

        if(stateFragment == null) {
            stateFragment = new SelectionStateFragment();
            getFragmentManager().beginTransaction()
                    .add(stateFragment, "headless").commit();
        }

        if (getResources().getBoolean(R.bool.twoPaneMode)) {
            // restore state
            if (stateFragment.lastSelection.length()>0) {
                onRssItemSelected(stateFragment.lastSelection);
            }
            // otherwise all is good, we use the fragments defined in the layout
            return;
        }
        // if savedInstanceState is null we do some cleanup
        if (savedInstanceState != null) {
            // cleanup any existing fragments in case we are in detailed mode
            getFragmentManager().executePendingTransactions();
            Fragment fragmentById = getFragmentManager().
                    findFragmentById(R.id.fragment_container);
            if (fragmentById!=null) {
                getFragmentManager().beginTransaction()
                        .remove(fragmentById).commit();
            }
        }

        MyListFragment listFragment = new MyListFragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, listFragment).commit();
    }

    @Override
    public void onRssItemSelected(String text) {
        stateFragment.lastSelection = text;
        if (getResources().getBoolean(R.bool.twoPaneMode)) {
            DetailFragment fragment = (DetailFragment) getFragmentManager()
                    .findFragmentById(R.id.detailFragment);
            fragment.setText(text);
        } else {
            // replace the fragment
            // Create fragment and give it an argument for the selected article
            DetailFragment newFragment = new DetailFragment();
            Bundle args = new Bundle();
            args.putString(DetailFragment.EXTRA_TEXT, text);
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
}