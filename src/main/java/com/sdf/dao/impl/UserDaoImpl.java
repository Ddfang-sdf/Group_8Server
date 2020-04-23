package com.sdf.dao.impl;

import com.sdf.dao.UserDao;
import com.sdf.domain.Order;
import com.sdf.domain.Scanner;
import com.sdf.domain.User;
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

    @Override
    public boolean realAddressUpdate(String real_time_address, Long order_id) {
        String sql = "UPDATE `order` SET real_time_address = ? WHERE order_id = ?";
        int check = template.update(sql,real_time_address,order_id);
        if (check <= 0)
            return false;
        return true;
    }

    @Override
    public boolean signStatusChange(Long order_id, String real_time_address) {
        String sql = "UPDATE `order`" +
                "SET sign_for = 'Y' , sign_date = NOW() , real_time_address = ?" +
                "WHERE order_id = ?";
        int check = template.update(sql, real_time_address, order_id);
        if (check <= 0)//失败返回false
         return false;
        return true;
    }

    @Override
    public Order findPhoneByOrderid(Long order_id) {
        Order order = null;
        String id = new String(String.valueOf(order_id));
        String sql = "SELECT * FROM `order` WHERE order_id = ?";
        try {

            order = template.queryForObject(sql, new BeanPropertyRowMapper<Order>(Order.class), id);

        }catch (EmptyResultDataAccessException e){
            return order;
        }
        return order;
    }

    /**
     * 因为不会用 JdbcTemplate的事务管理功能，所以这里即使给出了是否查询成功的信号，也没有意义
     * @param user_phone
     * @param order_id
     */
    @Override
    public boolean insertIntoHistory(String user_phone, Long order_id) {
        String sql = "INSERT INTO historical_order VALUES(?,?)";
        int check = template.update(sql, user_phone, order_id);
        if (check <= 0)//插入失败，返回false
            return false;
        return true;
    }

    @Override
    public Order findOrderById(String order_id) {
        Order order = null;
        String sql = "SELECT * FROM `order` WHERE order_id = ?";
        try {
            order = template.queryForObject(sql,new BeanPropertyRowMapper<Order>(Order.class),order_id);
        }catch (EmptyResultDataAccessException e){
            return order;
        }
        return order;
    }

    @Override
    public boolean UserRegist(User _user) {
        String sql = "INSERT INTO `user` VALUES(NULL,?,?,?,?,?,?,?)";
        Object[] args = new Object[]{
          _user.getUsername(),_user.getPasswd(),_user.getAddress(),_user.getGender(),
                _user.getAge(),_user.getIdentify(),_user.getUser_phone()
        };
        int check = template.update(sql, args);
        if (check <= 0)
            return false;
        return true;
    }

    @Override
    public User userLogin(User _user) {
        User user = null;
        String sql = "SELECT * FROM USER WHERE username = ? AND passwd = ?";
        try {
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), _user.getUsername(), _user.getPasswd());
        }catch (EmptyResultDataAccessException e){
            return user;
        }
        return user;
    }
}
