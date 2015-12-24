package com.gmail.edenthink.tools;

/**
 * Created by Eden on 2015/12/22.
 * My model interface
 */
public interface DataModel {
    /**
     * Save this data to database
     * If the tuple does not exist, insert new tuple
     */
    void save();

    /**
     * Reload data from database
     * If tuple does not exist, call save() to insert new tuple
     */
    void reload();
}
