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

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ViewHolder>{

    private ArrayList<AccountItem> mData= null;

    AccountAdapter(ArrayList<AccountItem> list){
        this.mData = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context =parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.holder_account,parent,false);
        AccountAdapter.ViewHolder vh = new AccountAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AccountItem item = mData.get(position);

        holder.username.setText(item.getUsername());
        holder.time.setText(item.getTime());
        holder.content.setText(item.getContent());
        holder.img_slide.setImageBitmap(item.getImage());
        holder.like.setText(item.getLike());
        holder.comment.setText(item.getComment());
        holder.snscode.setText(item.getSnscode());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView username;
        TextView time;
        TextView content;
        ImageView img_slide;

        TextView like;
        TextView comment;

        TextView snscode;

        Button like_btn;
        Button comment_btn;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            username = itemView.findViewById(R.id.username);
            time = itemView.findViewById(R.id.time);
            content = itemView.findViewById(R.id.content);
            img_slide = itemView.findViewById(R.id.img_slide);
            like = itemView.findViewById(R.id.like);
            comment = itemView.findViewById(R.id.comment);

            snscode = itemView.findViewById(R.id.snscode);

            like_btn = itemView.findViewById(R.id.like_btn);
            comment_btn = itemView.findViewById(R.id.comment_btn);


            comment_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
