package com.sdf.service;

import com.sdf.dao.ScannerMapper;
import com.sdf.dao.UserMapper;
import com.sdf.domain.Scanner;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class ScannerMapperService {

    private InputStream config;
    private SqlSessionFactory factory;
    private SqlSession session;
    private ScannerMapper scannerMapper;

    private void init() {
        try {
            config = Resources.getResourceAsStream("mybatis-config.xml");
            factory = new SqlSessionFactoryBuilder().build(config);
            session = factory.openSession();
            scannerMapper = session.getMapper(ScannerMapper.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void destroy() {
        try {
            session.commit();
        } catch (Exception e) {
            session.rollback();
        } finally {
            if (config != null) {
                try {
                    config.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * 扫描员登陆
     * @param scanner
     * @return
     */
    public Scanner findForLogin(Scanner scanner){
        init();
        try {
            return scannerMapper.findForLogin(scanner);
        }finally {
            destroy();
        }

    }

    /**
     * 修改实时地址
     * @param real_time_address
     * @param order_id
     * @return
     */
    public boolean realAddressUpdate(String real_time_address,  String order_id){
        init();
        try {
            return scannerMapper.realAddressUpdate(real_time_address,order_id);
        }catch (Exception e){
            session.rollback();
            throw new RuntimeException("更新实时地址失败");
        }finally {
            destroy();
        }
    }

    /**
     * 更新签收状态
     * @param order_id
     * @param real_time_address
     * @return
     */
    public boolean signStatusChange(String order_id, String real_time_address){
        init();
        try {
            return scannerMapper.signStatusChange(order_id,real_time_address);
        }catch (Exception e){
            session.rollback();
            e.printStackTrace();
            throw new RuntimeException("更新签收状态异常");
        }finally {
            destroy();
        }
//        return false;
    }




}
