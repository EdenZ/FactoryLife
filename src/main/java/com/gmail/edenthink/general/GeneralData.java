package com.gmail.edenthink.general;

import com.gmail.edenthink.tools.DataAccess;
import com.gmail.edenthink.tools.Driver;
import com.gmail.edenthink.tools.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Eden on 2015/12/9.
 */
public class GeneralData {
    public static final String TABLE = "general";
    public static final String NAME = "name", IS_LEFT = "is_left", IS_SWITCH = "is_switch";

    public static void newPlayer(String player) {

        String sql = String.format("INSERT INTO %s (%s) VALUES (\"%s\");", TABLE, NAME, player);
        try (Statement statement = Driver.getConnection().createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            if (e.getErrorCode() != 0) {
                Util.printSQLError(e);
            }
        }
    }

    public static int getRemain(String player) {
        String sql = String.format("SELECT * FROM %s WHERE %s = \"%s\";", TABLE, NAME, player);
        int num = 0;
        try (Statement s = Driver.getConnection().createStatement(); ResultSet set = s.executeQuery(sql)) {
            if (set.next()) {
                num = set.getInt(IS_LEFT);
            }
        } catch (SQLException e) {
            Util.printSQLError(e);
        }
        return num;
    }

    public static void setRemain(String player, int num) {
        String sql = String.format("UPDATE %s SET %s = %d WHERE %s = \"%s\";", TABLE, IS_LEFT, num, NAME, player);
        try (Statement statement = Driver.getConnection().createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            Util.printSQLError(e);
        }
    }

    public static Location getChestLocation(String player) {
        Location loc = null;
        String sql = String.format("SELECT * FROM location WHERE owner = \'%s\' AND type = \"respawnchest\";", player);
        try (Statement statement = Driver.getConnection().createStatement()) {
            try (ResultSet set = statement.executeQuery(sql)) {
                if (set.next()) {
                    double x = set.getDouble("x");
                    double y = set.getDouble("y");
                    double z = set.getDouble("z");
                    World world = Bukkit.getWorld(set.getString("world"));
                    loc = new Location(world, x, y, z);
                }
            }

        } catch (SQLException e) {
            Util.printSQLError(e);
        }

        return loc;
    }

    public static void setChestLocation(String player, double x, double y, double z, String world) {
        String sql = String.format("INSERT INTO location " +
                "VALUES (%s, \'respawnchest\', %f, %f, %f, %s);", player, x, y, z, world);
        if (getChestLocation(player) != null) {
            sql = String.format("UPDATE location" +
                    "SET x = %f, y = %f, z = %f, world = \"%s\"" +
                    "WHERE owner = \"%s\" AND type = \"respawnchest\";", x, y, z, world, player);
        }
        try (Statement statement = Driver.getConnection().createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            if (e.getErrorCode() != 0) {
                Util.printSQLError(e);
            }
        }
    }

    public static void resetRemain() {
        String sql = String.format("UPDATE %s SET %s = 3;", TABLE, IS_LEFT);
        try (Statement statement = Driver.getConnection().createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            Util.printSQLError(e);
        }
    }

    public static void setSwitch(String player, int isOn) {
        String sql = String.format("UPDATE %s SET %s = %d WHERE %s = \"%s\";", TABLE, IS_SWITCH, isOn, NAME, player);
        try (Statement statement = Driver.getConnection().createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            Util.printSQLError(e);
        }
    }

    public static boolean getSwitch(String player) {
        int isOn = 0;
        String sql = String.format("SELECT %s FROM %s WHERE %s = \"%s\";", IS_SWITCH, TABLE, NAME, player);
        try (Statement s = Driver.getConnection().createStatement()) {
            try (ResultSet set = s.executeQuery(sql)) {
                if (set.next()) {
                    isOn = set.getInt(IS_SWITCH);
                }
            }
        } catch (SQLException e) {
            Util.printSQLError(e);
        }
        return isOn == 1;
    }
}
