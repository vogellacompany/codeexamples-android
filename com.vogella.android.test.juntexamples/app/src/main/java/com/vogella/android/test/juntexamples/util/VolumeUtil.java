package com.vogella.android.test.juntexamples.util;

import android.media.AudioManager;

import static android.media.AudioManager.RINGER_MODE_SILENT;
import static android.media.AudioManager.STREAM_RING;

/**
 * Created by vogella on 19.06.16.
 */

public class VolumeUtil {
    public static  void maximizeVolume(AudioManager audioManager) {
        if (audioManager.getRingerMode() != RINGER_MODE_SILENT) {
            int max = audioManager.getStreamMaxVolume(STREAM_RING);
            audioManager.setStreamVolume(STREAM_RING, max, 0);
        }
    }
}
