package com.vogella.android.test.juntexamples;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by vogella on 11.04.17.
 */

public class OutgoingCallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context ctx, Intent i) {
        if(i.getAction().equalsIgnoreCase(Intent.ACTION_NEW_OUTGOING_CALL))
        {
            String phoneNum = i.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            Intent intent = new Intent(ctx, MainActivity.class);
            intent.putExtra("phoneNum", phoneNum);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ctx.startActivity(intent);
        }
    }
}
