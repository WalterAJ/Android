package cn.edu.hznu.application;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

public class login extends AppCompatActivity {
    private EditText user_name;
    private EditText password;
    private Button login_btn;
    private TextView signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user_name = (EditText)findViewById(R.id.user_name);
        password = (EditText)findViewById(R.id.password);
        login_btn = (Button)findViewById(R.id.login);
        signup = (TextView)findViewById(R.id.sign_up);
        LitePal.getDatabase();
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<User> users = DataSupport.where("user_name = ?",user_name.getText().toString()).find(User.class);
                Log.d("login","1");
                if (!users.isEmpty())
                {
                    users = DataSupport.where("user_name = ? and password = ?",user_name.getText().toString(),password.getText().toString()).find(User.class);
                    if (!users.isEmpty()) {
                        Data.setUsername(users.get(0).getUser_name());
                        Data.setImgUrl(users.get(0).getImgUrl());
                        Data.setPassword(users.get(0).getPassword());
                        Data.setNickname(users.get(0).getNickname());
                        //Log.d("img111",Data.getImgUrl());
                        Intent intent = new Intent(login.this, MainActivity.class);
                        startActivity(intent);


                    }
                    else
                        {
                            Toast.makeText(login.this, "密码错误！", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(login.this, "账号不存在！", Toast.LENGTH_SHORT).show();
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this,signUp.class);
                startActivity(intent);
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null)
            actionBar.hide();
       ;
    }
}
