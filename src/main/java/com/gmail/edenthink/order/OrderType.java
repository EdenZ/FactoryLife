package com.gmail.edenthink.order;

import org.bukkit.inventory.ItemStack;

/**
 * Created by Eden on 2015/12/17.
 */
@SuppressWarnings("deprecation")
public enum  OrderType {
    MATERIALBLOCK(new ItemStack[]{new ItemStack(42, 5), new ItemStack(190, 5), new ItemStack(190, 5, (short) 1)}, 50),
    MONSTERDROP(new ItemStack[]{new ItemStack(367, 40), new ItemStack(352, 40), new ItemStack(289, 30)}, 30);

    private final ItemStack[] items;
    private final double reward;

    OrderType(ItemStack[] items, double reward) {
        this.items = items;
        this.reward = reward;
    }

    public ItemStack[] getItems() {
        return items;
    }

    public double getReward() {
        return reward;
    }
}
