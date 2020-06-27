package com.example.mylogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_name, et_pass, et_pass2, et_subname, et_email, et_num;
    private Button btn_register;
    private RadioGroup et_sex;
    private Spinner em_spinner;
    private int userSex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_email = findViewById(R.id.et_email);
        et_pass = findViewById(R.id.et_pass);
        et_pass2 = findViewById(R.id.et_pass2);
        et_name = findViewById(R.id.et_id);
        et_subname = findViewById(R.id.et_subname);
        et_num = findViewById(R.id.et_num);
        et_sex = (RadioGroup)findViewById(R.id.et_sex);
        em_spinner = (Spinner)findViewById(R.id.email_spinner);

        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = et_sex.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) findViewById(id);

                final String userEmail = et_email.getText().toString() + "@" + em_spinner.getSelectedItem().toString();
                final String userPass = et_pass.getText().toString();
                final String userPass2 = et_pass2.getText().toString();
                final String userName = et_name.getText().toString();
                final String userSubname = et_subname.getText().toString();
                final int userNum = Integer.parseInt(et_num.getText().toString());
                final String Sex = rb.getText().toString();

                if (Sex.equals("남 성")) {userSex = 1;}
                if (Sex.equals("여 성")) {userSex = 2;}

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
                RegisterRequest registerRequest = new RegisterRequest(userEmail, userPass, userName, userSubname, userNum, userSex, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }
}
