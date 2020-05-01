package com.sdf.dao;

import com.sdf.domain.Scanner;

import java.io.Serializable;

public interface UserMapper {

    /**
     * 查询是否存在该扫描员用户
     * @param scanner
     * @return
     */
    Scanner findForLogin(Scanner scanner);
}
