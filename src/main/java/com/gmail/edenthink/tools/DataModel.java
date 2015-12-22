package com.gmail.edenthink.tools;

/**
 * Created by Eden on 2015/12/22.
 */
public interface DataModel {
    /**
     * Save this data to database
     */
    public void save();

    /**
     * Reload data from database
     */
    public void reload();
}
