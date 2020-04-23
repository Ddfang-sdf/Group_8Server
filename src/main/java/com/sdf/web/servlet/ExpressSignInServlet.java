package com.sdf.web.servlet;

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

/**
 * 快件确认签收业务
 * 需要请求中包含订单号和实时地址
 */
@WebServlet("/ExpressSignInServlet")
public class ExpressSignInServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //设置响应编码格式
        resp.setContentType("application/json;charset=utf-8");
        //创建业务层对象
        UserService service = new UserServiceImpl();
        //创建结果集对象
        ResultInfo info = null;
        //创建响应数据
        String json = null;
        //获取订单号和实时地址
        String order_id = req.getParameter("order_id");
        String real_time_address = req.getParameter("real_time_address");
        //调用业务层方法
        boolean check = service.ExpressSignIn(order_id, real_time_address);
        if (check){
            //签收操作成功
            info = ServletUtils.getInfo(true,null,"");
            json = ServletUtils.getJsonInfo(info);
            resp.getWriter().write(json);
        }else {
            //签收失败
            info = ServletUtils.getInfo(false,null, MsgHouseUtils.expressSignMsg);
            json = ServletUtils.getJsonInfo(info);
            resp.getWriter().write(json);
        }
        //相应结果
    }
}
