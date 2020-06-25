package com.example.mylogin.Chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylogin.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {
    private List<ChatData> mDataset;
    private String myNickName;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView nickname;
        public TextView msg;
        public View rootView;
        public MyViewHolder(View v){
            super(v);
            nickname = v.findViewById(R.id.T_nickname);
            msg = v.findViewById(R.id.T_msg);
            rootView = v;
        }
    }

    public ChatAdapter(List<ChatData> myDataset, Frag5 context, String myNickName){
        mDataset = myDataset;
        this.myNickName = myNickName;
    }


    @NonNull
    @Override
    public ChatAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.row_chat, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ChatData chat = mDataset.get(position);

        holder.nickname.setText(chat.getNickname());
        holder.msg.setText(chat.getMsg());

        if(chat.getNickname().equals(this.myNickName)) {    //본인이면 오른쪽정렬 아니면 왼쪽
            holder.msg.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            holder.nickname.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            holder.nickname.setTextColor(0xAAef484a);
        }
        else {
            holder.msg.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            holder.nickname.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            holder.nickname.setTextColor(0xAA6d36d8);
        }
    }

    @Override
    public int getItemCount() {
        return mDataset == null ? 0 :  mDataset.size();
    }

    public ChatData getChat(int position) {
        return mDataset != null ? mDataset.get(position) : null;
    }

    public void addChat(ChatData chat) {
        mDataset.add(chat);
        notifyItemInserted(mDataset.size()-1); //갱신
    }

}
