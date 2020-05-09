package com.sdf.dao;

import com.sdf.domain.HistoricalOrder;
import com.sdf.domain.Order;

import com.sdf.domain.User;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface UserMapper {

    /**
     * 用户登陆
     * @param  user
     * @return
     */
    User userLogin(@Param("user") User user);

    /**
     * 用户注册校验
     * @param user
     * @return
     */
    boolean userRegist(@Param("user") User user);

    /**
     * 查询订单
     * @param order_id
     * @return
     */
    Order findOrderById(/*@Param("order_id")*/ String order_id);

    /**
     * 查询历史订单
     * @param uid
     * @return
     */
    List<HistoricalOrder> findHistoricalByUid(Integer uid);

    /**
     * 根据历史订单集合，查询历史订单详细数据
     * @param historicalOrders
     * @return
     */
    List<Order> findOrdersByList(@Param("hises")List<HistoricalOrder> historicalOrders);

    /**
     * 寄件
     * @param order
     * @return
     */
    boolean mailingByUid(@Param("order") Order order);

/*    *//**
     * 修改个人信息数据回显
     * @param uid
     * @return
     *//*
    User findUserByUid(String uid);*/

    /**
     * 修改个人信息
     * @param user
     * @return
     */
    Boolean changeUserInfo(@Param("user") User user);

    /**
     * 修改订单数据
     * @param user
     * @return
     */
    Boolean changeOrderInfo(@Param("user") User user);

    /**
     * 查询用户信息
     * @param user
     * @return
     */
    User findUserById(@Param("user") User user);




}
