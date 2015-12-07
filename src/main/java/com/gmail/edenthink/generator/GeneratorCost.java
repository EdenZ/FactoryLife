package com.gmail.edenthink.generator;

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
        int id = event.getBlock().getTypeId(), data = event.getBlock().getData();
        if (id == 194) {
            if (data == 0) {
                if (!withdraw(event.getPlayer(), 30)) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage("You need H30.0 to place this.");
                }
            } else if (data == 1) {
                if (!withdraw(event.getPlayer(), 50)) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage("You need H50.0 to place this.");
                }
            } else if (data == 5) {
                if (!withdraw(event.getPlayer(), 300)) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage("You need H300.0 to place this.");
                }
            } else if (data == 2 || data == 3 || data == 4) {
                if (!withdraw(event.getPlayer(), 5)) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage("You need H5.0 to place this.");
                }
            } else if (data == 9) {
                if (!withdraw(event.getPlayer(), 120)) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage("You need H120.0 to place this.");
                }
            } else {
                if (!withdraw(event.getPlayer(), 70)) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage("You need H70.0 to place this.");
                }
            }
        } else if (id == 232) {
            if (data == 0) {
                if (!withdraw(event.getPlayer(), 70)) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage("You need H70.0 to place this.");
                }
            }else if (data == 1) {
                if (!withdraw(event.getPlayer(), 200)) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage("You need H200.0 to place this.");
                }
            }else if (data == 2) {
                if (!withdraw(event.getPlayer(), 500)) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage("You need H500.0 to place this.");
                }
            }else if (data == 3) {
                if (!withdraw(event.getPlayer(), 1000)) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage("You need H1000.0 to place this.");
                }
            }
        } else if (id == 229) {
            if (!withdraw(event.getPlayer(), 7)) {
                event.setCancelled(true);
                event.getPlayer().sendMessage("You need H7.0 to place this.");
            }
        }
    }
}
