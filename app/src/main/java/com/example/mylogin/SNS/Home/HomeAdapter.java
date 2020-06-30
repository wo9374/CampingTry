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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.mylogin.R;
import com.example.mylogin.SEARCH.Detail.DetailInformation;
import com.example.mylogin.SEARCH.Frag2;
import com.example.mylogin.SNS.Frag1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        holder.title.setText(item.getTitle());
        holder.campcode.setText(item.getCampcode());
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
        TextView campcode;

        TextView title;

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
            campcode = itemView.findViewById(R.id.campcode);

            title = itemView.findViewById(R.id.title);

            like_btn = itemView.findViewById(R.id.like_btn);
            comment_btn = itemView.findViewById(R.id.comment_btn);
            chat_btn = itemView.findViewById(R.id.chat_btn);

            System.out.println(snscode.getText() + " sns코드@@@@@@@ㄹㄹㄹㄹㄹ@@@@@@");
            System.out.println(Frag1.likelist.get(0)+ "@@@@@@@@@@@KKKKKK@@@@@@@@@@@@@");
            for( int i =0; i<Frag1.likelist.size(); i++){
                if(snscode.getText().equals(Frag1.likelist.get(i))){
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
            });


            comment_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), Comment.class);

                    intent.putExtra("snscode",snscode.getText());
                    intent.putExtra("nic",Home.nic);
                    intent.putExtra("userid", Home.userid);

                    v.getContext().startActivity(intent);
                }
            });

            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    Intent intent = new Intent(v.getContext(), DetailInformation.class);

                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                boolean success = jsonObject.getBoolean("success");
                                if (success)//로그인 성공
                                {
                                    String imgurl = jsonObject.getString("imgurl");
                                    String addr = jsonObject.getString("addr");
                                    String name = jsonObject.getString("name");

                                    Intent intent = new Intent(v.getContext(), DetailInformation.class);
                                    intent.putExtra("url", imgurl);
                                    intent.putExtra("userid", Home.userid);
                                    intent.putExtra("addr", addr);
                                    intent.putExtra("name", name);
                                    intent.putExtra("code", campcode.getText().toString());

                                    v.getContext().startActivity(intent);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    //실제 서버로 Volley를 이용해서 요청을 함.
                    GoDetailRequest goDetailRequest = new GoDetailRequest(Integer.parseInt(campcode.getText().toString()), responseListener);
                    RequestQueue queue = Volley.newRequestQueue(v.getContext());
                    queue.add(goDetailRequest);
                }
            });
        }
    }
}
