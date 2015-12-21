package com.gmail.edenthink.model;

import com.avaje.ebean.annotation.CreatedTimestamp;
import com.avaje.ebean.annotation.UpdatedTimestamp;
import com.avaje.ebean.validation.NotNull;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 *Order data
 */

@Entity
@Table(name = "order_data")
public class OrderData {
    @Id
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private PlayerData owner;

    @CreatedTimestamp
    private Timestamp createdTime;

    @UpdatedTimestamp
    private Timestamp updatedTime;

    @NotNull
    private double reward;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "order")
    private List<ItemData> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlayerData getOwner() {
        return owner;
    }

    public void setOwner(PlayerData owner) {
        this.owner = owner;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }

    public List<ItemData> getItems() {
        return items;
    }

    public void setItems(List<ItemData> items) {
        this.items = items;
    }
}
