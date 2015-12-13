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
        if (id == 194) {
            if (data == 0) {
                if (!withdraw(event.getPlayer(), 30)) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(plugin.getLangData().getData().getString(NORMAL));
                }
            } else if (data == 1) {
                if (!withdraw(event.getPlayer(), 50)) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(plugin.getLangData().getData().getString(NORMAL));
                }
            } else if (data == 5) {
                if (!withdraw(event.getPlayer(), 300)) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(plugin.getLangData().getData().getString(NORMAL));
                }
            } else if (data == 2 || data == 3 || data == 4) {
                if (!withdraw(event.getPlayer(), 5)) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(plugin.getLangData().getData().getString(NORMAL));
                }
            } else if (data == 9) {
                if (!withdraw(event.getPlayer(), 120)) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(plugin.getLangData().getData().getString(NORMAL));
                }
            } else {
                if (!withdraw(event.getPlayer(), 70)) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(plugin.getLangData().getData().getString(NORMAL));
                }
            }
        } else if (id == 232) {
            if (data == 0) {
                if (!withdraw(event.getPlayer(), 70)) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(plugin.getLangData().getData().getString(NORMAL));
                }
            }else if (data == 1) {
                if (!withdraw(event.getPlayer(), 200)) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(plugin.getLangData().getData().getString(NORMAL));
                }
            }else if (data == 2) {
                if (!withdraw(event.getPlayer(), 1000)) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(plugin.getLangData().getData().getString(NORMAL));
                }
            }else if (data == 3) {
                if (!withdraw(event.getPlayer(), 10000)) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(plugin.getLangData().getData().getString(NORMAL));
                }
            }
        } else if (id == 229) {
            if (!withdraw(event.getPlayer(), 7)) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(plugin.getLangData().getData().getString(NORMAL));
            }
        }
    }
}
