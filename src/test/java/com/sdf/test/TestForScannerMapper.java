package com.sdf.test;

import com.sdf.domain.ResultInfo;
import com.sdf.domain.Scanner;
import com.sdf.service.ScannerMapperService;
import com.sdf.utils.MsgHouseUtils;
import com.sdf.utils.ServletUtils;
import org.junit.Test;

public class TestForScannerMapper {


    private ScannerMapperService service = new ScannerMapperService();
    private ResultInfo info;
    private String json;
    @Test
    public void testFindForLogin(){
        Scanner scanner = new Scanner();
        scanner.setS_username("赵校来");
        scanner.setS_passwd("123456");
        Scanner forLogin = service.findForLogin(scanner);
        if (forLogin != null){
            info = ServletUtils.getInfo(true,scanner,"");
            json = ServletUtils.getJsonInfo(info);
        }else {
            info = ServletUtils.getInfo(false,null, MsgHouseUtils.loginErrorMsg);
            json = ServletUtils.getJsonInfo(info);
        }
        System.out.println(json);
    }

    @Test
    public void testRealAddressUpdate(){
        String real_time_address = "北京市王府井";
        String order_id = "372036854775811";
        if(service.realAddressUpdate(real_time_address, order_id)){
            info = ServletUtils.getInfo(true,null,"");
            json = ServletUtils.getJsonInfo(info);
        }else {
            info = ServletUtils.getInfo(false,null,MsgHouseUtils.errorMsg);
            json = ServletUtils.getJsonInfo(info);
        }
        System.out.println(json);
    }

    @Test
    public void testSignStatusChange(){
        String order_id = "372036854775809";
        String real_time_address = "河南省开封市金明校区华苑3号楼811室";
        if (service.signStatusChange(order_id,real_time_address)){
            info = ServletUtils.getInfo(true,null,"");
            json = ServletUtils.getJsonInfo(info);
        }else {
            info = ServletUtils.getInfo(false,null,MsgHouseUtils.expressSignErrorMsg);
            json = ServletUtils.getJsonInfo(info);
        }
        System.out.println(json);
    }
}
