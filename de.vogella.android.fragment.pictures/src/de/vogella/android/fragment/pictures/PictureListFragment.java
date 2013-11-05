package de.vogella.android.fragment.pictures;

import java.util.ArrayList;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class PictureListFragment extends ListFragment {

	static ArrayList<PictureInfo> sPics = new ArrayList<PictureInfo>();
	static int sCurrentIndex = 0;
	boolean mDualPane;

	static {
		PictureData.setupPics(sPics);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ListAdapter adapter = new ArrayAdapter<PictureInfo>(getActivity(),
				android.R.layout.simple_list_item_1, sPics);
		setListAdapter(adapter);
		View picture = getActivity().findViewById(R.id.picture);
		mDualPane = picture != null;
		if (mDualPane) {
			PictureFragment pictureFragment = (PictureFragment) getFragmentManager()
					.findFragmentById(R.id.picture);
			if (pictureFragment == null) {
				pictureFragment = new PictureFragment();
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.picture, pictureFragment);
				ft.commit();
			}
		}
	}

	public static PictureInfo getCurrentPictureInfo() {
		return sPics.get(sCurrentIndex);
	}

	public void onListItemClick(ListView l, View v, int position, long id) {
		sCurrentIndex = position;
		if (mDualPane) {
			PictureFragment pictureFragment = (PictureFragment) getFragmentManager()
					.findFragmentById(R.id.picture);
			if (pictureFragment != null) {
				pictureFragment.refreshPictureInfo();
			}
		} else {
			Intent intent = new Intent(getActivity(), PictureActivity.class);
			startActivity(intent);
		}
	}

}
