package com.gmail.edenthink.order;

import com.gmail.edenthink.FactoryLife;
import com.gmail.edenthink.tools.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

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
     * Called when player try to finish an order
     * @param player who
     * @param orderNo which order
     */
    @SuppressWarnings("deprecation")
    public void processOrder(Player player, int orderNo) {
        if (orderNo == 1) {
            processStep(player, manager.ORDER_ONE, new ItemStack[]{new ItemStack(42, 5), new ItemStack(190, 5), new ItemStack(190, 5, (short) 1)}, 70);
        } else if (orderNo == 2) {
            processStep(player, manager.ORDER_TWO, new ItemStack[]{new ItemStack(367, 40), new ItemStack(352, 40), new ItemStack(289, 30)}, 50);
        } else if (orderNo == 3) {
            processStep(player, manager.ORDER_THREE, new ItemStack[]{new ItemStack(264, 5), new ItemStack(368, 8)}, 130);
        }
    }

    /**
     * See walther or not able to complete order
     * @param player who
     * @param orderType which
     * @param items what is needed
     * @param reward the reward
     */
    @SuppressWarnings("deprecation")
    private void processStep(Player player, String orderType, ItemStack[] items, double reward) {
        for (ItemStack item : items) {
            if (!Util.checkItem(player.getInventory(), item.getTypeId(), item.getDurability(), item.getAmount())) {
                player.sendMessage("not enough item: "+item.toString());
                return;
            }
            if (FactoryLife.DEBUG) {
                plugin.getLogger().info("Item checked");
            }
        }
        if (manager.orderTimesReduce(player.getName(), orderType)) {
            for (ItemStack item : items) {
                Util.removeItems(player.getInventory(), item.getTypeId(), item.getDurability(), item.getAmount());
            }
            if (FactoryLife.DEBUG) {
                plugin.getLogger().info("Item removed");
            }
            FactoryLife.getEcon().depositPlayer(player, reward);
            player.sendMessage(plugin.getLangData().getData().getString(COMPLETE));
        } else player.sendMessage(plugin.getLangData().getData().getString(NO_ORDER));
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
