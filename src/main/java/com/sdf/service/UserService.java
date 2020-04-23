package com.sdf.service;

import com.sdf.dao.UserDao;
import com.sdf.dao.impl.UserDaoImpl;
import com.sdf.domain.Order;
import com.sdf.domain.Scanner;
import com.sdf.domain.User;

import java.util.List;

public interface UserService {

    UserDao dao = new UserDaoImpl();

    /**
     * 扫描员登陆业务
     * @param scanner
     * @return 扫描员对象的json数据
     */
    Scanner ScannerLogin(Scanner scanner);

    /**
     * 扫描员更新实时地址业务
     * @param real_time_address
     * @param order_id
     * @return
     */
    boolean realAddressUpdate(String real_time_address, String order_id);

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
     * 根据订单号查询订单，订单数据短时间不易改变，用户每次打开软件都可能查询订单，每次都访问关系型数据库
     * 会影响用户体验，因此使用redis中间件做缓冲。
     * @return 订单对象的json数据
     * @param order_id
     */
    Order findOrderById(String order_id);

    /**
     * 用户注册
     * @param user
     * @return
     */
    boolean UserRegist(User user);

    /**
     * 用户登陆
     * @param user
     * @return 用户对象的json数据
     */
    User userLogin(User user);

    /**
     * 查询历史订单
     * @param uid
     * @return
     */
    List<Order> findHistoricalByUid(String uid);

    /**
     * 寄件功能
     * @param uid
     * @return
     */
    boolean mailingByUid(Order uid);

    /**
     * 查询用户个人信息
     * @param uid
     * @return
     */
    User findUserByUid(String uid);

    /**
     * 修改用户个人信息
     * @param user
     * @return
     */
    boolean changeUserInfo(User user);

    /**
     * 修改用户身份证号码
     * @param uid
     * @param identify
     * @return
     */
    boolean changeIdentify(String uid, String identify);

    /**
     * 根据用户的uid查询身份证号码
     * @param uid
     * @return
     */
    String findIdentifyByUid(String uid);
}
