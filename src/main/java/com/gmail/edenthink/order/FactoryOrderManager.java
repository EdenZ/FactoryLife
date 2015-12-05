package com.gmail.edenthink.order;

import com.gmail.edenthink.tools.SQLManager;

/**
 * Controlling table
 */
public class FactoryOrderManager extends SQLManager {
    final String PLAYER = "player";
    final String TABLE = "factory_order";
    final String ORDER_ONE = "order_one";
    final String ORDER_TWO = "order_two";
    final String ORDER_THREE = "order_three";

    public void insertNewOrder(String player) {
        // TODO: 2015/12/5
        String sql = String.format("INSERT INTO %s (%s) VALUES (\"%s\");", TABLE, PLAYER, player);
        executeSQL(sql);
    }

    public boolean orderTimesReduce(String player, int orderNo) {
        // TODO: 2015/12/5
        return false;
    }

    public int getRemain(String player, int orderNo) {
        // TODO: 2015/12/5
        return 0;
    }

    public void resetOrderTimes() {
        // TODO: 2015/12/5
    }

    public void resetOrderTimes(String player) {
        // TODO: 2015/12/5
    }
}
