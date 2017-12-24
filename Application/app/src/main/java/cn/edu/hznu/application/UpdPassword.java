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

public class UpdPassword extends AppCompatActivity {

    private EditText password;
    private EditText newpw,newpw_again;
    private Button upd_btn;
    private String passwordText,newpwText,newpw_againText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upd_password);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null)
            actionBar.hide();
        password = (EditText)findViewById(R.id.upd_pw);
        newpw = (EditText)findViewById(R.id.upd_newpw);
        newpw_again = (EditText)findViewById(R.id.upd_newpw_again);
        upd_btn = (Button)findViewById(R.id.upd_btn);
        upd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordText = password.getText().toString();
                newpwText = newpw.getText().toString();
                newpw_againText = newpw_again.getText().toString();
                User user = new User();
                Log.d("passwordText",Data.getPassword());
                Log.d("passwprd",Data.getPassword());
                if(passwordText.equals(Data.getPassword())) {
                    if (newpw_againText.equals(newpwText)) {
                        user.setPassword(newpwText);
                        user.updateAll("user_name = ?", Data.getUsername());
                        Toast.makeText(UpdPassword.this, "修改成功！", Toast.LENGTH_SHORT).show();
                        UpdPassword.this.finish();
                    } else {
                        Toast.makeText(UpdPassword.this, "两次密码输入不一致！", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(UpdPassword.this, "原密码输入错误！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
