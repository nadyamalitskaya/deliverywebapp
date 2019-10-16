package com.healthybusket.domen;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class DeliveryOrderHasFoodPack implements Serializable {

    private Long id;

    public DeliveryOrderHasFoodPack(){}

    public DeliveryOrderHasFoodPack(FoodPack foodPack, DeliveryOrder order, int amount){
        food_pack = foodPack;
        this.order = order;
        this.amount = amount;
    }

    private FoodPack food_pack;

    private DeliveryOrder order;

    private int amount;

    @Column(name="amount")
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @ManyToOne()
    @JoinColumn(name="pack_id")
    public FoodPack getFood_pack() {
        return food_pack;
    }


    @ManyToOne()
    @JoinColumn(name="order_id")
    public DeliveryOrder getOrder() {
        return order;
    }

    public void setOrder(DeliveryOrder order) {
        this.order = order;
    }

    public void setFood_pack(FoodPack food_pack) {
        this.food_pack = food_pack;
    }

    @Id
    @GeneratedValue
    @Column(name="order_pack_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
