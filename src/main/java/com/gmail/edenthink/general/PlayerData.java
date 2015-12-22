package com.gmail.edenthink.general;

import com.gmail.edenthink.tools.DataModel;

/**
 * For player data
 */
public class PlayerData implements DataModel {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void save() {

    }

    @Override
    public void reload() {

    }
}
