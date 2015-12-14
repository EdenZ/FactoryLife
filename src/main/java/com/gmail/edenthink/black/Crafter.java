package com.gmail.edenthink.black;

import com.gmail.edenthink.FactoryLife;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Eden on 12/13/2015.
 */
public class Crafter {
    private final FactoryLife plugin;


    public Crafter(FactoryLife plugin) {
        this.plugin = plugin;
    }

    @SuppressWarnings("deprecation")
    public void prepare(Player player, int type) {
        if (type == 0) {
            if (false/*Check permission and items required*/) {

            }
            craftItem(player, new ItemStack(5, 5), 60 * 5);
        }
    }

    public void craftItem(Player player, ItemStack item, int time) {
        // TODO: 12/13/2015 Add item to player's inventory after time
    }
}
