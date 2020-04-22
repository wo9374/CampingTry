package com.example.mylogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_id, et_pass, et_pass2, et_name, et_subname, et_birth, et_num;
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        et_pass2 = findViewById(R.id.et_pass2);
        et_name = findViewById(R.id.et_name);
        et_subname = findViewById(R.id.et_subname);
        et_birth = findViewById(R.id.et_birth);
        et_num = findViewById(R.id.et_num);

        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userID = et_id.getText().toString();
                final String userPass = et_pass.getText().toString();
                final String userPass2 = et_pass2.getText().toString();
                final String userName = et_name.getText().toString();
                final String userSubname = et_subname.getText().toString();
                final int userBirth = Integer.parseInt(et_birth.getText().toString());
                final int userNum = Integer.parseInt(et_num.getText().toString());

                final Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (userPass.equals(userPass2))
                            {
                                if (success)//회원등록 성공
                                {
                                    Toast.makeText(getApplicationContext(),"회원 등록에 성공하셨습니다.",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                } else { //회원등록 실패
                                    Toast.makeText(getApplicationContext(),"회원 등록에 실패하셨습니다.",Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                            else {Toast.makeText(getApplicationContext(),"비밀번호가 동일하지 않습니다.",Toast.LENGTH_SHORT).show();}
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                //실제 서버로 Volley를 이용해서 요청을 함.
                RegisterRequest registerRequest = new RegisterRequest(userID, userPass, userName, userSubname, userBirth, userNum, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }
}
