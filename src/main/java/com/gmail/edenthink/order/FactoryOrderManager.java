package com.gmail.edenthink.order;

import com.gmail.edenthink.tools.Driver;
import com.gmail.edenthink.tools.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Controlling database
 */
public class FactoryOrderManager {
    final String PLAYER = "player";
    final String TABLE = "factory_order";
    final String ORDER_ONE = "order_one";
    final String ORDER_TWO = "order_two";
    final String ORDER_THREE = "order_three";

    public void insertNewOrder(String player) {
        String sql = String.format("INSERT INTO %s (%s) VALUES (\"%s\");", TABLE, PLAYER, player);
        try (Statement statement = Driver.getConnection().createStatement()){
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.print(Util.printSQLError(e));
        }
    }

    public boolean orderTimesReduce(String player, String orderNo) {
        int current = getRemain(player, orderNo);
        if (current < 1) return false;
        String sql = String.format("UPDATE %s SET %s = %d WHERE %s = \"%s\";", TABLE, orderNo, current - 1, PLAYER, player);
        try (Statement statement = Driver.getConnection().createStatement()){
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.print(Util.printSQLError(e));
        }
        return true;
    }

    /**
     *
     * @param player who
     * @param orderNo what
     * @return number
     */
    public int getRemain(String player, String orderNo) {
        int num = 0;
        ResultSet set = null;
        String sql = String.format("SELECT %s FROM %s WHERE %s = \"%s\"", orderNo, TABLE, PLAYER, player);
        try (Statement statement = Driver.getConnection().createStatement()){
            set = statement.executeQuery(sql);
            if (set.next()) {
                num = set.getInt(orderNo);
            }
        } catch (SQLException e) {
            System.err.print(Util.printSQLError(e));
        }
        finally {
            if (set != null) {
                try {
                    set.close();
                } catch (SQLException e) {
                    System.err.print(Util.printSQLError(e));
                }
            }
        }
        return num;
    }

    public void resetOrderTimes() {
        String sql = String.format("UPDATE %s SET %s = 3, %s = 3, %s = 3", TABLE, ORDER_ONE, ORDER_TWO, ORDER_THREE);
        try (Statement statement = Driver.getConnection().createStatement()){
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.print(Util.printSQLError(e));
        }
    }
}
