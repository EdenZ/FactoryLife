package com.gmail.edenthink.order;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Eden on 2015/12/6.
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
            //order info
            if (strings[0].equalsIgnoreCase("info")) {
                // TODO: 2015/12/6 Show the state of orders
                return true;
            } else if (strings[0].equalsIgnoreCase("one")) {
                orderController.processOrder((Player) commandSender, 1);
                commandSender.sendMessage("Order completed");
                return true;
            } else if (strings[0].equalsIgnoreCase("two")) {
                orderController.processOrder((Player) commandSender, 2);
                commandSender.sendMessage("Order completed");
                return true;
            } else if (strings[0].equalsIgnoreCase("three")) {
                orderController.processOrder((Player) commandSender, 2);
                commandSender.sendMessage("Order completed");
                return true;
            }
        }
        return false;
    }
}
