package com.example.android.rssreader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import android.app.Application;
import com.example.android.rssfeedlibrary.RssItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

public class RssApplication extends Application {
    public final static String URL ="url";
    public final static String RSS_UPDATE ="neuedaten";
    public final static String RSS_FILE ="rssitems.json";
    public static List<RssItem> list;


    static Bus bus;

    @Override
    public void onCreate() {
        super.onCreate();
        bus = new Bus(ThreadEnforcer.ANY);

        list = new ArrayList<>();
        StringBuffer buffer = new StringBuffer();
        try (BufferedReader input = new BufferedReader(
                new InputStreamReader(
                        openFileInput(RSS_FILE)))) {
            String line;
            while ((line = input.readLine()) != null) {
                buffer.append(line);
            }
        } catch (Exception ex) {
// do nothing
        }
        if (buffer!=null && buffer.length()>0 )
        {
            Gson gson = new Gson();
            Type type = new TypeToken<List<RssItem>>() {}.getType();
            List<RssItem> fromJson = gson.fromJson(buffer.toString(), type);
            list.addAll(fromJson);
        }
    }
}