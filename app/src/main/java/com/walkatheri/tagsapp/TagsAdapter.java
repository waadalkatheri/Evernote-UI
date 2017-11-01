package com.walkatheri.tagsapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;


public class TagsAdapter extends RecyclerView.Adapter<TagsAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<String> tags;

    private OnClickListener mListener;

    public TagsAdapter(Context mContext, ArrayList<String> tags) {
        this.mContext = mContext;
        this.tags = tags;
    }

    public void setOnClick(OnClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.tags_item_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final String tag = tags.get(position);

        holder.tagCB.setText(tag);

        holder.tagCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mListener.onClick(tag, isChecked);
            }
        });

    }

    @Override
    public int getItemCount() {
        return tags.size();
    }

    public interface OnClickListener{
        public void onClick(String tag, Boolean isChecked);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CheckBox tagCB;

        public ViewHolder(View itemView) {
            super(itemView);

            tagCB = (CheckBox) itemView.findViewById(R.id.tags_item_cb);
        }
    }
}
