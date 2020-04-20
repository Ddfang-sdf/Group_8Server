package com.sdf.domain;

public class User {
    private int uid;
    private String username;
    private String passwd;
    private String address;
    private String gender;
    private int age;
    private String identify;
    private String user_phone;

    public User() {
    }

    public User(int uid, String username, String passwd, String address, String gender, int age, String identify, String user_phone) {
        this.uid = uid;
        this.username = username;
        this.passwd = passwd;
        this.address = address;
        this.gender = gender;
        this.age = age;
        this.identify = identify;
        this.user_phone = user_phone;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
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
