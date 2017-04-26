package com.vogella.android.otto;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

public class BottomFragment extends Fragment {

    private TextView bottomTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApplication)getActivity().getApplicationContext()).getOttoBus().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bottom, container, false);
        bottomTextView = (TextView) v.findViewById(R.id.bottom_textview);
        return v;
    }

    @Subscribe
    public void buttonClickAvailable(final View v){
        if(v instanceof FloatingActionButton) {
            bottomTextView.setVisibility(View.VISIBLE);
            bottomTextView.setText("Button pushed");
            bottomTextView.animate().alpha(0).setDuration(2000).withEndAction(new Runnable() {
                @Override public void run() {
                    bottomTextView.setAlpha(1);
                    bottomTextView.setVisibility(View.GONE);
                }
            });
        }
    }
}
