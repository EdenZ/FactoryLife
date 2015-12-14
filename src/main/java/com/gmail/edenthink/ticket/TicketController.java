package com.gmail.edenthink.ticket;

import com.gmail.edenthink.FactoryLife;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Main class for ticket system
 */
public class TicketController implements Listener {
    private final FactoryLife plugin;
    private final List<Integer> ORE_ID = new ArrayList<>(Arrays.asList(14, 15, 56, 165, 166, 167, 168));
    private final TicketData data;

    public TicketController(FactoryLife plugin) {
        this.plugin = plugin;
        data = new TicketData();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getCommand("ticket").setExecutor(new TicketCommand(this));
    }

    public TicketData getData() {
        return data;
    }

    /**
     * Check player has ticket
     * @param player who
     * @return true if owning ticket
     */
    public boolean checkTicket(String player) {
        return data.getTicket(player) > 0;
    }

    /**
     * Consuming ticket
     * @param player who
     */
    public void consumeTicket(String player) {
        data.modifyTicket(player, -1);
    }

    /**
     * Cancel player place ore block
     * @param event event
     */
    @SuppressWarnings("deprecation")
    @EventHandler
    public void noOrePlaced(BlockPlaceEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (event.getPlayer().isOp()) {
            return;
        }
        if (ORE_ID.contains(event.getBlock().getTypeId())) {
            event.setCancelled(true);
        }
    }

    /**
     * The main function of ticket
     * @param event event
     */
    @SuppressWarnings("deprecation")
    @EventHandler
    public void ticketFunction(BlockBreakEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (!ORE_ID.contains(event.getBlock().getTypeId())) {
            return;
        }
        String name = event.getPlayer().getName();
        if (checkTicket(name)) {
            consumeTicket(name);
            Location loc = event.getBlock().getLocation();
            boolean isDiamond = event.getBlock().getTypeId() == 54;
            Random ran = new Random();
            for (ItemStack item : event.getBlock().getDrops()) {
                if (isDiamond && ran.nextInt(100) < 69) {
                    continue;
                }
                event.getBlock().getWorld().dropItem(loc, item);
            }
        }
    }

    /**
     * Insert a row for new player
     * @param event event
     */
    @EventHandler
    public void newPlayer(PlayerJoinEvent event) {
        data.newPlayer(event.getPlayer().getName());
    }
}
