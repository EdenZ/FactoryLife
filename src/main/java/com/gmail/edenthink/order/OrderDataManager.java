package com.gmail.edenthink.order;

/**
 * Controlling database
 */
public class OrderDataManager {
    final String PLAYER = "player";
    final String TABLE = "factory_order";
    final String ORDER_ONE = "order_one_times";
    final String ORDER_TWO = "order_two_times";
    final String ORDER_THREE = "order_three_times";

    public void insertNewOrder(String player) {
    }

    public boolean orderTimesReduce(String player, String orderNo) {
        return false;
    }

    /**
     *
     * @param player who
     * @param orderNo what
     * @return number
     */
    public int getRemain(String player, String orderNo) {
        return 0;
    }

    public void resetOrderTimes() {
    }
}
