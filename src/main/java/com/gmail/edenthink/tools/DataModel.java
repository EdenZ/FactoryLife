package com.gmail.edenthink.tools;

/**
 * Created by Eden on 2015/12/22.
 * My model interface
 */
public interface DataModel {
    /**
     * Save this data to database
     */
    void save();

    /**
     * Reload data from database
     */
    void reload();
}
