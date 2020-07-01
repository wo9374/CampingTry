package com.example.mylogin.Shop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylogin.R;

import java.util.ArrayList;

public class DetailShopAdapter extends  RecyclerView.Adapter<DetailShopAdapter.ImageViewHolder>{
    private ArrayList<DetailShopImageItem> ImageItems;

    public void setData(ArrayList<DetailShopImageItem> list){
        ImageItems = list;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_shop_detail, parent, false);

        ImageViewHolder holder = new ImageViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        DetailShopImageItem data = ImageItems.get(position);

        holder.image_img.setImageBitmap(data.getImage_img());
    }

    @Override
    public int getItemCount() {
        return ImageItems.size();
    }

    class  ImageViewHolder extends RecyclerView.ViewHolder{
        ImageView image_img;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            image_img = (ImageView) itemView.findViewById(R.id.image_img);
            image_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }
    }
}
