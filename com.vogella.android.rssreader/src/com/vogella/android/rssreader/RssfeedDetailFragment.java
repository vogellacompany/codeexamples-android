package com.vogella.android.rssreader;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class RssfeedDetailFragment extends Fragment {

	private WebView webview;

	public static RssfeedDetailFragment instantiate(String rssItemUrl) {
		// create instance
		RssfeedDetailFragment fragment = new RssfeedDetailFragment();
		
		// initialize and store input argument
		if (rssItemUrl != null) {
			Bundle args = new Bundle();
			args.putString("url", rssItemUrl);
			fragment.setArguments(args);
		}
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, 
			ViewGroup container,
			Bundle savedInstanceState) {
		webview = new WebView(getActivity());
		Bundle attrs = getArguments();
		if (attrs != null) {
			String url = attrs.getString("url");
			setUrl(url);
		}
		return webview;
	}

	public void setUrl(String url) {
		getArguments().putString("url", url);
		if (webview != null) {
			webview.setInitialScale(50);
			webview.loadUrl(url);
		}
	}
}