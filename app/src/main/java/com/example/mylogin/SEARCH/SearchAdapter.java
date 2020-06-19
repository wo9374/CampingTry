package com.example.mylogin.SEARCH;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
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

        holder.image.setImageBitmap(item.getImage());
        holder.title.setText(item.getTitle());
        holder.content.setText(item.getContent());
        holder.address.setText(item.getAddress());
        holder.price.setText(item.getPrice());
        holder.code.setText(item.getCode());
        holder.url.setText(item.getUrl());
        holder.star.setRating(item.getStar());
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
        TextView code;
        TextView url;
        RatingBar star;
        Button Detail_btn;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image_slide);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
            add_icon = itemView.findViewById(R.id.add_icon);
            address = itemView.findViewById(R.id.address);
            bill = itemView.findViewById(R.id.bill);
            price = itemView.findViewById(R.id.price);
            code = itemView.findViewById(R.id.code);
            url = itemView.findViewById(R.id.url);

            star = itemView.findViewById(R.id.star);

            Detail_btn = itemView.findViewById(R.id.Detail_btn);
            Detail_btn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(v.getContext(), DetailInformation.class);
                    intent.putExtra("code",code.getText());
                    intent.putExtra("url",url.getText());
                    intent.putExtra("name",title.getText());

                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
