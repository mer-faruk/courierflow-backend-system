package com.courierflow.repository;

import com.courierflow.entity.CourierOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourierOrderRepository extends JpaRepository<CourierOrder, Long> {
}
