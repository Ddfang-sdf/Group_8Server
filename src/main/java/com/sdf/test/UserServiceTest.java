package com.sdf.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdf.domain.Order;
import com.sdf.domain.ResultInfo;
import com.sdf.domain.Scanner;
import com.sdf.domain.User;
import com.sdf.service.UserService;
import com.sdf.service.impl.UserServiceImpl;
import com.sdf.utils.DruidUtils;
import com.sdf.utils.JedisPoolUtils;
import com.sdf.utils.MsgHouseUtils;
import com.sdf.utils.ServletUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import redis.clients.jedis.Jedis;


import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试类，用于测试service层函数
 */
public class UserServiceTest {

    ObjectMapper mapper = new ObjectMapper();
    UserService service = new UserServiceImpl();
    JdbcTemplate template = new JdbcTemplate(DruidUtils.getDs());

    /**
     * 业务层扫描员登录功能测试 -----ScannerLoginServlet
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
        Scanner scanner_ser = scannerService.ScannerLogin(scanner_cus);
        if (scanner_ser != null) {
            info = ServletUtils.getInfo(true, scanner_ser, "");
        } else {
            info = ServletUtils.getInfo(false, scanner_ser, MsgHouseUtils.loginErrorMsg);
        }
        System.out.println(info);

    }

    /**
     * 测试扫描员修改实时地址 -----ChangeAddressServlet
     * 扫描员修改订单实时地址，请求中发送订单号,本次订单号位372036854775808
     */
    @Test
    public void testReal_address_update() {
        //servlet中获取用户数据
        String order_id = "372036854775808";
        ResultInfo info = null;
        String real_time_address = "河南省郑州市郑州大学校区仁和宿舍3号楼811室";
        if (service.realAddressUpdate(real_time_address, order_id)) {
            //修改成功
            info = ServletUtils.getInfo(true, true, "");
        } else {
            //修改失败
            info = ServletUtils.getInfo(false, false, MsgHouseUtils.changeErrorMsg);
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
        String order_id = "372036854775808";
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
    public void testFindOrderById() throws JsonProcessingException {
        String order_id = "372036854775807";
        Order orderById = service.findOrderById(order_id);

        ResultInfo info = ServletUtils.getInfo(true, orderById, "");
        String s = mapper.writeValueAsString(info);
        System.out.println(s);
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
        User user = service.userLogin(_user);
        ResultInfo info = ServletUtils.getInfo(true, user, "");
        System.out.println(info);
    }

    /**
     * #查询历史订单 请求中包含---用户的 uid
     */
    @Test
    public void testFindHistoricalByUid() throws JsonProcessingException {
        String uid = "4";
        List<Order> json = service.findHistoricalByUid(uid);
        ResultInfo info = ServletUtils.getInfo(true, json, "");
        String s = mapper.writeValueAsString(info);

        System.out.println(s);
    }

    @Test
    public void testSelectAll() {

        String sql = "SELECT * FROM `order` WHERE `order_id` IN (" +
                "SELECT order_id FROM historical_order WHERE user_phone IN(" +
                "SELECT user_phone FROM `user` WHERE uid = ?" +
                ")" +
                ")";
        List<Order> order_list = template.query(sql, new BeanPropertyRowMapper<Order>(Order.class), "4");
        System.out.println(order_list);
    }

    /**
     * 寄件功能测试，
     * #寄件功能,请求中包含---包含uid，此处李莫愁注册登陆之后，uid是6
     */
    @Test
    public void testMailingByUid() throws JsonProcessingException {
        String uid = "6";
        ResultInfo info = null;
        String json = null;
        Order order = new Order();
        order.setUid(6);
        order.setSender_address("河南省开封市明伦小区仁和公寓3号楼811室");
        order.setSender_name("李莫愁");
        order.setSender_phone("13788239993");
        order.setReceiver_address("河南省开封市金明校区华苑3号楼811室");
        order.setReceiver_name("陆展元");
        order.setReceiver_phone("13566668380");
        order.setWeight(20);
        order.setType("易碎品");
        order.setReal_time_address("河南省开封市明伦小区仁和公寓3号楼811室");
        /*
        6,'河南省开封市明伦小区仁和公寓3号楼811室','李莫愁','13788239993','河南省开封市金明校区华苑3号楼811室',
'陆展元','13566668380',NULL,'N',NULL,20,'易碎品','河南省开封市明伦小区仁和公寓3号楼811室'
         */
        if (service.mailingByUid(order)) {
            //成功
            info = ServletUtils.getInfo(true, null, "");
            json = mapper.writeValueAsString(info);

        } else {
            //失败
            info = ServletUtils.getInfo(false, null, MsgHouseUtils.sendExpressErrorMsg);
            json = mapper.writeValueAsString(info);
        }
        System.out.println(json);
    }

    /**
     * 用户修改个人信息：
     * 流程如下：
     * 1、数据回显
     * 2、获取表单内容，并修改用户个人信息
     */
    @Test
    public void testChangeUserInfo() throws JsonProcessingException {
        String uid = "4";
        //数据回显
        User user_view = service.findUserByUid(uid);
        ResultInfo info = null;
        if (user_view != null) {
            info = ServletUtils.getInfo(true, user_view, "");
            String json = mapper.writeValueAsString(info);
            System.out.println(json);
        } else {
            info = ServletUtils.getInfo(false, null, MsgHouseUtils.UserInfoErrorMsg);
            String json = mapper.writeValueAsString(info);
            System.out.println(json);
        }
        //获取表单数据，封装User对象，修改后返回user对象
        /*

u.`username`='光头强',u.`gender`='男',u.age=18,u.`address`='河南省开封市金明校区华苑3号楼811室',

u.`uid`=4;
         */
        //封装user对象
        User user = new User();
        user.setUid("4");
        user.setUsername("光头强");
        user.setGender("男");
        user.setAge("18");
        user.setAddress("河南省开封市金明校区华苑3号楼811室");

        boolean user_changed = service.changeUserInfo(user);
        if (user_changed) {
            //修改成功 再次查询用户数据
            user_view = service.findUserByUid(uid);
            info = ServletUtils.getInfo(true, user_view, "");
            String json = mapper.writeValueAsString(info);
            System.out.println(json);
        } else {
            //修改失败，提示错误信息
            info = ServletUtils.getInfo(false, null, MsgHouseUtils.errorMsg);
            String json = mapper.writeValueAsString(info);
            System.out.println(json);
        }
    }

    @Test
    public void testchangeSql() {
        String sql = "UPDATE USER u " +
                "INNER JOIN `order` o  ON u.`uid`=o.`uid` " +
                "SET u.`username`='光头强',u.`gender`='男',u.age=18,u.`address`='河南省开封市金明校区华苑3号楼811室' ," +
                "o.`sender_name`=u.`username`,u.`address`=o.`sender_address` " +
                "WHERE u.`uid`=4";
        int update = template.update(sql);
        System.out.println(update);

    }
    /**
     * 用户修改身份证号码功能测试
     * 流程：
     * 1、数据回显
     * 2、修改user表
     * 3、响应新的user对象
     * #修改身份证 请求中包含---包含uid，身份证
     * 此处光头强先生（uid=4）修改身份证为：283747592218273649
     */
    @Test
    public void testchangeIdentify() throws JsonProcessingException {
        String uid = "4";
        String identify = "283747592218273649";
        //1身份证号回显
        String _identify = service.findIdentifyByUid(uid);
        System.out.println("用户修改前身份证号"+_identify);
        //2修改身份证号码
        ResultInfo info = null;
        if (service.changeIdentify(uid,identify)){
            //修改成功
            User userByUid = service.findUserByUid(uid);
            info = ServletUtils.getInfo(true,userByUid,"");
            String json = mapper.writeValueAsString(info);
            System.out.println(json);
        }else{
            //修改失败
            info = ServletUtils.getInfo(false,null,MsgHouseUtils.errorMsg);
            String json = mapper.writeValueAsString(info);
            System.out.println(json);
        }
    }

    @Test
    public void testSelectForOneField(){
        String uid = "4";
        String sql = "select identify from user where uid = 4";
        String identify = template.queryForObject(sql,String.class);
        System.out.println(identify);

    }

    /**
     * 测试redis
     */
    @Test
    public void testRedis(){
        Jedis jedis = JedisPoolUtils.getJedis();

        jedis.set("小拳拳","锤你胸口");

        String tip = jedis.get("小拳拳");

        System.out.println(tip);

        Long del = jedis.del("小拳拳");

        System.out.println(del);

        jedis.close();
    }

}
