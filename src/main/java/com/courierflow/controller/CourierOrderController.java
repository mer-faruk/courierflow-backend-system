package com.courierflow.controller;

import com.courierflow.entity.CourierOrder;
import com.courierflow.service.CourierOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class CourierOrderController {

    private final CourierOrderService courierOrderService;

    public CourierOrderController(CourierOrderService courierOrderService) {
        this.courierOrderService = courierOrderService;
    }

    @PostMapping
    public CourierOrder addOrder(@RequestBody CourierOrder order,
                                 @RequestParam Long storeId,
                                 @RequestParam Long courierId) {
        return courierOrderService.addOrder(order, storeId, courierId);
    }

    @GetMapping
    public List<CourierOrder> getAllOrders() {
        return courierOrderService.getAllOrders();
    }

    @PutMapping("/{id}/deliver")
    public CourierOrder deliverOrder(@PathVariable Long id) {
        return courierOrderService.deliverOrder(id);
    }
}
