package com.example.mylogin.SNS;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylogin.R;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder>{

    private ArrayList<NotificationItem> mData = null;

    NotificationAdapter(ArrayList<NotificationItem> list){
        this.mData = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context =parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.holder_notification, parent, false);
        NotificationAdapter.ViewHolder vh = new NotificationAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NotificationItem item = mData.get(position);

        holder.nic.setText(item.getNic());
        holder.time.setText(item.getTime());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView nic;
        TextView time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nic = itemView.findViewById(R.id.nic);
            time = itemView.findViewById(R.id.time);
        }
    }
}
