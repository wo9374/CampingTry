package com.example.mylogin.SearchUser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.mylogin.LoginActivity;
import com.example.mylogin.R;

import org.json.JSONException;
import org.json.JSONObject;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class FragSearchID  extends Fragment {
    private View view;
    private FragmentActivity mContext;

    private EditText Myname, Mynum;
    private Button Search_btn;

    public static FragSearchID newInstance()
    {
        FragSearchID fragSearchID = new FragSearchID();
        return fragSearchID;
    }

    @Override
    public void onAttach(@NonNull Activity context) {
        mContext = (FragmentActivity) context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_searchid, container, false);

        Myname = view.findViewById(R.id.sid_name);
        Mynum = view.findViewById(R.id.sid_num);
        Search_btn = view.findViewById(R.id.Searchid_btn);

        Search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userName = Myname.getText().toString();
                int userNum = Integer.parseInt(Mynum.getText().toString());

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success)//로그인 성공
                            {
                                String userID = jsonObject.getString("userID");
                                Intent intent = new Intent(mContext, LoginActivity.class);
                                intent.putExtra("userID", userID);  //로그인한 정보를 넘겨줌
                                startActivity(intent);
                            } else { //로그인 실패
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                FragSIDRequest fragSIDRequest = new FragSIDRequest(userName, userNum, responseListener);
                RequestQueue queue = Volley.newRequestQueue(mContext);
                queue.add(fragSIDRequest);
            }
        });
        return view;
    }
}