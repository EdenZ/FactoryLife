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
    private OrderType type;
    private String receiver;
    private List<ItemStack> items;

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

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
