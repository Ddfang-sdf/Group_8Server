package com.sdf.service;

import com.sdf.dao.UserDao;
import com.sdf.dao.impl.UserDaoImpl;
import com.sdf.domain.Scanner;

public interface ScannerService {

    UserDao dao = new UserDaoImpl();

    /**
     * 扫描员登陆业务
     * @param scanner
     * @return
     */
    Scanner ScannerLogin(Scanner scanner);

    /**
     * 扫描员更新实时地址业务
     * @param real_time_address
     * @param order_id
     * @return
     */
    boolean realAddressUpdate(String real_time_address, Long order_id);
}
