package com.sdf.web.servlet;

import com.sdf.domain.Order;
import com.sdf.domain.ResultInfo;
import com.sdf.service.UserService;
import com.sdf.service.impl.UserServiceImpl;
import com.sdf.utils.MsgHouseUtils;
import com.sdf.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findIdentifyByUidServlet")
public class FindIdentifyByUidServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //创建业务层对象
        UserService userService = new UserServiceImpl();
        //创建结果集对象
        ResultInfo res = null;
        //创建响应数据
        String json = null;
        //获取请求参数
        String uid = request.getParameter("uid");
        //查询订单
        String identify = userService.findIdentifyByUid(uid);

        if(identify == null || identify.length() == 0){
            res = ServletUtils.getInfo(false,null,MsgHouseUtils.IdentifyErrorMsg);
            json = ServletUtils.getJsonInfo(res);
            response.getWriter().write(json);
            return;
        }
        res = ServletUtils.getInfo(true,identify,"");
        json = ServletUtils.getJsonInfo(res);
        response.getWriter().write(json);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request,response);
    }
}
