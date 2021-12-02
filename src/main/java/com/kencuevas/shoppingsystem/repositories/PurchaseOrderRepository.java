package com.kencuevas.shoppingsystem.repositories;

import com.kencuevas.shoppingsystem.models.Article;
import com.kencuevas.shoppingsystem.models.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
    List<PurchaseOrder> findPurchaseOrderId(long purchaseOrderId);
}
