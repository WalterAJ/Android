package cn.edu.hznu.application;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import org.litepal.crud.DataSupport;

import java.util.List;

public class signUp extends AppCompatActivity {
    private Button signup;
    private EditText user_name;
    private EditText password;
    private EditText password_again;
    private EditText nickname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        user_name = (EditText)findViewById(R.id.Sign_user_name);
        password = (EditText)findViewById(R.id.Sign_password);
        password_again = (EditText)findViewById(R.id.Sign_password_again);
        nickname = (EditText)findViewById(R.id.Sign_nickname) ;
        signup = (Button)findViewById(R.id.sign_up);



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("login",user_name.getText().toString());
                List<User> users = DataSupport.findAll(User.class);
                for(User user:users){
                    Log.d("login",user.getUser_name());

                }
                users = DataSupport.where("user_name = ? ",user_name.getText().toString()).find(User.class);

                if (!users.isEmpty()){
                    Toast.makeText(signUp.this, "您注册的账号已存在！", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(password.getText().toString().equals(password_again.getText().toString())) {
                        User user = new User();
                        user.setUser_name(user_name.getText().toString());
                        user.setPassword(password.getText().toString());
                        user.setNickname(nickname.getText().toString());
                        user.save();

                        Intent intent = new Intent(signUp.this, login.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(signUp.this, "两次密码输入不一致！", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null)
            actionBar.hide();
    }
}
