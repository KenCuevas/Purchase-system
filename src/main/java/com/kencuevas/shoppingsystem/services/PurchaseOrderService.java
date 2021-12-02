package com.kencuevas.shoppingsystem.services;

import com.kencuevas.shoppingsystem.dto.PurchaseOrderDTO;

import java.util.List;

public interface PurchaseOrderService {
    PurchaseOrderDTO createOrder(long articleId, PurchaseOrderDTO purchaseOrderDTO);
    List<PurchaseOrderDTO> getOrderByArticleId(long articleId);
    PurchaseOrderDTO getOrderById(Long articleId, Long purchaseOrderId);
    PurchaseOrderDTO updateOrder(Long articleId, long purchaseOrderId, PurchaseOrderDTO purchaseOrderDTO);
    void deleteOrder(Long articleId, Long purchaseOrderId);
    List<PurchaseOrderDTO> getAllOrder();
}
