package com.gmail.edenthink.order;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Eden on 2015/12/6.
 */
public class OrderCommand implements CommandExecutor {
    private final Controller controller;

    public OrderCommand(Controller controller) {
        this.controller = controller;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) return false;
        if (command.getName().equalsIgnoreCase("order")) {
            if (strings.length != 1) return false;
            //order info
            if (strings[0].equalsIgnoreCase("info")) {
                // TODO: 2015/12/6 Show the state of orders
                return true;
            } else if (strings[0].equalsIgnoreCase("one")) {
                controller.processOrder((Player) commandSender, 1);
                return true;
            } else if (strings[0].equalsIgnoreCase("two")) {
                controller.processOrder((Player) commandSender, 2);
                return true;
            } else if (strings[0].equalsIgnoreCase("three")) {
                controller.processOrder((Player) commandSender, 2);
                return true;
            }
        }
        return false;
    }
}
