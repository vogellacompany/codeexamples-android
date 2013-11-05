package de.vogella.android.fragment.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity {
	private MyAdapter mAdapter;
	private ViewPager mPager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mPager = (ViewPager) findViewById(R.id.pager);
		
		mAdapter = new MyAdapter(getSupportFragmentManager());
		
		mPager.setAdapter(mAdapter);
	}

	public static class MyAdapter extends FragmentPagerAdapter {
		public MyAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				return new DetailFragment();
			case 1:
				return new ImageFragment(R.drawable.human);
			case 2:
				return new ImageFragment(R.drawable.logo);

			default:
				return null;
			}
		}
	}
}