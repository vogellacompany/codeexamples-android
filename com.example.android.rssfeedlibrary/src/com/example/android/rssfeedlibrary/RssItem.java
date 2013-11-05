package com.example.android.rssfeedlibrary;

public class RssItem {
	private String pubDate;
	private String description;
	private String link;
	private String title;

	public RssItem() {
	}

	public RssItem(String title, String link) {
		this.title = title;
		this.link = link;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "RssItem [title=" + title + "]";
	}

}