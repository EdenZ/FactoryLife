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
public class Controller implements Listener{
    private final FactoryLife plugin;
    private final FactoryOrderManager manager;

    public Controller(FactoryLife plugin) {
        this.plugin = plugin;
        manager = new FactoryOrderManager();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getCommand("order").setExecutor(new OrderCommand(this));
    }

    /**
     * Reset for all player
     */
    public void resetOrder() {
        manager.resetOrderTimes();
    }

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

    @SuppressWarnings("deprecation")
    private void processStep(Player player, String orderType, ItemStack[] items, double reward) {
        for (ItemStack item : items) {
            if (!Util.checkItem(player.getInventory(), item.getTypeId(), item.getDurability(), item.getAmount())) {
                return;
            }
        }
        if (manager.orderTimesReduce(player.getName(), orderType)) {
            for (ItemStack item : items) {
                Util.removeItems(player.getInventory(), item.getTypeId(), item.getDurability(), item.getAmount());
            }
            FactoryLife.getEcon().depositPlayer(player, reward);
        }
    }

    @EventHandler
    public void joinPlayerOrderCheck(PlayerJoinEvent event) {
        manager.insertNewOrder(event.getPlayer().getName());
    }
}
