package com.sdf.test;

import com.sdf.dao.UserMapper;
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
import java.util.Map;

public class TestForUserMapper {

    private InputStream config;
    private SqlSessionFactory factory;
    private SqlSession session;
    private UserMapper userMapper;

    //@Before
    public void init(){
        try {
            config = Resources.getResourceAsStream("mybatis-config.xml");
            factory = new SqlSessionFactoryBuilder().build(config);
            session = factory.openSession();
            userMapper = session.getMapper(UserMapper.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //@After
    public void destroy(){
        try{
            session.commit();
        }catch (PersistenceException e){
        }finally{
            if(config != null){
                try {
                    config.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(session != null){
                session.close();
            }
        }
    }
    private UserMapperService service = new UserMapperService();
    private ResultInfo info;
    private String json;
    @Test
    public void testUserLogin() throws InvocationTargetException, IllegalAccessException {
        User _user = new User();
        String username = "李莫愁";
        String passwd = "123456";
        Map<String, String> map = new HashMap<>();
        map.put("username", "李莫愁");
        map.put("passwd", "12356");
        BeanUtils.populate(_user, map);
        User user = service.testUserLogin(_user);
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
}
