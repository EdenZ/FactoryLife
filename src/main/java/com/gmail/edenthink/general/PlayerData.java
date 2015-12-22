package com.gmail.edenthink.general;

import com.gmail.edenthink.tools.DataModel;

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

    }

    @Override
    public void reload() {

    }
}
