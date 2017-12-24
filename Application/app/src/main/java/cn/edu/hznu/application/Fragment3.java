package cn.edu.hznu.application;

import android.annotation.TargetApi;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import static android.app.Activity.RESULT_CANCELED;

public class Fragment3 extends Fragment
{
    private static final int CODE_GALLERY_REQUEST = 1;
    private TextView nickname;
    private RelativeLayout upd_password;
    private RelativeLayout my_post;
    private XCRoundImageView img;
    private Button logout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.my, container, false);

        img = (XCRoundImageView)view.findViewById(R.id.roundImageView);
        nickname = (TextView)view.findViewById(R.id.nickname);
        upd_password = (RelativeLayout)view.findViewById(R.id.upd_password);
        my_post = (RelativeLayout)view.findViewById(R.id.my_post);
        upd_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UpdPassword.class);
                startActivity(intent);
            }
        });
        my_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyPost.class);
                startActivity(intent);
            }
        });
        logout = (Button)view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), login.class);
                startActivity(intent);
            }
        });
        initAvatar();

        img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                choseHeadImageFromGallery();
            }
        });
        return view;
    }
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

           /* case CODE_CAMERA_REQUEST:
                if (hasSdcard()) {
                    File tempFile = new File(
                            Environment.getExternalStorageDirectory(),
                            IMAGE_FILE_NAME);
                    cropRawPhoto(Uri.fromFile(tempFile));
                } else {
                    Toast.makeText(getContext(), "没有SDCard!", Toast.LENGTH_LONG)
                            .show();
                }

                break;

            case CODE_RESULT_REQUEST:
                if (intent != null) {
                    //setImageToHeadView(intent);
                }

                break;*/
        }

        super.onActivityResult(requestCode, resultCode, intent);
    }
   public void initAvatar(){
       if(!(Data.getImgUrl() == null)){
           Bitmap bitmap = BitmapFactory.decodeFile(Data.getImgUrl());
           Log.d("img",Data.getImgUrl());
           img.setImageBitmap(bitmap);
       }

       nickname.setText(Data.getNickname());

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
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        img.setImageBitmap(bitmap);
        changeAvatar(imagePath);
        Data.setImgUrl(imagePath);
        //Log.d("img111",Data.getImgUrl());

    }
    public void changeAvatar(String imagePath){
        User user = new User();
        user.setImgUrl(imagePath);
        user.updateAll("user_name = ?",Data.getUsername());
    }


}