package com.sdf.test;

import com.sdf.domain.ResultInfo;
import com.sdf.domain.Scanner;
import com.sdf.service.ScannerService;
import com.sdf.service.impl.ScannerServiceImpl;
import com.sdf.utils.ServletUtils;
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
        Scanner scanner_cus = new Scanner();
        ScannerService service = new ScannerServiceImpl();
        ResultInfo info = null;
        //servlet 从请求中获取用户名密码，封装为map
        Map<String,String> map = new HashMap<>();
        map.put("s_username","赵校来");
        map.put("s_passwd","123456");
        BeanUtils.populate(scanner_cus,map);
        Scanner scanner_ser = service.ScannerLogin(scanner_cus);
        if (scanner_ser != null){
            info = ServletUtils.getInfo(true, scanner_ser, "");
        }else{
            info = ServletUtils.getInfo(false,scanner_ser,"用户名或密码不正确");
        }
        System.out.println(info);

    }
}
