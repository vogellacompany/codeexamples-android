package de.vogella.android.fragment.pictures;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class PictureFragment extends Fragment {
	ImageView mImage;
	TextView mDescription;
	TextView mDay;
	TextView mTitle;
	ViewGroup mHeader;
	ViewGroup mNotes;
	boolean mNotesShowing = false;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null) {
			// Currently in a layout without a container, so no
			// reason to create our view.
			return null;
		}
		View theView = inflater.inflate(R.layout.picture, null);
		mImage = (ImageView) theView.findViewById(R.id.image);
		mNotes = (ViewGroup) theView.findViewById(R.id.notes);
		mDescription = (TextView) theView.findViewById(R.id.description);
		mDay = (TextView) theView.findViewById(R.id.day);
		mTitle = (TextView) theView.findViewById(R.id.title);
		mHeader = (ViewGroup) theView.findViewById(R.id.header);
		mNotes.setAlpha(0);

		refreshPictureInfo();
		mImage.setOnClickListener(notesClickListener);
		mHeader.setOnClickListener(notesClickListener);

		return theView;
	}

	public void refreshPictureInfo() {
		PictureInfo info = PictureListFragment.getCurrentPictureInfo();
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
				info.resourceId);
		mImage.setImageBitmap(bitmap);
		mDescription.setText(info.description);
		mDay.setText(info.day);
		mTitle.setText(info.name);
	}

	class FadingListener extends AnimatorListenerAdapter {
		boolean mFadingIn;

		public FadingListener(boolean fadingIn) {
			mFadingIn = fadingIn;
		}

		@Override
		public void onAnimationStart(Animator animation) {
			mNotes.setLayerType(View.LAYER_TYPE_HARDWARE, null);
			mNotes.buildLayer();
		}

		@Override
		public void onAnimationEnd(Animator animation) {
			mNotes.setLayerType(View.LAYER_TYPE_NONE, null);
			if (!mFadingIn) {
				mNotes.setVisibility(View.INVISIBLE);
			}
		}
	};

	private View.OnClickListener notesClickListener = new View.OnClickListener() {

		FadingListener mFadingInListener = new FadingListener(true);
		FadingListener mFadingOutListener = new FadingListener(false);

		@Override
		public void onClick(View v) {
			if (!mNotesShowing) {
				mNotesShowing = true;
				mNotes.setVisibility(View.VISIBLE);
				mNotes.setTranslationY(getView().getHeight());
				mNotes.animate().setListener(mFadingInListener).translationY(0)
						.alpha(1);
				mImage.animate().translationY(-mImage.getTop()).alpha(.6f);
			} else {
				mNotes.animate().setListener(mFadingOutListener)
						.translationY(getView().getHeight()).alpha(0);
				mImage.animate().translationY(0).alpha(1);
				mNotesShowing = false;
			}
		}
	};

}
