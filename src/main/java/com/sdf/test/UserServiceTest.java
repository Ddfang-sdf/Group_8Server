package com.sdf.test;

import com.sdf.domain.Scanner;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;


import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试类，用于测试service层函数
 */
public class UserServiceTest {
    @Test
    public void testScannerLogin() throws InvocationTargetException, IllegalAccessException {
        Scanner scanner = new Scanner();
        //servlet 从请求中获取用户名密码，封装为map
        Map<String,String> map = new HashMap<>();
        map.put("s_username","赵校来");
        map.put("s_passwd","123456");
        BeanUtils.populate(scanner,map);

    }
}
