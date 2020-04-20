package com.sdf.domain;

public class Scanner {
    private int sid;//扫描员id
    private String s_username;//扫描员用户名
    private String s_passwd;//扫描员密码

    public Scanner() {
    }

    public Scanner(int sid, String s_username, String s_passwd) {
        this.sid = sid;
        this.s_username = s_username;
        this.s_passwd = s_passwd;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getS_username() {
        return s_username;
    }

    public void setS_username(String s_username) {
        this.s_username = s_username;
    }

    public String getS_passwd() {
        return s_passwd;
    }

    public void setS_passwd(String s_passwd) {
        this.s_passwd = s_passwd;
    }

    @Override
    public String toString() {
        return "Scanner{" +
                "sid=" + sid +
                ", s_username='" + s_username + '\'' +
                ", s_passwd='" + s_passwd + '\'' +
                '}';
    }
}
