package com.gmail.edenthink.order;

import com.gmail.edenthink.FactoryLife;

/**
 * Created by Eden on 2015/12/22.
 */
public class OrderGenerator {
    private final FactoryLife plugin;

    public OrderGenerator(FactoryLife plugin) {
        this.plugin = plugin;
    }

    public OrderData newDailyOrder(String receiver) {
        return null;
    }
}
