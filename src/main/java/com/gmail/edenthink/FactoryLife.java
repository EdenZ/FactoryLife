package com.gmail.edenthink;

import com.gmail.edenthink.general.AEGetter;
import com.gmail.edenthink.general.GeneratorCost;
import com.gmail.edenthink.general.InventorySaver;
import com.gmail.edenthink.general.LoginHelper;
import com.gmail.edenthink.order.OrderController;
import com.gmail.edenthink.ticket.TicketController;
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
    private OrderController orderControl;
    public static final boolean DEBUG = false;
    private GeneratorCost generatorCost;
    private TicketController ticketCotrol;
    private InventorySaver saver;

    public OrderController getOrderControl() {
        return orderControl;
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
        Driver.disconnect();
        log.info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));
    }

    @Override
    public void onEnable() {
        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupPermissions();
        setupChat();
        //For first setup
        saveDefaultConfig();
        saveResource("Storage.db", false);
        //Enable main functions
        orderControl = new OrderController(this);
        generatorCost = new GeneratorCost(this);
        ticketCotrol = new TicketController(this);
        saver = new InventorySaver(this);
        new LoginHelper(this);
        getCommand("aeitems").setExecutor(new AEGetter(this));
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
