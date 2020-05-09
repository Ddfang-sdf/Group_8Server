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

//修改个人信息Servlet
@WebServlet("/saveUserInfoServlet")
public class SaveUserInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        /*
         * 修改用户信息的时候需要响应新的用户数据，但是一开始我写的底层返回的是Boolean。
         * 此处修改为从底层查询。
         *      ---- by sdf
         */


        //创建业务层对象
        UserMapperService service = new UserMapperService();
        //创建结果集对象
        ResultInfo res = null;
        //创建响应数据
        String json = null;
        //创建用户对象
        User user = new User();
        //获取请求参数
        Map<String, String[]> map = request.getParameterMap();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        User userInfo = service.saveUserInfo(user);
        //修改
        if(userInfo != null){
            //修改成功，不需要给ErrorMsg ---- by sdf
            res = ServletUtils.getInfo(true,userInfo,"");
            json = ServletUtils.getJsonInfo(res);
            response.getWriter().write(json);
        }else{
            res = ServletUtils.getInfo(false,null,MsgHouseUtils.changeErrorMsg);
            json = ServletUtils.getJsonInfo(res);
            response.getWriter().write(json);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request,response);
    }
}
