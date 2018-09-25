package com.example.android.rssreader;

import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.rssfeedlibrary.RssFeedProvider;
import com.example.android.rssfeedlibrary.RssItem;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class MyListFragment extends Fragment {
    private OnItemSelectedListener listener;
    RssItemAdapter adapter;
    List<RssItem> rssItems;
    ParseTask parseTask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rsslist_overview, container, false);
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        rssItems = RssApplication.list;;
        adapter = new RssItemAdapter(rssItems, this);
        mRecyclerView.setAdapter(adapter);
        if(rssItems.isEmpty()) { // #1
            updateListContent();
        }
        RssApplication.bus.register(this);
        return view;
    }

    @Override
    public void onDestroyView() {
        RssApplication.bus.unregister(this);
        super.onDestroyView();
    }

    public interface OnItemSelectedListener {
        void onRssItemSelected(String link);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement MyListFragment.OnItemSelectedListener");
        }
    }

    public void updateDetail(String uri) {
        listener.onRssItemSelected(uri);
    }

    public void updateListContent() {
        if (parseTask == null) {
            parseTask = new ParseTask();
            parseTask.setFragment(this);
            parseTask.execute("http://www.vogella.com/article.rss");
        }
    }

    public void setListContent(List<RssItem> result) {
        RssApplication.list = result;
        rssItems.clear();
        rssItems.addAll(result);
        adapter.notifyDataSetChanged();
        if(parseTask != null) {
            parseTask.setFragment(null);
            parseTask = null;
        }
    }

    private static class ParseTask extends
            AsyncTask<String, Void, List<RssItem>> {
        private MyListFragment fragment;
        public void setFragment(MyListFragment fragment) {
            this.fragment = fragment;
        }
        @Override
        protected List<RssItem> doInBackground(String... params) {
            List<RssItem> list = RssFeedProvider.parse(params[0]);
            return list;
        }
        @Override
        protected void onPostExecute(List<RssItem> result) {
            fragment.setListContent(result);
        }
    }

    @Subscribe
    public void update(Object object){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setListContent(new ArrayList<RssItem>(RssApplication.list));
                MyListFragment.this.createNotification();
            }
        });
    }

    private void createNotification() {
        Notification noti = new Notification.Builder(getActivity())
                .setContentTitle("Update").setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher).
                        setContentIntent(PendingIntent.getActivity(getContext(), 0, new Intent
                                (getContext(), RssfeedActivity.class),PendingIntent.FLAG_UPDATE_CURRENT))
                .build();
        NotificationManager notificationManager =
                (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify((int) System.currentTimeMillis(), noti);
    }

}
