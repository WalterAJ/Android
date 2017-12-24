package cn.edu.hznu.application;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyPost extends AppCompatActivity {
    private List<Post> postList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_post);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null)
            actionBar.hide();
        PostAdapter adapter = new PostAdapter(this,R.layout.post,postList);
        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        getPost();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Post post = postList.get(position);
                Intent intent = new Intent(MyPost.this,PostDetailsActivity.class);
                intent.putExtra("post_data", (Serializable) post);
                startActivity(intent);
            }
        });
    }
    public  void getPost() {
        List<Post> posts = DataSupport.where("user_name = ?",Data.getUsername()).find(Post.class);
        for (Post post : posts) {

            postList.add(post);
        }
    }
}
