package com.example.mylogin.SEARCH;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mylogin.R;

public class SearchViewHolder extends RecyclerView.ViewHolder {
    public ViewFlipper viewFlipper;
    public TextView title;
    public TextView content;
    public ListView listView;
    public TextView address;

    public SearchViewHolder(@NonNull View itemView) {
        super(itemView);
        viewFlipper = itemView.findViewById(R.id.image_slide);
        title = itemView.findViewById(R.id.title_name);
        content = itemView.findViewById(R.id.content_holder);
        listView = itemView.findViewById(R.id.icon_listview);
        address = itemView.findViewById(R.id.address);
    }
}
