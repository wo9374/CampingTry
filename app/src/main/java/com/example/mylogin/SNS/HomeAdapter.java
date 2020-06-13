package com.example.mylogin.SNS;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mylogin.R;
import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{

    private ArrayList<HomeItem> mData = null;

    HomeAdapter(ArrayList<HomeItem> list){
        this.mData = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context =parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.sns_holder,parent,false);
        HomeAdapter.ViewHolder vh = new HomeAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeItem item = mData.get(position);

        holder.username.setText(item.getUsername());
        holder.time.setText(item.getTime());
        holder.content.setText(item.getContent());
        holder.img_slide.setImageResource(item.getImage());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView username;
        TextView time;
        TextView content;
        ImageView img_slide;

        Button like;
        Button comment;
        Button chat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username);
            time = itemView.findViewById(R.id.time);
            content = itemView.findViewById(R.id.content);
            img_slide = itemView.findViewById(R.id.img_slide);

            like = itemView.findViewById(R.id.like);
            comment = itemView.findViewById(R.id.comment);
            chat = itemView.findViewById(R.id.chat);


        }
    }
}
