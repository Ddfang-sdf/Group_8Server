package com.sdf.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdf.domain.ResultInfo;

public class ServletUtils {
    private ObjectMapper mapper = new ObjectMapper();
    private static ResultInfo info = null;

    /**
     * 设置响应数据对象
     * @param flag
     * @param data
     * @param errorMsg
     */
    public static ResultInfo getInfo(boolean flag ,Object data ,String errorMsg){
        info = new ResultInfo();
        info.setFlag(flag);
        info.setData(data);
        info.setErrorMsg(errorMsg);
        return info;
    }


}
