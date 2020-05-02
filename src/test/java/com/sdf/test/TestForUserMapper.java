package com.sdf.test;

import com.sdf.domain.Order;
import com.sdf.domain.ResultInfo;
import com.sdf.domain.User;
import com.sdf.service.UserMapperService;
import com.sdf.utils.MsgHouseUtils;
import com.sdf.utils.ServletUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestForUserMapper {


    private UserMapperService service = new UserMapperService();
    private ResultInfo info;
    private String json;
    @Test
    public void testUserLogin() throws InvocationTargetException, IllegalAccessException {
        User _user = new User();
        String username = "李莫愁";
        String passwd = "123456";
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("passwd", passwd);
        BeanUtils.populate(_user, map);
        User user = service.userLogin(_user);
        if (user != null) {
            info = ServletUtils.getInfo(true,user,"");
            json = ServletUtils.getJsonInfo(info);
        }else {
            info = ServletUtils.getInfo(false,null, MsgHouseUtils.loginErrorMsg);
            json = ServletUtils.getJsonInfo(info);
        }
        System.out.println(json);
    }

    @Test
    public void testUserRegist() throws InvocationTargetException, IllegalAccessException {
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

        if(service.userRegist(_user)) {
            info = ServletUtils.getInfo(true,null,"");
            json = ServletUtils.getJsonInfo(info);
        }else {
            info = ServletUtils.getInfo(false,null,MsgHouseUtils.registerErrorMsg);
            json = ServletUtils.getJsonInfo(info);
        }
        System.out.println(json);

    }

    @Test
    public void testFindOrderById(){
        String order_id = "372036854775807";
        Order orderById = service.findOrderById(order_id);
        if (orderById != null){
            info = ServletUtils.getInfo(true,orderById,"");
            json = ServletUtils.getJsonInfo(info);
        }else {
            info = ServletUtils.getInfo(false,null,MsgHouseUtils.errorMsg);
            json = ServletUtils.getJsonInfo(info);
        }
        System.out.println(json);
    }

    @Test
    public void testFindHistoricalByUid(){
        Integer uid = 4;
        List<Order> historicalByUid = service.findHistoricalByUid(uid);
        if (historicalByUid != null || historicalByUid.size() != 0){
            info = ServletUtils.getInfo(true,historicalByUid,"");
            json = ServletUtils.getJsonInfo(info);
        }else {
            info = ServletUtils.getInfo(false,null,MsgHouseUtils.errorMsg);
            json = ServletUtils.getJsonInfo(info);
        }
        System.out.println(json);

    }

    @Test
    public void testMailingByUid(){
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
        if(service.mailingByUid(order)){
            info = ServletUtils.getInfo(true,null,"");
            json = ServletUtils.getJsonInfo(info);
        }else {
            info = ServletUtils.getInfo(false,null,MsgHouseUtils.sendExpressErrorMsg);
            json = ServletUtils.getJsonInfo(info);
        }
        System.out.println(json);
    }

    @Test
    public void testSaveUserInfo() throws InvocationTargetException, IllegalAccessException {
        User _user = new User();
        Map<String, String> map = new HashMap<>();
        map.put("uid","6");
        map.put("username", "李莫愁");
        map.put("address", "上海虹桥机场");
        map.put("gender", "女");
        map.put("age", "17");
        map.put("identify", "273747596018273645");

        //System.out.println(map);
        BeanUtils.populate(_user, map);

        User user = service.saveUserInfo(_user);
        if (user != null){
            info = ServletUtils.getInfo(true,user,"");
            json = ServletUtils.getJsonInfo(info);
        }else {
            info = ServletUtils.getInfo(false,null,MsgHouseUtils.UserInfoErrorMsg);
            json = ServletUtils.getJsonInfo(info);
        }
        System.out.println(json);
    }
















}
