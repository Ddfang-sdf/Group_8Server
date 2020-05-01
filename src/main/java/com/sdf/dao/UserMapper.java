package com.sdf.dao;

import com.sdf.domain.Scanner;
import com.sdf.domain.User;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

public interface UserMapper {

    /**
     * 用户登陆
     * @param  user
     * @return
     */
    User userLogin(@Param("user") User user);
}
