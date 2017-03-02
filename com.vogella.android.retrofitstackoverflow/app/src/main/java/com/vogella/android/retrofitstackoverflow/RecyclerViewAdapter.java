package com.vogella.android.retrofitstackoverflow;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private final View.OnClickListener listener;
    private List<Object> data;

    private static final int VIEW_TYPE_QUESTION = 0;
    private static final int VIEW_TYPE_ANSWER = 1;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text;

        public ViewHolder(View v) {
            super(v);
            text = (TextView) v.findViewById(android.R.id.text1);
        }
    }

    public RecyclerViewAdapter(List<Object> data, View.OnClickListener onItemClickListener) {
        this.data = data;
        this.listener = onItemClickListener;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if (viewType == VIEW_TYPE_QUESTION) {
            v = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            v.setBackgroundColor(parent.getContext().getResources().getColor(android.R.color.darker_gray));
        } else {
            v = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_selectable_list_item, parent, false);
            v.setOnClickListener(listener);
        }
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_QUESTION) {
            Question question = ((Question) data.get(position));
            holder.text.setText(Html.fromHtml(question.body).toString());
        } else {
            Answer answer = ((Answer) data.get(position));
            holder.text.setText(answer.toString());
            holder.itemView.setTag(answer.answerId);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_QUESTION;
        } else {
            return VIEW_TYPE_ANSWER;
        }
    }
}
