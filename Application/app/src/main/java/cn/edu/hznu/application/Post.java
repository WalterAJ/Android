package cn.edu.hznu.application;

import org.litepal.crud.DataSupport;
import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 20150815 on 2017/12/2.
 */

public class Post extends DataSupport implements Serializable  {
    private String userimgUrl;
    private String user_name;
    private String nickname;
    private String title;
    private String content;
    private String time;
    private String img;


    public Post(String userimgUrl, String user_name,String nickname, String title, String content, String time, String img) {
        this.userimgUrl = userimgUrl;
        this.user_name = user_name;
        this.nickname = nickname;
        this.title = title;
        this.content = content;
        this.time = time;
        this.img = img;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUserimgUrl() {
        return userimgUrl;
    }

    public void setUserimgUrl(String userimgUrl) {
        this.userimgUrl = userimgUrl;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



}
