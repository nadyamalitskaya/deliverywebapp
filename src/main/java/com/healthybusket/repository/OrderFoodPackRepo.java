package com.healthybusket.repository;
import com.healthybusket.domen.DeliveryOrderHasFoodPack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderFoodPackRepo extends JpaRepository<DeliveryOrderHasFoodPack, Long>
{
}
