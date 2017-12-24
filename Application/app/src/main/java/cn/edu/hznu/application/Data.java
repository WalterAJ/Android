package cn.edu.hznu.application;

import android.app.Application;

/**
 * Created by 20150815 on 2017/12/20.
 */

public class Data  {
    private static String username;
    private static String password;
    private static String imgUrl = null;
    private static String nickname;

    public static String getNickname() {
        return nickname;
    }

    public static void setNickname(String nickname) {
        Data.nickname = nickname;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Data.password = password;
    }

    public static String getImgUrl() {
        return imgUrl;
    }

    public static void setImgUrl(String imgUrl) {
        Data.imgUrl = imgUrl;
    }

    public static  String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Data.username = username;
    }
}
