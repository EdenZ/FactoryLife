package com.gmail.edenthink.order;

import com.gmail.edenthink.tools.SQLManager;
import org.bukkit.inventory.ItemStack;

/**
 * Controlling table
 */
public class FactoryOrderManager extends SQLManager {
    public void insertNewOrder(String player, int type, double reward, ItemStack[] itemStacks) {
        // TODO: 2015/12/5
    }

    public void cleanUpOrder() {
        // TODO: 2015/12/5
    }

    public ItemStack[] getItems(String player, int type) {
        ItemStack[] items = new ItemStack[0];
        // TODO: 2015/12/5
        return items;
    }

    public double getReward(String player, int type) {
        // TODO: 2015/12/5
        return 0;
    }

    public void setDone(String player, int type, boolean done) {
        //// TODO: 2015/12/5
    }
}
