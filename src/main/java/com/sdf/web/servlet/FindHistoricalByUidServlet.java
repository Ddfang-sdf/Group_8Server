package com.sdf.web.servlet;

import com.sdf.domain.Order;
import com.sdf.domain.ResultInfo;
import com.sdf.service.UserMapperService;
import com.sdf.utils.MsgHouseUtils;
import com.sdf.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FindHistoricalByUidServlet")
public class FindHistoricalByUidServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //创建业务层对象
        UserMapperService service = new UserMapperService();
        //创建结果集对象
        ResultInfo res = null;
        //创建响应数据
        String json = null;
        //获取请求参数
        String uid = request.getParameter("uid");
        Integer user_id = Integer.parseInt(uid);
        //查询历史订单
        List<Order> historicalOrders = service.findHistoricalByUid(user_id);

        if (historicalOrders != null || historicalOrders.size() != 0){
            res = ServletUtils.getInfo(true,historicalOrders,"");
            json = ServletUtils.getJsonInfo(res);
            response.getWriter().write(json);
        }else {
            res = ServletUtils.getInfo(false,null,MsgHouseUtils.orderNullErrorMsg);
            json = ServletUtils.getJsonInfo(res);
            response.getWriter().write(json);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request,response);
    }
}
