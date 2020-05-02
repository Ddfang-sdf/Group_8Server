package com.sdf.service;

import com.sdf.dao.UserMapper;
import com.sdf.domain.HistoricalOrder;
import com.sdf.domain.Order;
import com.sdf.domain.User;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserMapperService {

    private InputStream config;
    private SqlSessionFactory factory;
    private SqlSession session;
    private UserMapper userMapper;

    private void init() {
        try {
            config = Resources.getResourceAsStream("mybatis-config.xml");
            factory = new SqlSessionFactoryBuilder().build(config);
            session = factory.openSession();
            userMapper = session.getMapper(UserMapper.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        try {
            session.commit();
        } catch (Exception e) {
            session.rollback();
        } finally {
            if (config != null) {
                try {
                    config.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * 用户登陆
     *
     * @param _user
     * @return
     */
    public User testUserLogin(User _user) {

        init();
        try {

            return userMapper.userLogin(_user);

        } finally {

            destroy();

        }
    }

    /**
     * 用户注册
     */
    public Boolean userRegist(User user) {
        init();
        try {
            return userMapper.userRegist(user);
        } finally {
            destroy();
        }
    }

    /**
     * 查询订单
     *
     * @param order_id
     * @return
     */
    public Order findOrderById(String order_id) {
        init();
        try {
            return userMapper.findOrderById(order_id);
        } finally {

            destroy();
        }
    }

    /**
     * 查询历史订单
     * @param uid
     * @return
     */
    public List<Order> findHistoricalByUid(Integer uid) {
        init();
        List<HistoricalOrder> historicalOrders = null;

        try {
            historicalOrders = userMapper.findHistoricalByUid(uid);
            if (historicalOrders == null || historicalOrders.size() == 0)
                return null;
            else
                return userMapper.findOrdersByList(historicalOrders);
        } finally {
            destroy();
        }
    }


}
