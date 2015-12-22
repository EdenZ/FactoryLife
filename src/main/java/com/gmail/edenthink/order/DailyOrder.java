package com.gmail.edenthink.order;

import org.bukkit.inventory.ItemStack;

/**
 * Created by Eden on 2015/12/22.
 * Enum of daily order information
 */
@SuppressWarnings("deprecation")
public enum DailyOrder {
    ONE(new ItemStack[]{new ItemStack(4241, 12)}, 65), /*Industrial Diamond*/
    TWO(new ItemStack[]{new ItemStack(4125, 32)}, 65),/*Adv material*/
    THREE(new ItemStack[]{new ItemStack(4190, 1)}, 65);/*Blue diamond= =*/

    private ItemStack[] items;
    private double reward;

    DailyOrder(ItemStack[] items, double reward) {
        this.items = items;
        this.reward = reward;
    }
}