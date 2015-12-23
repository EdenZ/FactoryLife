package com.gmail.edenthink.general;

import com.gmail.edenthink.tools.DataModel;
import com.gmail.edenthink.tools.Driver;
import com.gmail.edenthink.tools.Util;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * For player data
 */
public class PlayerData implements DataModel {
    private String name;
    private int mob_kill = 0;
    private int order_complete = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMob_kill() {
        return mob_kill;
    }

    public void setMob_kill(int mob_kill) {
        this.mob_kill = mob_kill;
    }

    public int getOrder_complete() {
        return order_complete;
    }

    public void setOrder_complete(int order_complete) {
        this.order_complete = order_complete;
    }

    @Override
    public void save() {
        String insert = "INSERT INTO player_data VALUES (?, ?, ?);";
        String update = "UPDATE player_data SET \'mob_kill\' = ?, \'order_complete\' = ? WHERE \'name\' = ?;";
        try (PreparedStatement statement = Driver.getConnection().prepareStatement(insert)) {
            statement.setString(1, name);
            statement.setInt(2, mob_kill);
            statement.setInt(3, order_complete);
            statement.executeUpdate();
        } catch (SQLException e) {
            try (PreparedStatement statement = Driver.getConnection().prepareStatement(update)) {
                statement.setString(1, name);
                statement.setInt(2, mob_kill);
                statement.setInt(3, order_complete);
                statement.executeUpdate();
            } catch (SQLException e1) {
                Util.printSQLError(e1);
            }
        }
    }

    @Override
    public void reload() {

    }
}
