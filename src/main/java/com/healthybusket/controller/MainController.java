package com.healthybusket.controller;

import com.healthybusket.domen.*;
import com.healthybusket.repository.DeliveryOrderRepo;
import com.healthybusket.repository.FoodPackRepo;
import com.healthybusket.repository.OrderFoodPackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class MainController {
    @Autowired
    private DeliveryOrderRepo deliveryOrderRepo;
    @Autowired
    private FoodPackRepo foodPackRepo;
    @Autowired
    private OrderFoodPackRepo orderFoodPackRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(
            @RequestParam(name = "deliveryId", required = false) Long deliveryId,
            @RequestParam(required = false) String filter,
            @AuthenticationPrincipal User user,
            Model model) {
        Iterable<DeliveryOrder> infos;
        Iterable<FoodPack> foodPacks = foodPackRepo.findAll();
        if (deliveryId != null) {
            addDeliveryToCourier(deliveryId, user.getId());
            return "redirect:/orderlist";
        }
        if (filter != null && !filter.isEmpty()) {
            infos = deliveryOrderRepo.findByName(filter);
        }
        else{
            infos = deliveryOrderRepo.findAllByUser_idIsNull();
        }

        model.addAttribute("foodPacks", foodPacks);
        model.addAttribute("infos", infos);
        model.addAttribute("deliveryFoodPacks", infos);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("/main")
    public String addNewDeliveryOrder(
            @Valid DeliveryOrder deliveryOrder, BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file) throws IOException {
        if(bindingResult.hasErrors()){
            Map<String, String> errorMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorMap); //если получим ошибки,  то они добавятся в Map и отобразятся во view
            model.addAttribute("info", deliveryOrder);
        }else {
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString();
                String resultFileName = uuidFile + "." + file.getOriginalFilename();
                file.transferTo(new File(uploadPath + "/" + resultFileName));
                deliveryOrder.setFilename(resultFileName);
            }
            deliveryOrder.setStatus_id(Status.OPEN);
            model.addAttribute("info",null);
            ArrayList<FoodPack> packs = new ArrayList();
            foodPackRepo.findAll().forEach(packs::add);
            deliveryOrderRepo.saveAndFlush(deliveryOrder);
            Set<DeliveryOrderHasFoodPack> list = new HashSet<>();

            //здесь хранится связь многие ко многим
            for (int i = 0; i < packs.toArray().length; i++) {
                list.add(new DeliveryOrderHasFoodPack(packs.get(i), deliveryOrder, deliveryOrder.getAmounts().get(i)));
            }

            deliveryOrder.setDeliveryOrderHasFoodPacks(list);
            orderFoodPackRepo.saveAll(list);
            orderFoodPackRepo.flush();
            deliveryOrderRepo.save(deliveryOrder);
        }
        Iterable<DeliveryOrder> infos = deliveryOrderRepo.findAll();
        Iterable<FoodPack> foodPacks = foodPackRepo.findAll();
        model.addAttribute("foodPacks", foodPacks);
        model.addAttribute("infos", infos);
        model.addAttribute("filter", "");
        return "redirect:/main";
    }


    private void addDeliveryToCourier(Long deliveryId, Long userId) {
        DeliveryOrder order = deliveryOrderRepo.findById(deliveryId);
        order.setUser_id(userId);
        deliveryOrderRepo.saveAndFlush(order);
    }
}