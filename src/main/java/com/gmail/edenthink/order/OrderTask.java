package com.gmail.edenthink.order;

import com.gmail.edenthink.tools.Util;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Task manager
 */
public class OrderTask {
    private OrderController orderController;

    public OrderTask(OrderController orderController) {
        this.orderController = orderController;
        new ResetOrderTask().runTaskTimer(orderController.getPlugin(), Util.tickToNextSixAM(), 20 * 60 * 60 * 24);
    }

    class ResetOrderTask extends BukkitRunnable {

        @Override
        public void run() {
            orderController.resetOrder();
            Bukkit.getServer().broadcastMessage("Making new order");
        }
    }
}
