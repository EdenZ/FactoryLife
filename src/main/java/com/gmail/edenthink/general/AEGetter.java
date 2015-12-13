package com.gmail.edenthink.general;

import com.gmail.edenthink.FactoryLife;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

/**
 * /aeitems
 */
public class AEGetter implements CommandExecutor {
    private final FactoryLife plugin;
    private final String PERM = "FactoryLife.ae";
    private final String AE = "general.ae_item";
    private final String AE_GET = "general.ae_item_gat";

    public AEGetter(FactoryLife plugin) {
        this.plugin = plugin;

    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        if (command.getName().equalsIgnoreCase("aeitems")) {
            if (! FactoryLife.getPerms().has(commandSender, PERM)) {
                Random ran = new Random();
                int a = ran.nextInt(4);
                switch (a) {
                    case 0:
                        ((Player) commandSender).getInventory().addItem(new ItemStack(4340, 1, (short) 13));
                        break;
                    case 1:
                        ((Player) commandSender).getInventory().addItem(new ItemStack(4340, 1, (short) 14));
                        break;
                    case 2:
                        ((Player) commandSender).getInventory().addItem(new ItemStack(4340, 1, (short) 15));
                        break;
                    case 3:
                        ((Player) commandSender).getInventory().addItem(new ItemStack(4340, 1, (short) 19));
                        break;
                }
                FactoryLife.getPerms().playerAdd((Player) commandSender, PERM);
                commandSender.sendMessage(plugin.getLangData().getData().getString(AE));
            } else commandSender.sendMessage(plugin.getLangData().getData().getString(AE_GET));
            return true;
        }
        return false;
    }
}
