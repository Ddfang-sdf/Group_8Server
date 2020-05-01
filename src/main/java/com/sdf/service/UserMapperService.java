package com.sdf.service;

import com.sdf.dao.UserMapper;
import com.sdf.domain.User;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class UserMapperService {

    private InputStream config;
    private SqlSessionFactory factory;
    private SqlSession session;
    private UserMapper userMapper;
    private void init(){
        try {
            config = Resources.getResourceAsStream("mybatis-config.xml");
            factory = new SqlSessionFactoryBuilder().build(config);
            session = factory.openSession();
            userMapper = session.getMapper(UserMapper.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
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

    public User testUserLogin(User _user)  {

        init();
        try {

            return userMapper.userLogin(_user);

        }finally {

            destroy();

        }


    }

}
