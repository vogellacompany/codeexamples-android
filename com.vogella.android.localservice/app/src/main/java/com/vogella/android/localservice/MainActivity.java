package com.vogella.android.localservice;

import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity implements ServiceConnection {
    private LocalWordService s;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wordList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1,
                wordList);

        setListAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent= new Intent(this, LocalWordService.class);
        bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(this);
    }

    private ArrayAdapter<String> adapter;
    private List<String> wordList;

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.updateList:
                if (s != null) {
                    Toast.makeText(this, "Number of elements" + s.getWordList().size(),
                            Toast.LENGTH_SHORT).show();
                    wordList.clear();
                    wordList.addAll(s.getWordList());
                    adapter.notifyDataSetChanged();
                }
                break;
            case R.id.triggerServiceUpdate:
                Intent service = new Intent(getApplicationContext(), LocalWordService.class);
                getApplicationContext().startService(service);
                break;
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder binder) {
        LocalWordService.MyBinder b = (LocalWordService.MyBinder) binder;
        s = b.getService();
        Toast.makeText(MainActivity.this, "Connected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        s = null;
    }
}