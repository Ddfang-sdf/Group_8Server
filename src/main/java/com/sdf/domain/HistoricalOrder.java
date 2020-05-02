package com.sdf.domain;

import java.io.Serializable;
import java.util.List;

public class HistoricalOrder implements Serializable {
    private Integer id;
    private Integer uid;
    private String order_id;

    @Override
    public String toString() {
        return "HistoricalOrder{" +
                "id=" + id +
                ", uid=" + uid +
                ", order_id='" + order_id + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
}
