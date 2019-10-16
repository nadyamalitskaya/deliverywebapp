package com.healthybusket.domen;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class DeliveryFoodPackKey implements Serializable {
    @Column(name="food_pack_id")
    Long food_pack_id;

    @Column(name="order_id")
    Long order_id;
}
