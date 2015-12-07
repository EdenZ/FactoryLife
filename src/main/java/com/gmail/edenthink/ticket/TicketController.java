package com.gmail.edenthink.ticket;

import com.gmail.edenthink.FactoryLife;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Eden on 2015/12/7.
 */
public class TicketController implements Listener {
    private final FactoryLife plugin;
    private final List<Integer> ORE_ID = new ArrayList<>(Arrays.asList(14, 15165, 166, 167, 168));
    private final TicketData data;

    public TicketController(FactoryLife plugin) {
        this.plugin = plugin;
        data = new TicketData();
    }

    public boolean checkTicket(String player) {
        return data.getTicket(player) > 0;
    }

    public void consumeTicket(String player) {
        data.modifyTicket(player, -1);
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void noOrePlaced(BlockPlaceEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (ORE_ID.contains(event.getBlock().getTypeId())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void ticketFunction(BlockBreakEvent event) {
        if (event.isCancelled()) {
            return;
        }
        String name = event.getPlayer().getName();
        if (checkTicket(name)) {
            consumeTicket(name);
            Location loc = event.getBlock().getLocation();
            for (ItemStack item : event.getBlock().getDrops()) {
                event.getBlock().getWorld().dropItem(loc, item);
            }
        }
    }
}
