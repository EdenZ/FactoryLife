package com.gmail.edenthink.general;

import com.gmail.edenthink.FactoryLife;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import static com.gmail.edenthink.tools.Util.withdraw;

/**
 * Place generator cost money
 */
public class GeneratorCost implements Listener {
    private final FactoryLife plugin;
    private final String NORMAL = "general.generator.normal";
    private final String GEO = "general.generator.geo";
    private final String NUCLEAR = "general.generator.nuclear";
    // TODO: 12/13/2015 Other generators here


    public GeneratorCost(FactoryLife plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void placeGenerator(BlockPlaceEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (event.getBlock().getTypeId() != 194 && event.getBlock().getTypeId() != 232 && event.getBlock().getTypeId() == 229) {
            return;
        }
        if (event.getPlayer().isOp()) {
            return;
        }
        int id = event.getBlock().getTypeId(), data = event.getBlock().getData();
        int cost = 0;
        if (id == 194) {
            if (data == 0) {
                cost = 30;
            } else if (data == 1) {
                cost = 50;
            } else if (data == 5) {

                cost = 300;
            } else if (data == 2 || data == 3 || data == 4) {

                cost = 5;
            } else if (data == 9) {
                cost = 120;
            } else {
                cost = 70;
            }
        } else if (id == 232) {
            if (data == 0) {
                cost = 70;
            }else if (data == 1) {
                cost = 200;
            }else if (data == 2) {
                cost = 1000;
            }else if (data == 3) {
                cost = 10000;
            }
        } else if (id == 229) {
            cost = 7;
        }
        if (cost == 0) {
            return;
        }
        if (!withdraw(event.getPlayer(), cost)) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(String.format(plugin.getLangData().getData().getString(NORMAL), cost));
        }
    }
}
