package com.sdf.utils;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * druid数据库连接池工具类
 */
public class DruidUtils {
    private DataSource ds;
    static {
        Properties prop = new Properties();
        try {
            prop.load(DruidUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
