package com.sdf.web.servlet;

import com.sdf.domain.ResultInfo;
import com.sdf.domain.User;
import com.sdf.service.UserMapperService;



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

//用户注册Servlet
@WebServlet("/userRegistServlet")
public class UserRegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //创建业务层对象

        UserMapperService service = new UserMapperService();

        //创建结果集对象
        ResultInfo res = null;
        //创建响应数据
        String json = null;
        //创建用户对象
        User registUser = new User();
        //获取请求参数
        Map<String, String[]> map = request.getParameterMap();

        try {
            BeanUtils.populate(registUser, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }




        if (service.userRegist(registUser)) {

            //注册成功，不需要给ErrorMsg ---- by sdf
            res = ServletUtils.getInfo(true, registUser, "");
            json = ServletUtils.getJsonInfo(res);
            response.getWriter().write(json);
        } else {
            res = ServletUtils.getInfo(false, null, MsgHouseUtils.registerErrorMsg);
            json = ServletUtils.getJsonInfo(res);
            response.getWriter().write(json);
        }


    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request,response);
    }
}
