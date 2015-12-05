package com.gmail.edenthink.order;

import com.gmail.edenthink.FactoryLife;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

/**
 * The controlling class for order system
 */
public class Controller implements Listener{
    private final FactoryLife plugin;

    public Controller(FactoryLife plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public void resetOrder(String player) {
        // TODO: 2015/12/5
    }

    public void processOrder(Player player) {
        // TODO: 2015/12/5  if user success to complete the order, reward user, else tell user failed
    }

    public void joinPlayerOrderCheck(Player player) {
        // TODO: 2015/12/5 Check the player's order, if none, create new orders
    }
}
