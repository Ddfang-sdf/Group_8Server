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

    ScannerService scannerService = new ScannerServiceImpl();

    /**
     * 业务层扫描员登录功能测试
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Test
    public void testScannerLogin() throws InvocationTargetException, IllegalAccessException {
        Scanner scanner_cus = new Scanner();
        ScannerService scannerService = new ScannerServiceImpl();
        ResultInfo info = null;
        //servlet 从请求中获取用户名密码，封装为map
        Map<String,String> map = new HashMap<>();
        map.put("s_username","赵校来");
        map.put("s_passwd","123456");
        BeanUtils.populate(scanner_cus,map);
        Scanner scanner_ser = scannerService.ScannerLogin(scanner_cus);
        if (scanner_ser != null){
            info = ServletUtils.getInfo(true, scanner_ser, "");
        }else{
            info = ServletUtils.getInfo(false,scanner_ser,"用户名或密码不正确");
        }
        System.out.println(info);

    }

    /**
     * 测试扫描员修改实时地址
     * 扫描员修改订单实时地址，请求中发送订单号,本次订单号位372036854775808
     */
    @Test
    public void testReal_address_update(){
        //servlet中获取用户数据
        Long order_id = 372036854775808l;
        String real_time_address = "河南省郑州市郑州大学校区仁和宿舍3号楼811室";
        if (scannerService.realAddressUpdate(real_time_address, order_id)){
            //修改成功
            System.out.println("T");
        }else {
            //修改失败
            System.out.println("F");
        }

    }


}
