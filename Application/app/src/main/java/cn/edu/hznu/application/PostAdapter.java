package cn.edu.hznu.application;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;


/**
 * Created by 20150815 on 2017/12/6.
 */

public class PostAdapter extends ArrayAdapter<Post> {
    private int  resourceId;
    public PostAdapter(Context context, int textViewResourceId, List<Post> object){
        super(context,textViewResourceId,object);
        resourceId = textViewResourceId;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Post post =  getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
       // ImageView postUser_img = (ImageView)view.findViewById(R.id.postUser_img);
       // TextView postUser_name = (TextView) view.findViewById(R.id.postUser_name);
       // TextView postTime = (TextView) view.findViewById(R.id.postTime);
        TextView postTitle = (TextView) view.findViewById(R.id.postTitle);
        ImageView postImg = (ImageView) view.findViewById(R.id.postImg);
        //得到可用的图片
        //显示
        
        //  postUser_name.setText(post.getUser_name());
        //  postTime.setText(post.getTime());
        postTitle.setText(post.getTitle());
        Log.d("login",post.getImg()+"   1");
        Bitmap bitmap = BitmapFactory.decodeFile(post.getImg());
        postImg.setImageBitmap(bitmap);
        return view;
    }

}
