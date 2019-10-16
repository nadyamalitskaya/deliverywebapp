package com.healthybusket.domen;

import javax.persistence.*;
import java.util.Set;

@Entity
public class OrderStatus {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "status_name", nullable = false)
    private String status_name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "status")
    private Set<DeliveryOrder> orderSet;

    public Set<DeliveryOrder> getOrderSet() {
        return orderSet;
    }

    public void setOrderSet(Set<DeliveryOrder> orderSet) {
        this.orderSet = orderSet;
    }

    public Long getId() {
        return id;
    }

    public String getStatus_name() {
        return status_name;
    }
}