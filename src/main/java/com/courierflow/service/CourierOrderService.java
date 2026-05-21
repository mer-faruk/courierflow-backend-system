package com.courierflow.service;

import com.courierflow.entity.Courier;
import com.courierflow.entity.CourierOrder;
import com.courierflow.entity.CourierStatus;
import com.courierflow.entity.OrderStatus;
import com.courierflow.entity.Store;
import com.courierflow.repository.CourierOrderRepository;
import com.courierflow.repository.CourierRepository;
import com.courierflow.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourierOrderService {

    private final CourierOrderRepository courierOrderRepository;
    private final StoreRepository storeRepository;
    private final CourierRepository courierRepository;

    public CourierOrderService(CourierOrderRepository courierOrderRepository,
                               StoreRepository storeRepository,
                               CourierRepository courierRepository) {
        this.courierOrderRepository = courierOrderRepository;
        this.storeRepository = storeRepository;
        this.courierRepository = courierRepository;
    }

    public CourierOrder addOrder(CourierOrder order, Long storeId, Long courierId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Store not found"));

        Courier courier = courierRepository.findById(courierId)
                .orElseThrow(() -> new RuntimeException("Courier not found"));

        if (courier.getStatus() == CourierStatus.BUSY) {
            throw new RuntimeException("Courier is already busy");
        }

        order.setStore(store);
        order.setCourier(courier);
        courier.setStatus(CourierStatus.BUSY);
        order.setStatus(OrderStatus.CREATED);
        order.setCreatedAt(LocalDateTime.now());

        courierRepository.save(courier);

        return courierOrderRepository.save(order);
    }

    public List<CourierOrder> getAllOrders() {
        return courierOrderRepository.findAll();
    }

    public CourierOrder deliverOrder(Long orderId) {
        CourierOrder order = courierOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Courier courier = order.getCourier();

        order.setStatus(OrderStatus.DELIVERED);
        courier.setStatus(CourierStatus.AVAILABLE);

        courierRepository.save(courier);

        return courierOrderRepository.save(order);
    }
}
