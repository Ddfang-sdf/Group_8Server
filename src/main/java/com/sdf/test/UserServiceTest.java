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
     *
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Test
    public void testScannerLogin() throws InvocationTargetException, IllegalAccessException {
        Scanner scanner_cus = new Scanner();
        ScannerService scannerService = new ScannerServiceImpl();
        ResultInfo info = null;
        //servlet 从请求中获取用户名密码，封装为map
        Map<String, String> map = new HashMap<>();
        map.put("s_username", "赵校来");
        map.put("s_passwd", "123456");
        BeanUtils.populate(scanner_cus, map);
        Scanner scanner_ser = scannerService.ScannerLogin(scanner_cus);
        if (scanner_ser != null) {
            info = ServletUtils.getInfo(true, scanner_ser, "");
        } else {
            info = ServletUtils.getInfo(false, scanner_ser, "用户名或密码不正确");
        }
        System.out.println(info);

    }

    /**
     * 测试扫描员修改实时地址
     * 扫描员修改订单实时地址，请求中发送订单号,本次订单号位372036854775808
     */
    @Test
    public void testReal_address_update() {
        //servlet中获取用户数据
        Long order_id = 372036854775808l;
        String real_time_address = "河南省郑州市郑州大学校区仁和宿舍3号楼811室";
        if (scannerService.realAddressUpdate(real_time_address, order_id)) {
            //修改成功
            System.out.println("T");
        } else {
            //修改失败
            System.out.println("F");
        }

    }

    /**
     * 测试快件签收业务
     * #扫描员确认订单签收，修改签收状态、实时地址和签收日期，并且将订单插入历史订单表中
     * #修改订单表信息
     * #查询用户手机号
     * #插入历史订单
     * JdbcTemplate属于springJdbc的一个类，因为没有深入学习过spring的知识，现在仅仅会增删改查，
     * 对于开启事务掌握的不好。就先不用了。由于一个线程上可以有多个连接对象，如果不使用jdbcTemplate，
     * 就要要开启事务必须要使用原生的jdbc，这样会导致多个dao层数据库基本操作冗余在一起。极易出错。这里
     * 就先不使用事务了。
     */
    @Test
    public void testOrder_sign() {
        //获取用户数据，
        //订单号
        Long order_id = 372036854775808l;
        // 签收状态
        String sign_for = "Y";
        //实时地址
        String real_time_address = "河南省郑州市郑州大学校区仁和宿舍3号楼811室";
        //调用业务层快件签收方法
        if (scannerService.ExpressSignIn(order_id, real_time_address)) {
            //快件签收完成
            System.out.println("快件签收完成");
        } else {
            System.out.println("出错了！！！");
        }


    }

    /**
     * 用户注册功能测试
     * 表单数据如下：
     * NULL,'李莫愁','123456','河南省开封市金明校区华苑3号楼811室','女',17,'273747596018273645','13588239999'
     */
    @Test
    public void test() {
        Map<String,String> map = new HashMap<>();
        map.put("uid","李莫愁");
        map.put("uid","李莫愁");

    }

}
