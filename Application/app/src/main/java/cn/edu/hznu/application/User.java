package cn.edu.hznu.application;

import org.litepal.crud.DataSupport;

/**
 * Created by 20150815 on 2017/12/2.
 */

public class User extends DataSupport{


    private String user_name;
    private String password;
    private String imgUrl;
    private String nickname;



    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    public String getUser_name(){return user_name;}
    public String  getPassword(){return password;}
    public String getImgUrl() {
        return imgUrl;
    }
    public String getNickname() {
        return nickname;
    }




}
