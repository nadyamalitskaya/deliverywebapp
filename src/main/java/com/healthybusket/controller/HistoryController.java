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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HistoryController {
    @Autowired
    private DeliveryOrderRepo deliveryOrderRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/history")
    public String getOrders(
            Model model,
            @AuthenticationPrincipal User user,
            @RequestParam(name = "order", required = false) Long orderId){
        Iterable<DeliveryOrder> infos = deliveryOrderRepo.findAll();
        model.addAttribute("infos", validateOrderList(infos));

        return "history";
    }

    private Iterable<DeliveryOrder> validateOrderList(Iterable<DeliveryOrder> infos){
        List<DeliveryOrder> infosUpdated = new ArrayList();
        infos.forEach(item -> {
            if(item.getStatus_id().equals(Status.CLOSED.getStatusId())){
                infosUpdated.add(item);
            }
        });
        return infosUpdated;
    }
}
