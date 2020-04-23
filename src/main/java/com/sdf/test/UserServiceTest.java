package com.sdf.test;

import com.sdf.domain.Order;
import com.sdf.domain.ResultInfo;
import com.sdf.domain.Scanner;
import com.sdf.domain.User;
import com.sdf.service.UserService;
import com.sdf.service.impl.UserServiceImpl;
import com.sdf.utils.DruidUtils;
import com.sdf.utils.ServletUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


import javax.sql.rowset.JdbcRowSet;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试类，用于测试service层函数
 */
public class UserServiceTest {

    UserService service = new UserServiceImpl();

    /**
     * 业务层扫描员登录功能测试
     *
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Test
    public void testScannerLogin() throws InvocationTargetException, IllegalAccessException {
        Scanner scanner_cus = new Scanner();
        UserService scannerService = new UserServiceImpl();
        ResultInfo info = null;
        //servlet 从请求中获取用户名密码，封装为map
        Map<String, String> map = new HashMap<>();
        map.put("s_username", "赵校来");
        map.put("s_passwd", "123456");
        BeanUtils.populate(scanner_cus, map);
        String scanner_ser = scannerService.ScannerLogin(scanner_cus);
        if (scanner_ser != null) {
            info = ServletUtils.getInfo(true, scanner_ser, "");
        } else {
            info = ServletUtils.getInfo(false, scanner_ser, "用户名或密码不正确");
        }
        System.out.println(info);

    }

    /**
     * 测试扫描员修改实时地址
     * 扫描员修改订单实时地址，请求中发送订单号,本次订单号位372036854775808
     */
    @Test
    public void testReal_address_update() {
        //servlet中获取用户数据
        Long order_id = 372036854775808l;
        ResultInfo info = null;
        String real_time_address = "河南省郑州市郑州大学校区仁和宿舍3号楼811室";
        if (service.realAddressUpdate(real_time_address, order_id)) {
            //修改成功
            info = ServletUtils.getInfo(true, true, "");
        } else {
            //修改失败
            info = ServletUtils.getInfo(false, false, "修改失败，请稍后再试");
        }
        System.out.println(info);

    }

    /**
     * 测试快件签收业务
     * #扫描员确认订单签收，修改签收状态、实时地址和签收日期，并且将订单插入历史订单表中
     * #修改订单表信息
     * #查询用户手机号
     * #插入历史订单
     * JdbcTemplate属于springJdbc的一个类，因为没有深入学习过spring的知识，现在仅仅会增删改查，
     * 对于开启事务掌握的不好。就先不用了。由于一个线程上可以有多个连接对象，如果不使用jdbcTemplate，
     * 就要要开启事务必须要使用原生的jdbc，这样会导致多个dao层数据库基本操作冗余在一起。极易出错。这里
     * 就先不使用事务了。
     */
    @Test
    public void testOrder_sign() {
        //获取用户数据，
        //订单号
        Long order_id = 372036854775808l;
        // 签收状态
        String sign_for = "Y";
        //实时地址
        String real_time_address = "河南省郑州市郑州大学校区仁和宿舍3号楼811室";
        //调用业务层快件签收方法
        if (service.ExpressSignIn(order_id, real_time_address)) {
            //快件签收完成
            System.out.println("快件签收完成");
        } else {
            System.out.println("出错了！！！");
        }


    }

    /**
     * 用户注册功能测试
     * #用户注册 请求中包含---用户名，密码，地址，性别，年龄，身份证，手机
     * 表单数据如下：
     * NULL,'李莫愁','123456','河南省开封市金明校区华苑3号楼811室','女',17,'273747596018273645','13588239999'
     */
    @Test
    public void test() throws InvocationTargetException, IllegalAccessException {
        User _user = new User();
        Map<String, String> map = new HashMap<>();
        map.put("username", "李莫愁");
        map.put("passwd", "123456");
        map.put("address", "河南省开封市金明校区华苑3号楼811室");
        map.put("gender", "女");
        map.put("age", "17");
        map.put("identify", "273747596018273645");
        map.put("user_phone", "13588239999");
        //System.out.println(map);
        BeanUtils.populate(_user, map);
        boolean user = service.UserRegist(_user);
        System.out.println(user);

    }

    /**
     * #查询订单 请求中包含---订单号 372036854775807
     */
    @Test
    public void testFindOrderById() {
        String order_id = "372036854775807";
        String order = service.findOrderById(order_id);
        ResultInfo info = ServletUtils.getInfo(true, order, "");
        System.out.println(info);
    }

    /**
     * 用户登录功能：
     * #用户登陆 请求中包含---用户名 密码
     */
    @Test
    public void testUserLogin() throws InvocationTargetException, IllegalAccessException {
        User _user = new User();
        String username = "李莫愁";
        String passwd = "123456";
        Map<String, String> map = new HashMap<>();
        map.put("username", "李莫愁");
        map.put("passwd", "123456");
        BeanUtils.populate(_user, map);
        String user = service.userLogin(_user);
        ResultInfo info = ServletUtils.getInfo(true, user, "");
        System.out.println(info);
    }

    /**
     * #查询历史订单 请求中包含---用户的 uid
     */
    @Test
    public void testFindHistoricalByUid() {
        String uid = "4";
        String json = service.findHistoricalByUid(uid);
        ResultInfo info = ServletUtils.getInfo(true, json, "");
        System.out.println(info);
    }
    @Test
    public void testSelectAll(){
        JdbcTemplate template = new JdbcTemplate(DruidUtils.getDs());
        String sql = "SELECT * FROM `order` WHERE `order_id` IN (" +
                        "SELECT order_id FROM historical_order WHERE user_phone IN(" +
                            "SELECT user_phone FROM `user` WHERE uid = ?" +
                        ")" +
                    ")";
        List<Order> order_list = template.query(sql, new BeanPropertyRowMapper<Order>(Order.class), "4");
        System.out.println(order_list);
    }


}
