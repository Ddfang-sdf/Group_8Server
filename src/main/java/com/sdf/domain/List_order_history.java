package com.sdf.domain;

import java.util.List;

public class List_order_history {
    private List<HistoricalOrder> historicalOrders;
    private List<Order> orders;

    @Override
    public String toString() {
        return "List_order_history{" +
                "historicalOrders=" + historicalOrders +
                ", orders=" + orders +
                '}';
    }

    public List<HistoricalOrder> getHistoricalOrders() {
        return historicalOrders;
    }

    public void setHistoricalOrders(List<HistoricalOrder> historicalOrders) {
        this.historicalOrders = historicalOrders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
