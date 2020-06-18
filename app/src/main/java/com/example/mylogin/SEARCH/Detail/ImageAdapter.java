package com.example.mylogin.SEARCH.Detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mylogin.R;
import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageViewHolder>{
    private ArrayList<ImageItem> ImageItems;

    public void setData(ArrayList<ImageItem> list){
        ImageItems = list;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 사용할 아이템의 뷰를 생성해준다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_image_item, parent, false);

        ImageViewHolder holder = new ImageViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        ImageItem data = ImageItems.get(position);

        holder.image_img.setImageBitmap(data.getImage_img());
    }

    @Override
    public int getItemCount() {
        return ImageItems.size();
    }
}

class ImageViewHolder extends RecyclerView.ViewHolder{
    public ImageView image_img;

    public ImageViewHolder(View itemView){
        super(itemView);

        image_img = (ImageView) itemView.findViewById(R.id.image_img);
        image_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}