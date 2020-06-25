package com.example.mylogin.SEARCH.Detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mylogin.R;
import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewViewHolder> {

    private ArrayList<ReviewItem> ReviewItems;

    public void setData(ArrayList<ReviewItem> list){
        ReviewItems = list;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 사용할 아이템의 뷰를 생성해준다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_reciew, parent, false);

        ReviewViewHolder holder = new ReviewViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        ReviewItem data = ReviewItems.get(position);

        holder.time.setText(data.getTime());
        holder.star.setRating(data.getStar());
        holder.name.setText(data.getName());
        holder.content.setText(data.getContent());

    }

    @Override
    public int getItemCount() {
        return ReviewItems.size();
    }
}

class ReviewViewHolder extends RecyclerView.ViewHolder{
    public RatingBar star;
    public TextView name;
    public TextView content;
    public TextView time;


    public ReviewViewHolder(View itemView){
        super(itemView);

        star = (RatingBar) itemView.findViewById(R.id.star);
        name = (TextView) itemView.findViewById(R.id.name);
        content = (TextView) itemView.findViewById(R.id.content);
        time = (TextView) itemView.findViewById(R.id.time);
    }
}
