package com.gmail.edenthink.order;

import org.bukkit.entity.Player;

/**
 * Created by Eden on 2015/12/5.
 */
public class Controller {
    public static void createNewOrder(Player player) {
        // TODO: 2015/12/5  Create new order every day
    }

    public static void cleanOrder() {
        // TODO: 2015/12/5  Clean up all expired orders,and create new orders for those who currently online
    }

    public static void processOrder(Player player) {
        // TODO: 2015/12/5  if user success to complete the order, reward user, else tell user failed
    }

    public static void joinPlayerOrderCheck(Player player) {
        // TODO: 2015/12/5 Check the player's order, if none, create new orders
    }
}
