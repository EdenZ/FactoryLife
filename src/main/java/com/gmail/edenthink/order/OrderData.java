package com.gmail.edenthink.order;

import com.gmail.edenthink.tools.DataModel;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by Eden on 2015/12/22.
 * Recording order information
 */
public class OrderData implements DataModel {
    private int id;
    private String receiver;
    private List<ItemStack> items;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public List<ItemStack> getItems() {
        return items;
    }

    public void setItems(List<ItemStack> items) {
        this.items = items;
    }

    @Override
    public void save() {

    }

    @Override
    public void reload() {

    }
}
