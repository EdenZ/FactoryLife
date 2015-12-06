package com.gmail.edenthink.order;

import org.bukkit.scheduler.BukkitRunnable;

/**
 * Task manager
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
