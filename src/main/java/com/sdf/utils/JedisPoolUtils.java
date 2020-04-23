package com.sdf.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.Properties;

public class JedisPoolUtils {
    private static JedisPool jedisPool = null;

    //加载配置文件
    static {
        Properties prop = new Properties();
        try {
            prop.load(JedisPoolUtils.class.getClassLoader().getResourceAsStream("jedis.properties"));
            //创建并配置连接池配置对象
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxIdle(Integer.parseInt(prop.getProperty("maxIdle")));
            config.setMaxTotal(Integer.parseInt(prop.getProperty("maxTotal")));
            //创建数据库连接池对象
            jedisPool = new JedisPool(config,prop.getProperty("host"),Integer.parseInt(prop.getProperty("port")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Jedis数据库连接对象的方法
     * @return
     */
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}
