package cn.edu.hznu.application;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static android.app.Activity.RESULT_CANCELED;


public class Fragment2 extends Fragment
{

    private static final int CODE_GALLERY_REQUEST = 1;
    private ImageView addPic;
    private EditText title,content;
    private TextView put;
    private String titleText,contentText;
    private String imageUrl = null;
    private Post post;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.release, container, false);
        addPic = (ImageView) view.findViewById(R.id.addPic_btn);
        put = (TextView) view.findViewById(R.id.put_release);
        title = (EditText)view.findViewById(R.id.release_title);
        content = (EditText) view.findViewById(R.id.release_content);
        put.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titleText = title.getText().toString();
                contentText = content.getText().toString();

                post = new Post(Data.getImgUrl(),Data.getUsername(),Data.getNickname(),titleText,contentText,"",imageUrl);
                post.save();
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        addPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choseHeadImageFromGallery();
            }
        });

        return view;

    }
    //选择相册内图片
    private void choseHeadImageFromGallery() {
        Intent intentFromGallery = new Intent();
        // 设置文件类型
        intentFromGallery.setType("image/*");
        intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent intent) {

        // 用户没有进行有效的设置操作，返回
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(getContext(), "取消", Toast.LENGTH_LONG).show();
            return;
        }

        switch (requestCode) {
            case CODE_GALLERY_REQUEST:
                Log.d("login",intent.getData().toString());
                handleImageOkKitKat(intent);
                break;


        }

        super.onActivityResult(requestCode, resultCode, intent);
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void handleImageOkKitKat(Intent data) {

        String imagePath=null;
        Uri uri = data.getData();
        Log.d("intent.getData :",""+uri);
        if (DocumentsContract.isDocumentUri(getContext(),uri)){
            String docId = DocumentsContract.getDocumentId(uri);
            // Log.d("getDocumentId(uri) :",""+docId);
            // Log.d("uri.getAuthority() :",""+uri.getAuthority());
            if ("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }
            else if ("com.android,providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                imagePath = getImagePath(contentUri,null);
            }

        }
        else if ("content".equalsIgnoreCase(uri.getScheme())){
            imagePath = getImagePath(uri,null);
        }
        Log.d("intent.getData :",imagePath);
        displayImage(imagePath);

    }
    public String getImagePath(Uri uri,String selection) {
        String path = null;
        Cursor cursor = getContext().getContentResolver().query(uri,null,selection,null,null);   //内容提供器
        if (cursor!=null){
            if (cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));   //获取路径
            }
        }
        cursor.close();
        return path;
    }
    public void displayImage(String imagePath){
        imageUrl = imagePath;
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        addPic.setImageBitmap(bitmap);


    }


}