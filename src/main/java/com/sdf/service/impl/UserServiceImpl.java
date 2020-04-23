package com.sdf.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdf.dao.UserDao;
import com.sdf.dao.impl.UserDaoImpl;
import com.sdf.domain.Order;
import com.sdf.domain.Scanner;
import com.sdf.domain.User;
import com.sdf.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao dao = new UserDaoImpl();

    @Override
    public Scanner ScannerLogin(Scanner scanner) {

        return dao.findForLogin(scanner);
    }

    @Override
    public boolean realAddressUpdate(String real_time_address, Long order_id) {
        return dao.realAddressUpdate(real_time_address, order_id);
    }

    @Override
    public boolean ExpressSignIn(Long order_id, String real_time_address) {
        //1、修改快建表数据
        if (!dao.signStatusChange(order_id, real_time_address)) //修改失败
            return false;
        //2、查询用户电话
        Order order = dao.findPhoneByOrderid(order_id);

        if (order == null)
            return false;

        String sender_phone = order.getSender_phone();

        //3、向历史订单表插入数据
        if(!dao.insertIntoHistory(sender_phone, order_id))
            return false;
        return true;
    }

    @Override
    public Order findOrderById(String order_id) {

        return dao.findOrderById(order_id);
    }

    @Override
    public boolean UserRegist(User user) {
        return dao.UserRegist(user);
    }

    @Override
    public User userLogin(User user) {

        return dao.userLogin(user);
    }

    /**
     *
     * @param uid
     * @return 订单集合的json数据
     */
    @Override
    public List<Order> findHistoricalByUid(String uid) {

        return dao.findHistoricalByUid(uid);
    }
}
