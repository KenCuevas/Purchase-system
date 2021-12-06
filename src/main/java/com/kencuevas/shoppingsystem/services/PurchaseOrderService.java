package com.kencuevas.shoppingsystem.services;

import com.kencuevas.shoppingsystem.dto.PurchaseOrderDTO;

import java.util.List;

public interface PurchaseOrderService {
    PurchaseOrderDTO createPurchaseOrder(long articleId, PurchaseOrderDTO purchaseOrderDTO);
    List<PurchaseOrderDTO> getOrdersByArticleId(long articleId);
    PurchaseOrderDTO getOrderById(Long articleId, Long purchaseOrderId);
    PurchaseOrderDTO updateOrder(Long articleId, long purchaseOrderId, PurchaseOrderDTO purchaseRequest);
    void deleteOrder(Long articleId, Long purchaseOrderId);
}
