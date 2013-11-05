package de.vogella.android.intent.getlastones;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RecentTaskInfo;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ActivityManager service = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		List<RecentTaskInfo> recentTasks = service.getRecentTasks(5,
				ActivityManager.RECENT_WITH_EXCLUDED);
		for (RecentTaskInfo recentTaskInfo : recentTasks) {
			System.out.println(recentTaskInfo.baseIntent);
		}
	}

}