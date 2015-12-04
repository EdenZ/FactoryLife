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

    /**
     * Get the int data of player from database
     *
     * @param table     the table
     * @param key       whose data
     * @param attribute what data
     * @return int data
     */
    public int getInt(String table, String keyAttribute, String key, String attribute) {
        return (int) executeQuery(table, keyAttribute, key, attribute);
    }

    /**
     * Get string data of player from database
     *
     * @param table     the table
     * @param key       whose data
     * @param attribute what data
     * @return string data
     */
    public String getString(String table, String keyAttribute, String key, String attribute) {
        return (String) executeQuery(table, keyAttribute, key, attribute);
    }

    /**
     * Update a string value
     *
     * @param table     the table
     * @param key       the player
     * @param attribute what kind of data
     * @param value     the value
     */
    public void updateString(String table, String keyAttribute, String key, String attribute, String value) {
        String sql = String.format("UPDATE %s SET %s = \"%s\"  WHERE %s = \"%s\";", table, attribute, value, keyAttribute, key);
        executeSQL(sql);
    }

    /**
     * Updata a int value
     *
     * @param table     the table
     * @param key       the player
     * @param attribute what kind of data
     * @param value     the value
     */
    public void updateInt(String table, String keyAttribute, String key, String attribute, int value) {
        String sql = String.format("UPDATE %s SET %s = %d  WHERE %s = \"%s\";", table, attribute, value, keyAttribute, key);
        executeSQL(sql);
    }

    /**
     * Increase or decrease of amount for one int data
     *
     * @param table     which table
     * @param key       the key
     * @param attribute what kind of data
     * @param value     how much
     */
    public void intModify(String table, String keyAttribute, String key, String attribute, int value) {
        updateInt(table, keyAttribute, key, attribute, getInt(table, keyAttribute, key, attribute) + value);
    }

    /**
     * Insert new row
     * @param table the table
     * @param col   column(primary key)
     * @param value value
     */
    public void insertRow(String table, String col, String value) {
        String sql = String.format("INSERT INTO %s ( %s ) VALUES (\"%s\");", table, col, value);
        executeSQL(sql);
    }
}
