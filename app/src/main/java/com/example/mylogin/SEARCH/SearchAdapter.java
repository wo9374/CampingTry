package com.example.mylogin.SEARCH;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mylogin.R;
import com.example.mylogin.SEARCH.Detail.DetailInformation;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private ArrayList<SearchRecycleItem> mData = null;

    public SearchAdapter(ArrayList<SearchRecycleItem> list) {
        this.mData = list;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context =parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.search_holder,parent,false);
        SearchAdapter.ViewHolder vh = new SearchAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        SearchRecycleItem item = mData.get(position);

        holder.image.setImageResource(item.getImage());
        holder.title.setText(item.getTitle());
        holder.content.setText(item.getContent());
        holder.address.setText(item.getAddress());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title;
        TextView content;
        ImageView add_icon;
        TextView address;
        TextView bill;
        TextView price;

        Button Detail_btn;
        Button Review_btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image_slide);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
            add_icon = itemView.findViewById(R.id.add_icon);
            address = itemView.findViewById(R.id.address);
            bill = itemView.findViewById(R.id.bill);
            price = itemView.findViewById(R.id.price);


            Detail_btn = itemView.findViewById(R.id.Detail_btn);
            Review_btn = itemView.findViewById(R.id.Review_btn);

            Detail_btn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //Intent intent = new Intent(this, DetailInformation.class);
                }
            });
        }
    }
}
