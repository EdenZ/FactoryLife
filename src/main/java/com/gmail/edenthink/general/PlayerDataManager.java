package com.gmail.edenthink.general;

import com.gmail.edenthink.FactoryLife;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Managing player data
 */
public class PlayerDataManager implements Listener {
    private final FactoryLife plugin;
    private List<PlayerData> data = new ArrayList<>();

    public PlayerDataManager(FactoryLife plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        dataSaveSchedule();
    }

    private void dataSaveSchedule() {

    }

    public PlayerData getPlayerData(Player player) {
        return null;
    }

    public List<PlayerData> getData() {
        return data;
    }

    @EventHandler
    public void newPlayer(PlayerJoinEvent event) {

    }

    @EventHandler
    public void playerQuit(PlayerQuitEvent event) {

    }
}
