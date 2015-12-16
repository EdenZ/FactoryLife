package com.gmail.edenthink.black;

import com.gmail.edenthink.FactoryLife;
import com.gmail.edenthink.tools.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Eden on 12/13/2015.
 */
public class Crafter {
    private final FactoryLife plugin;
    private final String MT_PERM = "FactoryLife.crafter.mt";

    public Crafter(FactoryLife plugin) {
        this.plugin = plugin;
        plugin.getCommand("craft").setExecutor(new CraftCommand(this));
    }

    @SuppressWarnings("deprecation")
    public void prepare(Player player, int type, int id) {
        if (type == 4303 && id == 12) {
            if (FactoryLife.getPerms().has(player,MT_PERM)) {
                ItemStack[] itemsNeeded = {new ItemStack(4303, 12, (short) 5), new ItemStack(4228, 4, (short) 1)};
                for (ItemStack item : itemsNeeded) {
                    if (item.getTypeId() == 4228 && !Util.checkItem(player.getInventory(), item.getTypeId(), item.getAmount())) {
                        player.sendMessage("not enough item: "+item.toString());
                        return;
                    }
                    else if (!Util.checkItem(player.getInventory(), item.getTypeId(), item.getDurability(), item.getAmount())) {
                        player.sendMessage("not enough item: "+item.toString());
                        return;
                    }
                }
                for (ItemStack item : itemsNeeded) {
                    if ((item.getTypeId() == 4228)) {
                        Util.removeItems(player.getInventory(), item.getTypeId(), item.getAmount());
                    } else Util.removeItems(player.getInventory(), item.getTypeId(), item.getDurability(), item.getAmount());
                }
                player.sendMessage("In process");
                craftItem(player, new ItemStack(4303, 1, (short) 12), 60 * 5);
            }

        }
    }

    public void craftItem(Player player, ItemStack item, int time) {
        Inventory inventory = player.getInventory();
        Bukkit.getScheduler().runTaskLater(plugin, () -> inventory.addItem(item), time * 20);
    }
}
