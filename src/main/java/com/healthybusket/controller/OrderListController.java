package com.healthybusket.controller;

import com.healthybusket.domen.DeliveryOrder;
import com.healthybusket.domen.Status;
import com.healthybusket.domen.User;
import com.healthybusket.repository.DeliveryOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderListController {   //то же самое что CourierPage

    @Autowired
    private DeliveryOrderRepo deliveryOrderRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/orderlist")
    public String getOrders(
            Model model,
            @AuthenticationPrincipal User user,
            @RequestParam(name = "order", required = false) Long orderId){
        if(orderId!= null){
            DeliveryOrder order = deliveryOrderRepo.findById(orderId);
            if(order.getStatus_id() != null){
                if(user.isAdmin() && order.getStatus_id().equals(Status.DELIVERED.getStatusId())){
                    order.setStatus_id(Status.getUpdatedStatus(order.getStatus_id()));
                    deliveryOrderRepo.saveAndFlush(order);
                    return "redirect:/orderlist";
                }
                if(!user.isAdmin() && !order.getStatus_id().equals(Status.DELIVERED.getStatusId())) {
                    order.setStatus_id(Status.getUpdatedStatus(order.getStatus_id()));
                    deliveryOrderRepo.saveAndFlush(order);
                    return "redirect:/orderlist";
                }
            }
        }

        if(!user.isAdmin()){
            Iterable<DeliveryOrder> infos = deliveryOrderRepo.findByUserId(user.getId());
            model.addAttribute("infos", validateOrderList(infos));
        }else{
            Iterable<DeliveryOrder> infos = deliveryOrderRepo.findAllByUser_idIsNotNull();
            model.addAttribute("infos", validateOrderList(infos));
        }

        return "orderList";
    }

    private Iterable<DeliveryOrder> validateOrderList(Iterable<DeliveryOrder> infos){
        List<DeliveryOrder> infosUpdated = new ArrayList();
        infos.forEach(item -> {
            if(!item.getStatus_id().equals(Status.CLOSED.getStatusId())){
                infosUpdated.add(item);
            }
        });
        return infosUpdated;
    }
}