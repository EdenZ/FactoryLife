package com.gmail.edenthink.order;

import com.gmail.edenthink.FactoryLife;
import org.bukkit.entity.Player;

/**
 * The controlling class for order system
 */
public class Controller {
    private final FactoryLife plugin;

    public Controller(FactoryLife plugin) {
        this.plugin = plugin;
    }

    public void createNewOrder(Player player) {
        // TODO: 2015/12/5  Create new order every day
    }

    public void cleanOrder() {
        // TODO: 2015/12/5  Clean up all expired orders,and create new orders for those who currently online
    }

    public void processOrder(Player player) {
        // TODO: 2015/12/5  if user success to complete the order, reward user, else tell user failed
    }

    public void joinPlayerOrderCheck(Player player) {
        // TODO: 2015/12/5 Check the player's order, if none, create new orders
    }
}
