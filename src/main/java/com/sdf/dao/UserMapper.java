package com.sdf.dao;

import com.sdf.domain.HistoricalOrder;
import com.sdf.domain.Order;
import com.sdf.domain.Scanner;
import com.sdf.domain.User;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
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



}
