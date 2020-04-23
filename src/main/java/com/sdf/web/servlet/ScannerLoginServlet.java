package com.sdf.web.servlet;

import com.sdf.domain.ResultInfo;
import com.sdf.domain.Scanner;
import com.sdf.service.UserService;
import com.sdf.service.impl.UserServiceImpl;
import com.sdf.utils.ServletUtils;
import com.sdf.utils.MsgHouseUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 扫描员登陆功能
 */
@WebServlet("/ScannerLoginServlet")
public class ScannerLoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //设置相应编码格式
        resp.setContentType("application/json;charset=utf-8");
        //获取扫描员用户名密码
        Map<String, String[]> map = req.getParameterMap();
        //创建业务层对象
        UserService service = new UserServiceImpl();
        //创建结果集对象
        ResultInfo info = null;
        //创建响应json
        String json = null;
        //封装scanner对象
        Scanner scanner = new Scanner();
        try {
            BeanUtils.populate(scanner,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        Scanner scannerLogin = service.ScannerLogin(scanner);
        if (scannerLogin == null){
            //底层查询结果位null，则登录失败
            info = ServletUtils.getInfo(false,null, MsgHouseUtils.loginErrorMsg);
            json = ServletUtils.getJsonInfo(info);
            resp.getWriter().write(json);
            return;
        }
        info = ServletUtils.getInfo(true,scanner,"");
        json = ServletUtils.getJsonInfo(info);
        resp.getWriter().write(json);
    }
}
