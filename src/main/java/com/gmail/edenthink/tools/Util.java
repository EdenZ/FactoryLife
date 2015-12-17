package com.gmail.edenthink.tools;

import com.gmail.edenthink.FactoryLife;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @SuppressWarnings({"deprecation", "Duplicates"})
    public static void removeItem(Inventory inventory, int id, int data, int amount) {
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

    @SuppressWarnings({"deprecation", "Duplicates"})
    public static void removeItem(Inventory inventory, int id, int amount) {
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

    /**
     * Check inventory contains enough items
     * @param inventory where
     * @param id        what
     * @param data      data
     * @param amount    how much
     * @return whether or not this inventory has enough items
     */
    @SuppressWarnings({"deprecation", "Duplicates"})
    public static boolean hasItem(Inventory inventory, int id, int data, int amount) {
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

    @SuppressWarnings({"deprecation", "Duplicates"})
    public static boolean hasItem(Inventory inventory, int id, int amount) {
        for (ItemStack is : inventory.getContents()) {
            if (is != null && is.getTypeId() == id) {
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

    @SuppressWarnings("deprecation")
    public static List<ItemStack> removeItems(Inventory inv, ItemStack[] items, List<Integer> noMetaIDs) {
        List<ItemStack> lack = new ArrayList<>();
        for (ItemStack item : items) {
            if (noMetaIDs.contains(item.getTypeId())) {
                if (!hasItem(inv, item.getTypeId(), item.getAmount())) {
                    lack.add(item);
                }
            } else {
                if (!hasItem(inv, item.getTypeId(), item.getDurability(), item.getAmount())) {
                    lack.add(item);
                }
            }
        }
        if (!lack.isEmpty()) {
            return lack;
        }
        for (ItemStack item : items) {
            if (noMetaIDs.contains(item.getTypeId())) {
                removeItem(inv, item.getTypeId(), item.getAmount());
            } else {
                removeItem(inv, item.getTypeId(), item.getDurability(), item.getAmount());
            }
        }
        return lack;
    }

    @SuppressWarnings("deprecation")
    public static List<ItemStack> removeItems(Inventory inv, ItemStack[] items) {
        List<ItemStack> lack = new ArrayList<>();
        for (ItemStack item : items) {
                if (!hasItem(inv, item.getTypeId(), item.getAmount())) {
                    lack.add(item);
            }
        }
        if (!lack.isEmpty()) {
            return lack;
        }
        for (ItemStack item : items) {
                removeItem(inv, item.getTypeId(), item.getDurability(), item.getAmount());

        }
        return lack;
    }

    /**
     * Print error
     * @param e exception
     * @return error format
     */
    public static void printSQLError(SQLException e) {
        FactoryLife.getLog().info(String.format("SQL message(%3d): %s", e.getErrorCode(), e.getMessage()));
    }

    /**
     * Withdraw amount money from player
     * @param player who
     * @param amount how much
     * @return success or not
     */
    public static boolean withdraw(Player player, int amount) {
        return FactoryLife.getEcon().withdrawPlayer(player, amount).transactionSuccess();
    }

    /**
     * Find out the tick left to 6 am
     * @return tick left
     */
    public static int tickToNextSixAM() {
        LocalDateTime time = LocalDateTime.now();
        int h = time.getHour();
        int timeleft = 0;
        if (h > 6) {
            timeleft += (30 - h) * 60 * 60 * 20;
        } else timeleft += (6 - h) * 60 * 60 * 20;
        return timeleft - time.getMinute() * 60 * 20 - time.getSecond() * 20;
    }
}
