package com.example.mylogin.SNS.Home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mylogin.R;
import com.example.mylogin.SNS.Frag1;

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
        View view = inflater.inflate(R.layout.holder_sns,parent,false);
        HomeAdapter.ViewHolder vh = new HomeAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeItem item = mData.get(position);

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

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView username;
        TextView time;
        TextView content;
        ImageView img_slide;

        TextView like;
        TextView comment;

        TextView snscode;

        Button like_btn;
        Button comment_btn;
        Button chat_btn;

        private boolean like_on=false;

        public ViewHolder(@NonNull View itemView) {
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
            chat_btn = itemView.findViewById(R.id.chat_btn);


            /*for( int i =0; i<Frag1.likelist.size(); i++){
                if(snscode == Frag1.likelist.get(i)){
                    like_btn.setText("좋아요 취소");
                    like_on = true;
                }
            }

            like_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(like_on==true){ //좋아요가 눌러져 있을 때
                        like_btn.setText("좋아요");
                        //여기서 좋아요 취소 쿼리문

                    }else{ //좋아요 안눌러져 있을 때
                        like_btn.setText("좋아요 취소");
                        //여기서 좋아요 증가 쿼리문
                    }
                }
            });*/


            comment_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), Comment.class);

                    intent.putExtra("snscode",snscode.getText());

                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
