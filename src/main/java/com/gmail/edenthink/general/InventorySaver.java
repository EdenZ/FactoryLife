package com.gmail.edenthink.general;

import com.gmail.edenthink.FactoryLife;
import com.gmail.edenthink.tools.DataAccess;
import com.gmail.edenthink.tools.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

/**
 * Save player's inventory when death
 */
public class InventorySaver implements Listener,CommandExecutor {
    private final FactoryLife plugin;
    private DataAccess invData;
    private final String INV_SAVE = "general.inv_save";
    private final String INV_NO_MONEY = "general.inv_no_money";

    public InventorySaver(FactoryLife plugin) {
        this.plugin = plugin;
        invData = new DataAccess(plugin, plugin.getDataFolder().getAbsolutePath(), "invData.yml");
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getCommand("saveinv").setExecutor(this);
        Bukkit.getScheduler().runTaskTimer(plugin, GeneralData::resetRemain, Util.tickToNextSixAM(), 24 * 60 * 60 * 20);
    }

    /**
     * If it is on good condition, player's inventory will be saved
     * @param event death event
     */
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (GeneralData.getRemain(player.getName()) > 0) {
            saveItem(player);
            event.getDrops().clear();
            GeneralData.setRemain(player.getName(), GeneralData.getRemain(player.getName()) - 1);
        } else if (GeneralData.getSwitch(player.getName())) {
            if (FactoryLife.getEcon().withdrawPlayer(player,15).transactionSuccess()){
                saveItem(player);
                event.getDrops().clear();
            } else player.sendMessage(plugin.getLangData().getData().getString(INV_NO_MONEY));
        }
    }

    /**
     * Reset player's inventory
     * @param event respawn event
     */
    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        if (!invData.getData().contains(player.getName())) {
            return;
        }
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            player.getInventory().setContents((ItemStack[]) ((List) invData.getData().get(player.getName() + ".inv")).toArray());
            player.getInventory().setArmorContents((ItemStack[]) ((List) invData.getData().get(player.getName() + ".arm")).toArray());
            event.getPlayer().sendMessage(plugin.getLangData().getData().getString(INV_SAVE));
            invData.getData().set(player.getName(), null);
            invData.saveData();
        }, 30);
    }



    /**
     * Just add a row
     * @param event join event
     */
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        GeneralData.newPlayer(event.getPlayer().getName());
    }

    /**
     * Put inventory on map
     * @param player player
     */
    @SuppressWarnings("deprecation")
    private void saveItem(Player player) {
        invData.getData().set(player.getName() + ".inv", Arrays.asList(player.getInventory().getContents()));
        invData.getData().set(player.getName() + ".arm", Arrays.asList(player.getInventory().getArmorContents()));
        invData.saveData();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        Player player = (Player) commandSender;
        if (command.getName().equalsIgnoreCase("saveinv")) {
            if (strings.length == 1) {
                if (strings[0].equalsIgnoreCase("on")) {
                    GeneralData.setSwitch(player.getName(), 1);
                    return true;
                } else if (strings[0].equalsIgnoreCase("off")) {
                    GeneralData.setSwitch(player.getName(), 0);
                    return true;
                } else if (strings[0].equalsIgnoreCase("info")) {
                    player.sendMessage(String.format("Time Left: %d\nState: %3s", GeneralData.getRemain(player.getName()), GeneralData.getSwitch(player.getName())));
                }
            }
        }
        return false;
    }
}
