package com.gmail.edenthink.tools;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Eden on 2015/12/5.
 */
public class util {
    /**
     * Remove items from inventory
     * @param inventory Where
     * @param id        What
     * @param amount    How much
     */
    @SuppressWarnings("deprecation")
    public static void removeItems(Inventory inventory, int id, int amount) {
        for (ItemStack is : inventory.getContents()) {
            if (is != null && is.getTypeId() == id) {
                int newAmount = is.getAmount() - amount;
                if (newAmount > 0) {
                    is.setAmount(newAmount);
                    return;
                } else {
                    inventory.remove(is);
                    amount = -newAmount;
                    if (amount == 0) return;
                }
            }
        }
    }
}
