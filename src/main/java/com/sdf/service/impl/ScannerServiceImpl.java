package com.sdf.service.impl;

import com.sdf.dao.UserDao;
import com.sdf.dao.impl.UserDaoImpl;
import com.sdf.domain.Order;
import com.sdf.domain.Scanner;
import com.sdf.service.ScannerService;

public class ScannerServiceImpl implements ScannerService {
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
}
