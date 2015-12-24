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

    /**
     * Save all player data every 5 minutes
     */
    private void dataSaveSchedule() {

    }

    /**
     * Save all player date
     */
    public void saveAllData() {
        data.forEach(PlayerData::save);
    }

    /**
     * Get the player data by name
     * @param player Online player
     * @return player data. Null if not found
     */
    public PlayerData getPlayerData(Player player) {
        for (PlayerData d : data) {
            if (player.getName().equals(d.getName())) {
                return d;
            }
        }
        return null;
    }

    public List<PlayerData> getData() {
        return data;
    }

    /**
     * Reload data from database
     * @param event Player join
     */
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

    }

    /**
     * Save data when player quit and drop data form the list
     * @param event Player quit
     */
    @EventHandler
    public void playerQuit(PlayerQuitEvent event) {

    }
}
