package com.gmail.edenthink.ticket;

import com.gmail.edenthink.tools.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Eden on 2015/12/7.
 */
public class TicketCommand implements CommandExecutor {
    private final TicketController controller;

    public TicketCommand(TicketController controller) {
        this.controller = controller;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        if (command.getName().equalsIgnoreCase("ticket")) {
            if (strings.length == 1) {
                if (strings[0].equalsIgnoreCase("info")) {
                    commandSender.sendMessage(String.format("Ticket: %3d", controller.getData().getTicket(commandSender.getName())));
                    return true;
                }
            } else if (strings.length == 2) {
                if (strings[0].equalsIgnoreCase("buy")) {
                    if (strings[1].equalsIgnoreCase("one")) {
                        if (Util.withdraw((Player) commandSender, 40)) {
                            controller.getData().modifyTicket(commandSender.getName(), 20);
                        }
                    } else if (strings[1].equalsIgnoreCase("two")) {
                        if (Util.withdraw((Player) commandSender, 50)) {
                            controller.getData().modifyTicket(commandSender.getName(), 30);
                        }
                    } else if (strings[1].equalsIgnoreCase("three")) {
                        if (Util.withdraw((Player) commandSender, 70)) {
                            controller.getData().modifyTicket(commandSender.getName(), 50);
                        }
                    }
                }
            }
        }
        return false;
    }
}
