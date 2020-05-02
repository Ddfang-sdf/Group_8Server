package com.sdf.web.servlet;

import com.sdf.domain.ResultInfo;
import com.sdf.service.ScannerMapperService;
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
 * 扫描员修改实时地址，请求中包含参数 order_id real_time_address
 */
@WebServlet("/ChangeAddressServlet")
public class ChangeAddressServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //创建业务层对象
        ScannerMapperService service = new ScannerMapperService();
        //创建结果集对象
        ResultInfo info = null;
        //响应数据
        String json = null;
        //获取用户数据
        String order_id = req.getParameter("order_id");
        String real_time_address = req.getParameter("real_time_address");
        if (service.realAddressUpdate(real_time_address, order_id)){
            //修改成功
            info = ServletUtils.getInfo(true,null,"");
            json = ServletUtils.getJsonInfo(info);
            resp.getWriter().write(json);
        }else {
            //修改失败
            info = ServletUtils.getInfo(false,null, MsgHouseUtils.changeErrorMsg);
            json = ServletUtils.getJsonInfo(info);
            resp.getWriter().write(json);
        }
    }
}
