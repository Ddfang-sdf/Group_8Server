package com.sdf.dao;

import com.sdf.domain.Order;
import com.sdf.domain.Scanner;
import com.sdf.domain.User;

import java.util.List;

public interface UserDao {
    /**
     * 查询是否存在该扫描员用户
     * @param scanner
     * @return
     */
    Scanner findForLogin(Scanner scanner);

    /**
     * 根据订单号更改order表的real_time_address的值
     *
     * @param real_time_address
     * @param order_id
     * @return
     */
    boolean realAddressUpdate(String real_time_address, Long order_id);

    /**
     * 修改表order的签收状态，签收日期，实时地址
     * @param order_id
     * @param real_time_address
     * @return
     */
    boolean signStatusChange(Long order_id, String real_time_address);

    Order findPhoneByOrderid(Long order_id);

    boolean insertIntoHistory(String user_phone, Long order_id);

    Order findOrderById(String order_id);

    boolean UserRegist(User user);

    User userLogin(User _user);

    List<Order> findHistoricalByUid(String uid);

    boolean mailingByUid(Order _order);

    User findUserByUid(String uid);

    boolean changeUserInfo(User user);

    boolean changeIdentify(String uid, String identify);

    String findIdentifyByUid(String uid);
}
