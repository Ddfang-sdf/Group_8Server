package com.sdf.domain;

import java.io.Serializable;

public class User implements Serializable {
    private String uid;//用户id
    private String username;//用户名
    private String passwd;//用户密码
    private String address;//用户收货地址
    private String gender;//用户性别
    private String age;//用户年龄
    private String identify;//用户身份证
    private String user_phone;//用户手机

    public User() {
    }

    public User(String uid, String username, String passwd, String address, String gender, String age, String identify, String user_phone) {
        this.uid = uid;
        this.username = username;
        this.passwd = passwd;
        this.address = address;
        this.gender = gender;
        this.age = age;
        this.identify = identify;
        this.user_phone = user_phone;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", passwd='" + passwd + '\'' +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", identify='" + identify + '\'' +
                ", user_phone='" + user_phone + '\'' +
                '}';
    }
}
