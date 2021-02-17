package com.example.inventory.model;

import javax.persistence.*;

@Entity
@Table(name = "inventory")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "amount")
    private long amount;

    @Column(name = "inventoryCode")
    private long inventoryCode;

    public Item() {
    }

    public Item(String name, long amount, long inventoryCode) {
        this.name = name;
        this.amount = amount;
        this.inventoryCode = inventoryCode;
    }

    public Item(Item other) {
        this.name = other.name;
        this.amount = other.amount;
        this.inventoryCode = other.inventoryCode;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getInventoryCode() {
        return inventoryCode;
    }

    public void setInventoryCode(long inventoryCode) {
        this.inventoryCode = inventoryCode;
    }

    @Override
    public String toString() {
        return "Item [" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", inventoryCode=" + inventoryCode +
                ']';
    }
}
