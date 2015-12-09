package com.gmail.edenthink.general;

import com.gmail.edenthink.FactoryLife;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Eden on 2015/12/9.
 */
public class InventorySaver implements Listener,CommandExecutor {
    private final FactoryLife plugin;
    private List<Player> players = new LinkedList<>();

    public InventorySaver(FactoryLife plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getCommand("saveinv").setExecutor(this);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (GeneralData.getRemain(player.getName()) > 0) {
            saveItem(player);
            GeneralData.setRemain(player.getName(), GeneralData.getRemain(player.getName()) - 1);
        } else if (GeneralData.getSwitch(player.getName())) {
            if (FactoryLife.getEcon().withdrawPlayer(player,15).transactionSuccess()){
                saveItem(player);
            }
        }
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (players.contains(event.getPlayer())) {
            if (event.getClickedBlock().getTypeId() == 54) {
                setRespawnChest(event.getPlayer(), event.getClickedBlock().getLocation());
            }
        }
    }

    private void saveItem(Player player) {

    }

    public void setRespawnChest(Player player, Location location) {

    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        Player player = (Player) commandSender;
        if (command.getName().equalsIgnoreCase("saverinv")) {
            if (strings.length == 1) {
                //chest
                if (strings[0].equalsIgnoreCase("chest")) {
                    if (players.contains(player)) {
                        return false;
                    }
                    players.add(player);
                    Bukkit.getScheduler().runTaskLater(plugin, () -> {
                        if (players.contains(player)) {
                            players.remove(player);
                        }
                    }, 20 * 10);
                    return true;
                }
            }
        }
        return false;
    }
}
