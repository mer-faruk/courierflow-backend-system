package com.courierflow.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "courier_orders")
public class CourierOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String customerAddress;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime createdAt;

    @ManyToOne
    private Store store;

    @ManyToOne
    private Courier courier;

    public CourierOrder() {
    }

    public CourierOrder(String customerName, String customerAddress, OrderStatus status, LocalDateTime createdAt, Store store, Courier courier) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.status = status;
        this.createdAt = createdAt;
        this.store = store;
        this.courier = courier;
    }

    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Store getStore() {
        return store;
    }

    public Courier getCourier() {
        return courier;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }
}
