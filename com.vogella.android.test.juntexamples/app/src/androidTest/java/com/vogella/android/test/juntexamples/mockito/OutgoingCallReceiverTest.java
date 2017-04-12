package com.vogella.android.test.juntexamples.mockito;

import android.content.Context;
import android.content.Intent;
import android.support.test.runner.AndroidJUnit4;

import com.vogella.android.test.juntexamples.OutgoingCallReceiver;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
public class OutgoingCallReceiverTest {
    private OutgoingCallReceiver mReceiver;
    private Context mContext;

    @Before
    public void setUp() {
        mReceiver = new OutgoingCallReceiver();
        mContext = mock(Context.class);
    }

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Captor
    private ArgumentCaptor<Intent> captor;

    @Test
    public void activityShouldGetCorrectIntentData() {
        // prepare data for onReceive and call it
        Intent intent = new Intent(Intent.ACTION_NEW_OUTGOING_CALL);
        intent.putExtra(Intent.EXTRA_PHONE_NUMBER, "01234567890");

        mReceiver.onReceive(mContext, intent);
        assertNull(mReceiver.getResultData());

        // what did receiver do?
        verify(mContext, times(1)).startActivity(captor.capture());
        Intent receivedIntent = captor.getValue();
        assertNull(receivedIntent.getAction());
        assertEquals("01234567890", receivedIntent.getStringExtra("phoneNum"));
        assertTrue((receivedIntent.getFlags() &
                Intent.FLAG_ACTIVITY_NEW_TASK) != 0);
    }
}