package com.gmail.edenthink.general;

import com.gmail.edenthink.tools.Driver;
import com.gmail.edenthink.tools.Util;

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
        return 0;
    }

    public static void setRemain(String player, int num) {

    }

    public static void resetRemain() {

    }

    public static void setSwitch(String player, boolean isEnable) {

    }

    public static boolean getSwitch(String player) {
        return false;
    }
}
