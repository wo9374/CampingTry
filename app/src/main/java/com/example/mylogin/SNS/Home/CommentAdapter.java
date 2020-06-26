package com.example.mylogin.SNS.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylogin.R;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>{
    private ArrayList<CommentItem> mData = null;

    CommentAdapter(ArrayList<CommentItem> list){
        this.mData = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context =parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.holder_comment, parent, false);
        CommentAdapter.ViewHolder vh = new CommentAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CommentItem item = mData.get(position);

        holder.username.setText(item.getUsername());
        holder.comment.setText(item.getContent());
        holder.time.setText(item.getTime());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView username;
        TextView time;
        TextView comment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username);
            comment = itemView.findViewById(R.id.comment);
            time = itemView.findViewById(R.id.time);

        }
    }
}
