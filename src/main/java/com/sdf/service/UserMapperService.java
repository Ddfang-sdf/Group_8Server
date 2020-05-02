package com.sdf.service;

import com.sdf.dao.UserMapper;
import com.sdf.domain.HistoricalOrder;
import com.sdf.domain.Order;
import com.sdf.domain.User;
import com.sdf.domain.exceptions.MyExcetion1;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLNonTransientException;
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
    public User userLogin(User _user) {

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
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            destroy() ;

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

    /**
     * 寄件
     */
    public boolean mailingByUid(Order order){
        init();
        boolean flag = false;
        try{
            flag = userMapper.mailingByUid(order);
        }catch (Exception e){
            session.rollback();
            e.printStackTrace();

        }finally {
            destroy();
//            throw new RuntimeException("寄件功能异常");
        }
        return flag;
    }

    public User saveUserInfo(User user){
        init();
        User userById = null;
        try {
            //更新user表
            if(!userMapper.changeUserInfo(user)){
                session.rollback();
                throw new MyExcetion1("更新用户表失败");
            }
            //更新order表
            if (!userMapper.changeOrderInfo(user)){
                session.rollback();
                throw new MyExcetion1("更新订单表失败");
            }

            //查询用户
             userById = userMapper.findUserById(user);


        }catch (Exception | MyExcetion1 e){

            session.rollback();
            e.printStackTrace();
            return userById;

        }finally {
            destroy();
        }


        return userById;
    }




}
