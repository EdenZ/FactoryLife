package com.gmail.edenthink.order;

import com.gmail.edenthink.FactoryLife;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eden on 2015/12/23.
 * Manage order with player
 */
public class OrderManager implements Listener {
    private final FactoryLife plugin;
    private List<OrderData> data = new ArrayList<>();

    public OrderManager(FactoryLife plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

    }
}
