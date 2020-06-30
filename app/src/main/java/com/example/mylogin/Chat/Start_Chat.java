package com.example.mylogin.Chat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.mylogin.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Start_Chat extends Fragment {
    private FragmentActivity mContext;

    private RecyclerView mRecyclerView;
    public  RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ChatData> chatList;
    private String nick;

    private String uid;
    private String chatRoomUid;
    private String destinatonUid;

    private EditText EditText_chat;
    private Button Button_send;
    private DatabaseReference myRef;

    private View view;

    @Override
    public void onAttach(@NonNull Activity activity) {
        mContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_chat_room, container, false);

        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();  //채팅을 요구 하는 아아디 즉 단말기에 로그인된 UID
        //destinatonUid = getIntent().getStringExtra("destinationUid"); // 채팅을 당하는 아이디

        Button_send = view.findViewById(R.id.Button_send);
        EditText_chat = view.findViewById(R.id.EditText_chat);

        if (getArguments() != null) {
            nick = getArguments().getString("nic");
        }

        Button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = EditText_chat.getText().toString();

                if(msg != null) {
                    ChatData chat = new ChatData();
                    chat.setNickname(nick);
                    chat.setMsg(msg);
                    EditText_chat.setText("");
                    myRef.push().setValue(chat);    //push로 묶어준다
                }
            }
        });

        mRecyclerView = view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);

        chatList = new ArrayList<>();
        mAdapter = new ChatAdapter(chatList, Start_Chat.this, nick);

        mRecyclerView.setAdapter(mAdapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ChatData chat = snapshot.getValue(ChatData.class);
                ((ChatAdapter) mAdapter).addChat(chat); //리스너 안에서는 인식이 안되니까 괄호 두번
                Log.d("Test", snapshot.getValue().toString());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}