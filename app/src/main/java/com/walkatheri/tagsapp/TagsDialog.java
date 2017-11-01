package com.walkatheri.tagsapp;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;

import com.anton46.collectionitempicker.CollectionPicker;
import com.anton46.collectionitempicker.Item;
import com.anton46.collectionitempicker.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by waad on 4/20/17.
 */

public class TagsDialog extends Dialog{

    List<Item> items;
    CollectionPicker picker;

    public TagsDialog(@NonNull Context context) {
        super(context);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.tags_dialog_layout);
        this.setCancelable(true);

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        this.getWindow().setLayout((9 * width)/10, (9*height)/10);

        ArrayList<String> tags = new ArrayList<>();

        tags.add("action");
        tags.add("approved");
        tags.add("home");
        tags.add("IFTTT");
        tags.add("rejected");
        tags.add("slides");
        tags.add("status");
        tags.add("taskclone");
        tags.add("shopmake");
        tags.add("dumpy");

        final TagsAdapter adapter = new TagsAdapter(context, tags);
        adapter.setOnClick(new TagsAdapter.OnClickListener() {
            @Override
            public void onClick(String tag, Boolean isChecked) {
                Log.i("tag", "onClick: "+tag+" :: "+isChecked);
                if(isChecked){
                    items.add(new Item(tag, tag));
                    picker.drawItemView();
                }else{
                    for(int i=0 ; i<items.size() ; i++){
                        if(items.get(i).id.equals(tag)){
                            items.remove(i);
                            picker.drawItemView();
                            break;
                        }
                    }
                }
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

        RecyclerView tagsRecyclerView = (RecyclerView) findViewById(R.id.tags_dialog_tags_recycler_view);

        tagsRecyclerView.setLayoutManager(layoutManager);
        tagsRecyclerView.setAdapter(adapter);

        picker = (CollectionPicker) findViewById(R.id.tags_dialog_tags);

        items = new ArrayList<>();

        picker.setItems(items);

        picker.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(Item item, int position) {
                if(!item.isSelected){
                    Log.i("tag", "onClick: ");
                    items.remove(position);
                    picker.drawItemView();
                }
            }
        });


    }
}
