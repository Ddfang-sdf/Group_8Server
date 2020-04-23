package com.sdf.service;

import com.sdf.dao.UserDao;
import com.sdf.dao.impl.UserDaoImpl;
import com.sdf.domain.Order;
import com.sdf.domain.Scanner;
import com.sdf.domain.User;

public interface UserService {

    UserDao dao = new UserDaoImpl();

    /**
     * 扫描员登陆业务
     * @param scanner
     * @return
     */
    Scanner ScannerLogin(Scanner scanner);

    /**
     * 扫描员更新实时地址业务
     * @param real_time_address
     * @param order_id
     * @return
     */
    boolean realAddressUpdate(String real_time_address, Long order_id);

    /**
     * 快件签收业务
     * 1、根据订单号，修改快件表的签收状态，签收日期，实时地址
     * 2、根据订单号，查询寄件人用户的手机号吗
     * 3、向历史订单表插入数据
     * @return
     * @param order_id
     * @param real_time_address
     */
    boolean ExpressSignIn(Long order_id, String real_time_address);

    /**
     * 根据订单号查询订单
     * @return
     * @param order_id
     */
    Order findOrderById(String order_id);

    /**
     * 用户注册
     * @param user
     * @return
     */
    boolean UserRegist(User user);
}
