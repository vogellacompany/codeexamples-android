package com.vogella.android.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.vogella.android.databinding.databinding.RowlayoutBinding;

public abstract class MyBaseAdapter extends RecyclerView.Adapter<MyBaseAdapter.MyViewHolder> {

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class MyViewHolder extends RecyclerView.ViewHolder {
                // each data item is just a string in this case
                private final ViewDataBinding binding;

                public MyViewHolder(ViewDataBinding binding) {
                        super(binding.getRoot());
                        this.binding = binding;
                }
                public void bind(Object obj) {
                       binding.setVariable(BR.obj,obj);
                       binding.executePendingBindings();
                }
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                // create a new view
                LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
                RowlayoutBinding binding = DataBindingUtil.inflate(layoutInflater, getLayoutIdForType(viewType), parent, false);
                // set the view's size, margins, paddings and layout parameters
                return new MyViewHolder(binding);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
                holder.bind(getDataAtPosition(position));
        }

        public abstract Object getDataAtPosition(int position);

        public abstract int getLayoutIdForType(int viewType);

}