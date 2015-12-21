package com.gmail.edenthink.model;

import com.avaje.ebean.validation.NotNull;

import javax.persistence.*;
import java.util.List;

/**
 * Player data
 */

@Entity
@Table(name = "player_data")
public class PlayerData{
    @Id
    private int id;

    @NotNull
    @Column(unique = true)
    private String player;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "owner")
    private List<OrderData> orders;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public List<OrderData> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderData> orders) {
        this.orders = orders;
    }
}
