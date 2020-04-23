package com.sdf.service.impl;

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
    public boolean realAddressUpdate(String real_time_address, String order_id) {
        return dao.realAddressUpdate(real_time_address, order_id);
    }

    @Override
    public boolean ExpressSignIn(String order_id, String real_time_address) {
        //1、修改快建表数据
        if (!dao.signStatusChange(order_id, real_time_address)) //修改失败
            return false;
        //2、查询用户电话
        String order_phone = dao.findPhoneByOrderid(order_id);

        if (order_phone == null)
            return false;

        //3、向历史订单表插入数据
        if (!dao.insertIntoHistory(order_phone, order_id))
            return false;
        return true;
    }

    /**
     * 订单数据，根据订单号查询
     *
     * @param order_id
     * @return
     */
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
     * @param uid
     * @return 订单集合的json数据
     */
    @Override
    public List<Order> findHistoricalByUid(String uid) {

        return dao.findHistoricalByUid(uid);
    }

    @Override
    public boolean mailingByUid(Order order) {
        return dao.mailingByUid(order);
    }

    @Override
    public User findUserByUid(String uid) {


        return dao.findUserByUid(uid);
    }

    @Override
    public boolean changeUserInfo(User user) {
        return dao.changeUserInfo(user);
    }

    @Override
    public String findIdentifyByUid(String uid) {

        return dao.findIdentifyByUid(uid);
    }

    @Override
    public boolean changeIdentify(String uid, String identify) {
        return dao.changeIdentify(uid, identify);
    }
}
