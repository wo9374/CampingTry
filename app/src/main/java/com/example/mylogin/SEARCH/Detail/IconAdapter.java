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

public class IconAdapter extends RecyclerView.Adapter<IconViewHolder> {
    private ArrayList<IconItem> IconItems;

    public void setData(ArrayList<IconItem> list){
        IconItems = list;
    }

    @NonNull
    @Override
    public IconViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 사용할 아이템의 뷰를 생성해준다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_icon_item, parent, false);

        IconViewHolder holder = new IconViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull IconViewHolder holder, int position) {
        IconItem data = IconItems.get(position);

        holder.icon_img.setImageResource(data.getIcon_img());
        holder.icon_txt.setText(data.getIcon_txt());
    }

    @Override
    public int getItemCount() {
        return IconItems.size();
    }

}

class IconViewHolder extends RecyclerView.ViewHolder{
    public ImageView icon_img;
    public TextView icon_txt;

    public IconViewHolder(View itemView){
        super(itemView);

        icon_img = (ImageView) itemView.findViewById(R.id.icon_img);
        icon_txt = (TextView) itemView.findViewById(R.id.icon_txt);
    }
}
