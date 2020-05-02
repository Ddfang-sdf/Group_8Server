package com.sdf.dao;

import com.sdf.domain.Scanner;
import org.apache.ibatis.annotations.Param;

public interface ScannerMapper {
    /**
     * 扫描员登陆
     * @param scanner
     * @return
     */
    Scanner findForLogin(@Param("scanner") Scanner scanner);

    /**
     * 更改实时地址
     * @param real_time_address
     * @param order_id
     * @return
     */
    boolean realAddressUpdate(@Param("address") String real_time_address, @Param("order_id") String order_id);

    /**
     * 更新签收状态
     * @param order_id
     * @param real_time_address
     * @return
     */
    boolean signStatusChange(@Param("order_id") String order_id, @Param("address") String real_time_address);
}
