package com.sdf.dao;

import com.sdf.domain.Scanner;

public interface UserDao {
    /**
     * 查询是否存在该扫描员用户
     * @param scanner
     * @return
     */
    Scanner findForLogin(Scanner scanner);

    /**
     * 根据订单号更改order表的real_time_address的值
     *
     * @param real_time_address
     * @param order_id
     * @return
     */
    boolean realAddressUpdate(String real_time_address, Long order_id);
}
