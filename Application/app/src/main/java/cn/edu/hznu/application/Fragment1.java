package cn.edu.hznu.application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class Fragment1 extends Fragment
{
    private List<Post> postList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.list, container, false);
        LitePal.getDatabase();
        getPost();

        PostAdapter adapter = new PostAdapter(getActivity(),R.layout.post,postList);
        ListView listView = (ListView)view.findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Post post = postList.get(position);
                Intent intent = new Intent(getActivity(),PostDetailsActivity.class);
                intent.putExtra("post_data", (Serializable) post);
                startActivity(intent);
            }
        });
        return view;
    }

    public  void getPost(){
        List<Post> posts = DataSupport.findAll(Post.class);
        for(Post post: posts){

            postList.add(post);
        }
}



}