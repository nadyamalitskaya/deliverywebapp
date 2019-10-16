package com.healthybusket.repository;

import com.healthybusket.domen.DeliveryOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryOrderRepo extends JpaRepository<DeliveryOrder, Integer> {
   List<DeliveryOrder> findByName(String name);

   List<DeliveryOrder> findByUserId(Long id);

   List<DeliveryOrder> findAllByUser_idIsNull();

   List<DeliveryOrder> findAllByUser_idIsNotNull();

   DeliveryOrder findById(Long id);
}
