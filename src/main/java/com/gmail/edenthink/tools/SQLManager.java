package com.gmail.edenthink.tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Eden on 2015/12/4.
 */
public abstract class SQLManager {

    /**
     * execute a sql statement
     * @param sql statement
     * @return number of row being impacted
     */
    public int executeSQL(String sql) {
        int number = -1;
        try (Statement s = Driver.getConnection().createStatement()) {
            number = s.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.printf("SQL message(%d): %s", e.getErrorCode(), e.getMessage());
        }
        return number;
    }

    /**
     * Running a query to get one data of a player
     *
     * @param table     the table
     * @param key       whose data
     * @param attribute what data
     * @return data
     */
    public Object executeQuery(String table, String keyAttribute, String key, String attribute) {
        Object re = null;
        String sql = String.format("SELECT %s FROM %s WHERE %s = \"%s\";", attribute, table, keyAttribute, key);
        ResultSet resultSet = null;
        try (Statement s = Driver.getConnection().createStatement()) {
            try {
                resultSet = s.executeQuery(sql);
                if (resultSet.next()) {
                    re = resultSet.getObject(attribute);
                }
            } catch (SQLException e) {
                System.err.printf("SQL message(%d): %s", e.getErrorCode(), e.getMessage());
            } finally {
                if (resultSet != null) {
                    resultSet.close();
                }
            }
        } catch (SQLException e) {
            System.err.printf("SQL message(%d): %s", e.getErrorCode(), e.getMessage());
        }
        return re;
    }
}
