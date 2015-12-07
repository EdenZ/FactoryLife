package com.gmail.edenthink.ticket;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Eden on 2015/12/7.
 */
public class TicketCotroller implements Listener {
    private final List<Integer> ORE_ID = new ArrayList<>(Arrays.asList(14, 15165, 166, 167, 168));

    public boolean checkTicket(String player) {
        // TODO: 2015/12/7
        return false;
    }

    public void consumeTicket(String player) {
        // TODO: 2015/12/7
    }

    @EventHandler
    public void noOrePlaced(BlockPlaceEvent event) {
        if (event.isCancelled()) {
            return;
        }
        // TODO: 2015/12/7
    }

    @EventHandler
    public void ticketFunction(BlockBreakEvent event) {
        // TODO: 2015/12/7
    }
}
