package cn.edu.hznu.application;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PostDetailsActivity extends AppCompatActivity {

    private XCRoundImageView postAvatar;
    private RelativeLayout back_btn;
    private ImageView post_detailImg;
    private TextView postUser_name;
    private TextView post_detailTitle,post_detailcontent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);
        ActionBar actionBar = getSupportActionBar();
        Post post =(Post)getIntent().getSerializableExtra("post_data");
        postAvatar = (XCRoundImageView)findViewById(R.id.postAvatar);
        post_detailImg = (ImageView)findViewById(R.id.post_detailImg);
        postUser_name = (TextView)findViewById(R.id.postUser_name);
        post_detailTitle = (TextView)findViewById(R.id.post_detailTitle);
        post_detailcontent = (TextView)findViewById(R.id.post_detailcontent);
        back_btn = (RelativeLayout)findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostDetailsActivity.this.finish();
            }
        });
        if(!(post.getUserimgUrl() == null)) {
            Bitmap bitmap = BitmapFactory.decodeFile(post.getUserimgUrl());
            postAvatar.setImageBitmap(bitmap);
        }
        if(!(post.getImg() == null)){
            Bitmap bitmap = BitmapFactory.decodeFile(post.getImg());
            post_detailImg.setImageBitmap(bitmap);
        }
        postUser_name.setText(post.getNickname());
        post_detailTitle.setText(post.getTitle());
        post_detailcontent.setText(post.getContent());


        if(actionBar!=null)
            actionBar.hide();



    }
}
