package com.sdf.dao.impl;

import com.sdf.dao.UserDao;
import com.sdf.domain.Scanner;
import com.sdf.utils.DruidUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {
    JdbcTemplate template = new JdbcTemplate(DruidUtils.getDs());

    @Override
    public Scanner findForLogin(Scanner _scanner) {
        String sql = "SELECT * FROM scanner WHERE s_username = ? AND s_passwd = ?";
        Scanner scanner = null;
        try{
            scanner = template.queryForObject(sql, new BeanPropertyRowMapper<Scanner>(Scanner.class), _scanner.getS_username(), _scanner.getS_passwd());
        }catch (EmptyResultDataAccessException e){
            return scanner;
        }
        return scanner;
    }
}
