package com.gmail.edenthink.order;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * /order Command
 */
public class OrderCommand implements CommandExecutor {
    private final OrderController orderController;

    public OrderCommand(OrderController orderController) {
        this.orderController = orderController;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) return false;
        if (command.getName().equalsIgnoreCase("order")) {
            if (strings.length != 1) return false;
            if (strings[0].equalsIgnoreCase("info")) {
                return true;
            }
            if (strings[0].equalsIgnoreCase("one")) {
                return true;
            }
        }
        return false;
    }
}
