package de.vogella.android.rss.reader;
import java.util.List;


public interface FeedParser {
    List<Message> parse();
}
