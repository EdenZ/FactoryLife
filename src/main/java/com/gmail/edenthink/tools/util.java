package com.gmail.edenthink.tools;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;

/**
 * Created by Eden on 2015/12/5.
 */
public class Util {
    /**
     * Remove items from inventory
     *
     * @param inventory Where
     * @param id        What
     * @param data      Meta
     * @param amount    How much
     */
    @SuppressWarnings("deprecation")
    public static void removeItems(Inventory inventory, int id, int data, int amount) {
        for (ItemStack is : inventory.getContents()) {
            if (is != null && is.getTypeId() == id && is.getData().getData() == data) {
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

    /**
     * Check inventory contains enough items
     * @param inventory where
     * @param id        what
     * @param data      data
     * @param amount    how much
     * @return whether or not this inventory has enough items
     */
    public static boolean checkItem(Inventory inventory, int id, int data, int amount) {
        for (ItemStack is : inventory.getContents()) {
            if (is != null && is.getTypeId() == id && is.getData().getData() == data) {
                int newAmount = is.getAmount() - amount;
                if (newAmount > 0) {
                    return true;
                } else {
                    amount = -newAmount;
                    if (amount == 0) return true;
                }
            }
        }
        return amount == 0;
    }

    public static String printSQLError(SQLException e) {
        return String.format("SQL message(%3d): %s", e.getErrorCode(), e.getMessage());
    }
}
