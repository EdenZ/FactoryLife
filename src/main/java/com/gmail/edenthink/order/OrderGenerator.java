package com.gmail.edenthink.order;

import com.gmail.edenthink.FactoryLife;

/**
 * Created by Eden on 2015/12/22.
 * Generate new order
 */
public class OrderGenerator {
    private final FactoryLife plugin;

    public OrderGenerator(FactoryLife plugin) {
        this.plugin = plugin;
    }

    public OrderData newDailyOrder(String receiver) {
        OrderData data = new OrderData();
        data.setType(OrderType.DAILY_ORDER);

        return data;
    }
}
