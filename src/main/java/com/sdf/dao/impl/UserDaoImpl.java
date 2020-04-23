package com.sdf.dao.impl;

import com.sdf.dao.UserDao;
import com.sdf.domain.Order;
import com.sdf.domain.Scanner;
import com.sdf.domain.User;
import com.sdf.utils.DruidUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {
    JdbcTemplate template = new JdbcTemplate(DruidUtils.getDs());

    @Override
    public Scanner findForLogin(Scanner _scanner) {
        String sql = "SELECT * FROM scanner WHERE s_username = ? AND s_passwd = ?";
        Scanner scanner = null;
        try {
            scanner = template.queryForObject(sql, new BeanPropertyRowMapper<Scanner>(Scanner.class), _scanner.getS_username(), _scanner.getS_passwd());
        } catch (EmptyResultDataAccessException e) {
            return scanner;
        }
        return scanner;
    }

    @Override
    public boolean realAddressUpdate(String real_time_address, String order_id) {
        String sql = "UPDATE `order` SET real_time_address = ? WHERE order_id = ?";
        int check = template.update(sql, real_time_address, order_id);
        if (check <= 0)
            return false;
        return true;
    }

    @Override
    public boolean signStatusChange(String order_id, String real_time_address) {
        String sql = "UPDATE `order`" +
                "SET sign_for = 'Y' , sign_date = NOW() , real_time_address = ?" +
                "WHERE order_id = ?";
        int check = template.update(sql, real_time_address, order_id);
        if (check <= 0)//失败返回false
            return false;
        return true;
    }

    @Override
    public String findPhoneByOrderid(String order_id) {
        String sender_phone = null;

        String sql = "SELECT sender_phone FROM `order` WHERE order_id = ?";
        try {

            sender_phone = template.queryForObject(sql, String.class, order_id);

        } catch (EmptyResultDataAccessException e) {
            return sender_phone;
        }
        return sender_phone;
    }

    /**
     * 因为不会用 JdbcTemplate的事务管理功能，所以这里即使给出了是否查询成功的信号，也没有意义
     *  @param user_phone
     * @param order_id
     */
    @Override
    public boolean insertIntoHistory(String user_phone, String order_id) {
        String sql = "INSERT INTO historical_order VALUES(?,?)";
        int check = 0;
        try {

             check = template.update(sql, user_phone, order_id);
        }catch (DuplicateKeyException e){
            return true;
        }
        if (check <= 0)//插入失败，返回false
            return false;
        return true;
    }

    @Override
    public Order findOrderById(String order_id) {
        Order order = null;
        String sql = "SELECT * FROM `order` WHERE order_id = ?";
        try {
            order = template.queryForObject(sql, new BeanPropertyRowMapper<Order>(Order.class), order_id);
        } catch (EmptyResultDataAccessException e) {
            return order;
        }
        return order;
    }

    @Override
    public boolean UserRegist(User _user) {
        String sql = "INSERT INTO `user` VALUES(NULL,?,?,?,?,?,?,?)";
        Object[] args = new Object[]{
                _user.getUsername(), _user.getPasswd(), _user.getAddress(), _user.getGender(),
                _user.getAge(), _user.getIdentify(), _user.getUser_phone()
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
        } catch (EmptyResultDataAccessException e) {
            return user;
        }
        return user;
    }

    @Override
    public List<Order> findHistoricalByUid(String uid) {
        List<Order> order_list = null;
        String sql = "SELECT * FROM `order` WHERE `order_id` IN ( " +
                "SELECT order_id FROM historical_order WHERE user_phone IN( " +
                "SELECT user_phone FROM `user` WHERE uid = ? " +
                ")" +
                ")";
        try {
            order_list = template.query(sql, new BeanPropertyRowMapper<Order>(Order.class), uid);
        } catch (EmptyResultDataAccessException e) {
            return order_list;
        }
        return order_list;
    }

    @Override
    public boolean mailingByUid(Order _order) {
        String sql = "INSERT INTO `order` VALUES(?,?,?,?,?,?,?,NULL,'N',NULL,?,?,?)";
        Object[] args = new Object[]{
                _order.getUid(), _order.getSender_address(), _order.getSender_name(),
                _order.getSender_phone(), _order.getReceiver_address(), _order.getReceiver_name(),
                _order.getReceiver_phone(),_order.getWeight(),_order.getType(),
                _order.getReal_time_address()
        };
        int check = template.update(sql, args);
        if (check <= 0){
            return false;
        }
        return true;
    }

    @Override
    public User findUserByUid(String uid) {
        User user = null;
        String sql = "select * from user where uid = ?";
        try{
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), uid);

        }catch (EmptyResultDataAccessException e){
            return user;
        }
        return user;
    }

    @Override
    public boolean changeUserInfo(User _user) {
        User user = null;
        String sql_update = "UPDATE USER u " +
                "INNER JOIN `order` o  ON u.`uid`=o.`uid` " +
                "SET u.`username`=?,u.`gender`=?,u.age=?,u.`address`=?, " +
                "o.`sender_name`=u.`username`,u.`address`=o.`sender_address` " +
                "WHERE u.`uid`=? ";
        Object[] args = new Object[]{
                _user.getUsername(),_user.getGender(),_user.getAge(),
                _user.getAddress(),_user.getUid()
        };
        int check = template.update(sql_update, args);
        if (check <= 0)
            return false;
        return true;
    }

    @Override
    public String findIdentifyByUid(String uid) {
        String sql = "select identify from user where uid = ?";
        String identify = null;
        try{
            identify = template.queryForObject(sql, String.class, uid);
        }catch (EmptyResultDataAccessException e){
            return identify;
        }
        return identify;
    }

    @Override
    public boolean changeIdentify(String uid, String identify) {
        String sql = "UPDATE `user` SET identify = ? WHERE uid = ?";
        int check = template.update(sql,identify,uid);
        if (check <= 0)
            return false;
        return true;
    }
}
