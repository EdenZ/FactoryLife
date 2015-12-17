package com.gmail.edenthink.order;

import com.gmail.edenthink.FactoryLife;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * The controlling class for order system
 */
public class OrderController implements Listener{
    private final FactoryLife plugin;
    private final OrderDataManager manager;
    private final OrderTask orderTask;
    private final String COMPLETE = "order.complete";
    private final String NO_ORDER = "order.no_order";

    public OrderController(FactoryLife plugin) {
        this.plugin = plugin;
        manager = new OrderDataManager();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getCommand("order").setExecutor(new OrderCommand(this));
        orderTask = new OrderTask(this);
    }

    public FactoryLife getPlugin() {
        return plugin;
    }

    public OrderDataManager getManager() {
        return manager;
    }

    /**
     * Reset for all player
     */
    public void resetOrder() {
        manager.resetOrderTimes();
    }

    /**
     * Insert new row for player
     * @param event event
     */
    @EventHandler
    public void joinPlayerOrderCheck(PlayerJoinEvent event) {
        manager.insertNewOrder(event.getPlayer().getName());
    }
}
