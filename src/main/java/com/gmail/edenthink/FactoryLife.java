package com.gmail.edenthink;

import com.gmail.edenthink.general.PlayerDataManager;
import com.gmail.edenthink.tools.DataAccess;
import com.gmail.edenthink.tools.Driver;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * Main class
 */
public class FactoryLife extends JavaPlugin {
    private static final Logger log = Logger.getLogger("Minecraft");
    public static Economy econ = null;
    public static Permission perms = null;
    public static Chat chat = null;
    private DataAccess langData;
    private PlayerDataManager playerDataManager;

    public DataAccess getLangData() {
        return langData;
    }

    public static Economy getEcon() {
        return econ;
    }

    public static Permission getPerms() {
        return perms;
    }

    public static Chat getChat() {
        return chat;
    }

    public static Logger getLog() {
        return log;
    }

    @Override
    public void onDisable() {
        //Save data
        playerDataManager.saveAllData();
        //Disconnect database
        Driver.disconnect();
        log.info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));
    }

    @Override
    public void onEnable() {
        //For Vault
        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupPermissions();
        setupChat();
        //For first setup
        saveDefaultConfig();
        langData = new DataAccess(this, getDataFolder().getAbsolutePath(), "lang.yml");
        langData.saveDefault();
        saveResource("Data.db", false);
        //Enable main functions
        playerDataManager = new PlayerDataManager(this);
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }
}
