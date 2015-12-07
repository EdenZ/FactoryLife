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
            //order info
            if (strings[0].equalsIgnoreCase("info")) {
                int one = orderController.getManager().getRemain(commandSender.getName(),orderController.getManager().ORDER_ONE);
                int two = orderController.getManager().getRemain(commandSender.getName(),orderController.getManager().ORDER_TWO);
                int three = orderController.getManager().getRemain(commandSender.getName(),orderController.getManager().ORDER_THREE);
                commandSender.sendMessage(String.format("Order one: %d\nOrder two: %d\nOrder three: %d", one, two, three));
                return true;
                //order one
            } else if (strings[0].equalsIgnoreCase("one")) {
                orderController.processOrder((Player) commandSender, 1);
                return true;
            } else if (strings[0].equalsIgnoreCase("two")) {
                orderController.processOrder((Player) commandSender, 2);
                return true;
            } else if (strings[0].equalsIgnoreCase("three")) {
                orderController.processOrder((Player) commandSender, 2);
                return true;
            }
        }
        return false;
    }
}
