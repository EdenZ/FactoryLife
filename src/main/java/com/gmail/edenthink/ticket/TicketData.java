package com.gmail.edenthink.ticket;

import com.gmail.edenthink.tools.Driver;
import com.gmail.edenthink.tools.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Controlling ticket data
 */
public class TicketData {
    public final String TABLE = "player_ticket";
    public final String PLAYER = "player";
    public final String TICKET = "ticket";

    public void newPlayer(String player) {
        String sql = String.format("INSERT INTO %s (%s) VALUES (\"%s\");", TABLE, PLAYER, player);
        try (Statement statement = Driver.getConnection().createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            if (e.getErrorCode() != 0) {
                Util.printSQLError(e);
            }
        }
    }

    public int getTicket(String player) {
        int num = 0;
        String sql = String.format("SELECT %s FROM %s WHERE %s = \"%s\";", TICKET, TABLE, PLAYER, player);
        try (Statement statement = Driver.getConnection().createStatement(); ResultSet set = statement.executeQuery(sql)) {
            if (set.next()) {
                num = set.getInt(TICKET);
            }
        } catch (SQLException e) {
            Util.printSQLError(e);
        }
        return num;
    }

    public void setTicket(String player, int amount) {
        String sql = String.format("UPDATE %s SET %s = %d WHERE %s = \"%s\";", TABLE, TICKET, amount, PLAYER, player);
        try (Statement statement = Driver.getConnection().createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            Util.printSQLError(e);
        }
    }

    public void modifyTicket(String player, int amount) {
        setTicket(player, getTicket(player) + amount);
    }
}
