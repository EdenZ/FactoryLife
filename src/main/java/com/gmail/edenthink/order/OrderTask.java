package com.gmail.edenthink.order;

import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Eden on 2015/12/6.
 */
public class OrderTask {
    private OrderController orderController;

    public OrderTask(OrderController orderController) {
        this.orderController = orderController;
        new ResetOrderTask().runTaskTimer(orderController.getPlugin(), 20 * 60 * 60, 20 * 60 * 60 * 24);
    }

    class ResetOrderTask extends BukkitRunnable {

        @Override
        public void run() {
            orderController.resetOrder();
        }
    }
}
