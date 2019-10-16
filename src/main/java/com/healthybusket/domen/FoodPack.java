package com.healthybusket.domen;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "food_pack")
public class FoodPack implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title ;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Column(name = "cost", nullable = false)
    @Range(min = 1)
    private Float cost ;

    @Column(name = "amount", nullable = false)
    private int amount;


    @OneToMany(mappedBy = "food_pack")
    private Set<DeliveryOrderHasFoodPack> deliveryOrderHasFoodPacks = new HashSet<>();

    public Set<DeliveryOrderHasFoodPack> getDeliveryOrderHasFoodPacks() {
        return deliveryOrderHasFoodPacks;
    }

    public void setDeliveryOrderHasFoodPacks(Set<DeliveryOrderHasFoodPack> deliveryOrderHasFoodPacks) {
        this.deliveryOrderHasFoodPacks = deliveryOrderHasFoodPacks;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }
}
