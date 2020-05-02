package com.sdf.domain;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private int uid;//用户id
    private String sender_address;//寄件人地址
    private String sender_name;//寄件人姓名
    private String sender_phone;//寄件人电话
    private String receiver_address;//收件人地址
    private String receiver_name;//收件人姓名
    private String receiver_phone;//收件人电话
    private long order_id;//订单号
    private String sign_for;//签收状态
    private Date sign_date;//签收日期
    private int weight;//包裹重量
    private String type;//包裹类型
    private String real_time_address;//快件实时地址

    public Order() {
    }

    public Order(int uid, String sender_address, String sender_name, String sender_phone, String receiver_address, String receiver_name, String receiver_phone, long order_id, String sign_for, Date sign_date, int weight, String type, String real_time_address) {
        this.uid = uid;
        this.sender_address = sender_address;
        this.sender_name = sender_name;
        this.sender_phone = sender_phone;
        this.receiver_address = receiver_address;
        this.receiver_name = receiver_name;
        this.receiver_phone = receiver_phone;
        this.order_id = order_id;
        this.sign_for = sign_for;
        this.sign_date = sign_date;
        this.weight = weight;
        this.type = type;
        this.real_time_address = real_time_address;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getSender_address() {
        return sender_address;
    }

    public void setSender_address(String sender_address) {
        this.sender_address = sender_address;
    }

    public String getSender_name() {
        return sender_name;
    }

    public void setSender_name(String sender_name) {
        this.sender_name = sender_name;
    }

    public String getSender_phone() {
        return sender_phone;
    }

    public void setSender_phone(String sender_phone) {
        this.sender_phone = sender_phone;
    }

    public String getReceiver_address() {
        return receiver_address;
    }

    public void setReceiver_address(String receiver_address) {
        this.receiver_address = receiver_address;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public String getReceiver_phone() {
        return receiver_phone;
    }

    public void setReceiver_phone(String receiver_phone) {
        this.receiver_phone = receiver_phone;
    }

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public String getSign_for() {
        return sign_for;
    }

    public void setSign_for(String sign_for) {
        this.sign_for = sign_for;
    }

    public Date getSign_date() {
        return sign_date;
    }

    public void setSign_date(Date sign_date) {
        this.sign_date = sign_date;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReal_time_address() {
        return real_time_address;
    }

    public void setReal_time_address(String real_time_address) {
        this.real_time_address = real_time_address;
    }

    @Override
    public String toString() {
        return "Order{" +
                "uid=" + uid +
                ", sender_address='" + sender_address + '\'' +
                ", sender_name='" + sender_name + '\'' +
                ", sender_phone='" + sender_phone + '\'' +
                ", receiver_address='" + receiver_address + '\'' +
                ", receiver_name='" + receiver_name + '\'' +
                ", receiver_phone='" + receiver_phone + '\'' +
                ", order_id=" + order_id +
                ", sign_for='" + sign_for + '\'' +
                ", sign_date=" + sign_date +
                ", weight=" + weight +
                ", type='" + type + '\'' +
                ", real_time_address='" + real_time_address + '\'' +
                '}';
    }
}
