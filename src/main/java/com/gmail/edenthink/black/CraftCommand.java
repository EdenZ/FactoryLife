package com.gmail.edenthink.black;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Eden on 12/14/2015.
 */
public class CraftCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        Player player = (Player) commandSender;
        if (command.getName().equalsIgnoreCase("craft")) {
            if (strings.length == 1) {
                if (strings[0].equalsIgnoreCase("mt")) {
                    // TODO: 12/14/2015 Take items and craft new item
                    return true;
                }
            }
        }
        return false;
    }
}
