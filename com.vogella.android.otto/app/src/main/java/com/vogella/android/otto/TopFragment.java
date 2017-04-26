package com.vogella.android.otto;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TopFragment extends android.app.Fragment {

    private FloatingActionButton button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_top, container, false);
        button = (FloatingActionButton) v.findViewById(R.id.top_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApplication) getActivity().getApplicationContext()).getOttoBus().post(v);
            }
        });
        return v;
    }
}
