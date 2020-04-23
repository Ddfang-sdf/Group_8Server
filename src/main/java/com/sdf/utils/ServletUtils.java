package com.sdf.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdf.domain.ResultInfo;

public class ServletUtils {

    private static ResultInfo info = null;
    private static ObjectMapper mapper = null;

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

    /**
     * 数据json序列化工具
     */
    public static String getJsonInfo(Object object){
        mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }



}
