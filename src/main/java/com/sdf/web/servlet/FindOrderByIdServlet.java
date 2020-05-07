package com.sdf.web.servlet;

import com.sdf.domain.Order;
import com.sdf.domain.ResultInfo;
//<<<<<<< Updated upstream
import com.sdf.service.UserMapperService;
//=======
import com.sdf.domain.User;
import com.sdf.service.UserMapperService;
//>>>>>>> Stashed changes
import com.sdf.utils.MsgHouseUtils;
import com.sdf.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//订单号查询订单Servlet
@WebServlet("/findOrderByIdServlet")
public class FindOrderByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //创建业务层对象
//<<<<<<< Updated upstream
        UserMapperService service = new UserMapperService();
//=======
        //UserMapperService userService = new UserMapperService();
//>>>>>>> Stashed changes
        //创建结果集对象
        ResultInfo res = null;
        //创建响应数据
        String json = null;
        //获取请求参数
        String order_id = request.getParameter("order_id");
        /*
        *
        * 这个地方应该做一个校验，所有获取用户数据的地方都要有一个后台校验的过程。
        * 数据库中的订单从372036854775807开始自增。
        * 有些数据不可以简单的获取一下就没有后续了，如果前台校验有bug，后台也没有校验的话，整个功能就存在bug。
        * 后台校验需要你稍微看一下数据库，知道每一个数据的约束，数据类型。然后再在servlet中校验。
        *
        * ----by sdf
        */
        try{
            Long orderId = Long.parseLong(order_id);
            if (orderId < 372036854775807l){
                res = ServletUtils.getInfo(false,null,MsgHouseUtils.orderNullErrorMsg);
                json = ServletUtils.getJsonInfo(res);
                response.getWriter().write(json);
                return;
            }
        }catch (ClassCastException e){

            res = ServletUtils.getInfo(false,null,MsgHouseUtils.orderNullErrorMsg);
            json = ServletUtils.getJsonInfo(res);
            response.getWriter().write(json);
            e.printStackTrace();
            return;
        }

        //查询订单
        Order orderById = service.findOrderById(order_id);

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
