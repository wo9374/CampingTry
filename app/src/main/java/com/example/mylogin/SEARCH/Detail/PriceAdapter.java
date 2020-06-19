package com.example.mylogin.SEARCH.Detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylogin.R;

import java.util.ArrayList;

public class PriceAdapter extends RecyclerView.Adapter<PriceViewHolder>{
    private ArrayList<PriceItem> PriceItems = null;

    public void setData(ArrayList<PriceItem> list){
        this.PriceItems = list;
    }

    @NonNull
    @Override
    public PriceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 사용할 아이템의 뷰를 생성해준다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_price_item, parent, false);

        PriceViewHolder holder = new PriceViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PriceViewHolder holder, int position) {
        PriceItem data = PriceItems.get(position);

        holder.zone.setText(data.getZone());
        holder.facility.setText(data.getFacility());
        holder.price.setText(data.getPrice());
    }

    @Override
    public int getItemCount() {
        return PriceItems.size();
    }
}

class PriceViewHolder extends RecyclerView.ViewHolder{
    TextView zone;
    TextView facility;
    TextView pr;
    TextView price;


    public PriceViewHolder(View itemView){
        super(itemView);

        zone = (TextView) itemView.findViewById(R.id.zone);
        facility = (TextView) itemView.findViewById(R.id.facility);
        pr = (TextView) itemView.findViewById(R.id.pr);
        price = (TextView) itemView.findViewById(R.id.price);
    }
}
