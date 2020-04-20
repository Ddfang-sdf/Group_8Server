package com.sdf.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * druid数据库连接池工具类
 */
public class DruidUtils {
    private static DataSource ds;


    static {
        Properties prop = new Properties();
        try {
            prop.load(DruidUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(prop);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("DruidUtils 加载配置文件出错！！！");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("DruidUtils 创建数据源出错！！！");
        }
    }

    /**
     * 获取数据源对象
     * @return
     */
    public DataSource getDs(){
        return ds;
    }

    /**
     * 获取数据库连接对象
     * @return
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
