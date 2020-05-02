package com.sdf.web.servlet;

import com.sdf.domain.Order;
import com.sdf.domain.ResultInfo;
import com.sdf.domain.User;
import com.sdf.service.UserService;
import com.sdf.service.impl.UserServiceImpl;
import com.sdf.utils.MsgHouseUtils;
import com.sdf.utils.ServletUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/findOrderByIdServlet")
public class FindOrderByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //创建业务层对象
        UserService userService = new UserServiceImpl();
        //创建结果集对象
        ResultInfo res = null;
        //创建响应数据
        String json = null;
        //获取请求参数
        String order_id = request.getParameter("order_id");
        //查询订单
        Order orderById = userService.findOrderById(order_id);

        if(orderById == null){
            res = ServletUtils.getInfo(false,null,MsgHouseUtils.orderNullErrorMsg);
            json = ServletUtils.getJsonInfo(res);
            response.getWriter().write(json);
            return;
        }
        res = ServletUtils.getInfo(true,orderById,"");
        json = ServletUtils.getJsonInfo(res);
        response.getWriter().write(json);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request,response);
    }
}
