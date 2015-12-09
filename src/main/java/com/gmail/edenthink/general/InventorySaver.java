package com.gmail.edenthink.general;

import com.gmail.edenthink.FactoryLife;
import com.gmail.edenthink.tools.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eden on 2015/12/9.
 */
public class InventorySaver implements Listener,CommandExecutor {
    private final FactoryLife plugin;
    private Map<Player, ItemStack[]> playerMap = new HashMap<>();

    public InventorySaver(FactoryLife plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getCommand("saveinv").setExecutor(this);
        Bukkit.getScheduler().runTaskTimer(plugin, GeneralData::resetRemain, Util.tickToNextSixAM(), 24 * 60 * 60 * 20);
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

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            event.getPlayer().getInventory().setContents(playerMap.get(event.getPlayer()));
            playerMap.remove(event.getPlayer());
        }, 30);
    }

    @SuppressWarnings("deprecation")
    private void saveItem(Player player) {
        playerMap.put(player, player.getInventory().getContents());
        player.getInventory().clear();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        Player player = (Player) commandSender;
        if (command.getName().equalsIgnoreCase("saverinv")) {
            if (strings.length == 1) {
                if (strings[0].equalsIgnoreCase("on")) {
                    GeneralData.setSwitch(player.getName(), true);
                    return true;
                } else if (strings[0].equalsIgnoreCase("off")) {
                    GeneralData.setSwitch(player.getName(), false);
                    return true;
                } else if (strings[0].equalsIgnoreCase("info")) {
                    player.sendMessage(String.format("Time Left: %d\nState: %3s", GeneralData.getRemain(player.getName()), GeneralData.getSwitch(player.getName())));
                }
            }
        }
        return false;
    }
}
