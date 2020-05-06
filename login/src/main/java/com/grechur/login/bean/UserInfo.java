package com.grechur.login.bean;

import androidx.lifecycle.LiveData;

/**
 * @ProjectName: ToolsDemo
 * @ClassName: UserInfo
 * @Description: 用户信息
 * @Author: Grechur
 * @CreateDate: 2020/5/6 17:32
 */
public class UserInfo extends LiveData<UserInfo> {
    private boolean admin;
    private String email;
    private String icon;
    private long id;
    private String nickname;
    private String password;
    private String publicName;
    private String token;
    private String username;
    private int type;

    public UserInfo(boolean admin, String email, String icon, long id, String nickname, String password, String publicName, String token, String username, int type) {
        this.admin = admin;
        this.email = email;
        this.icon = icon;
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.publicName = publicName;
        this.token = token;
        this.username = username;
        this.type = type;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
        postValue(this);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        postValue(this);
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
        postValue(this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
        postValue(this);
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
        postValue(this);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        postValue(this);
    }

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
        postValue(this);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        postValue(this);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        postValue(this);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
        postValue(this);
    }
}
