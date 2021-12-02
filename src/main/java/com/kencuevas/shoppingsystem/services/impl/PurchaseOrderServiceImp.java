package com.kencuevas.shoppingsystem.services.impl;


import com.kencuevas.shoppingsystem.dto.PurchaseOrderDTO;
import com.kencuevas.shoppingsystem.exceptions.ResourceNotFoundException;
import com.kencuevas.shoppingsystem.models.Article;
import com.kencuevas.shoppingsystem.models.PurchaseOrder;
import com.kencuevas.shoppingsystem.repositories.ArticleRepository;
import com.kencuevas.shoppingsystem.repositories.PurchaseOrderRepository;
import com.kencuevas.shoppingsystem.services.PurchaseOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseOrderServiceImp implements PurchaseOrderService {
    private PurchaseOrderRepository orderRepository;
    private ArticleRepository articleRepository;
    private ModelMapper mapper;

    public PurchaseOrderServiceImp(PurchaseOrderRepository orderRepository, ArticleRepository articleRepository, ModelMapper mapper) {
        this.orderRepository = orderRepository;
        this.articleRepository = articleRepository;
        this.mapper = mapper;
    }

    @Override
    public PurchaseOrderDTO createOrder(long articleId, PurchaseOrderDTO purchaseOrderDTO) {
        PurchaseOrder purchaseOrder = mapToEntity(purchaseOrderDTO);

        Article article = articleRepository.findById(articleId).orElseThrow(
                ()-> new ResourceNotFoundException("Aricle", "id", articleId));
        purchaseOrder.setArticle(article);

        PurchaseOrder newOrder = orderRepository.save(purchaseOrder);
        return mapToDTO(newOrder);
    }

    @Override
    public List<PurchaseOrderDTO> getOrderByArticleId(long articleId) {
        return null;
    }

    @Override
    public PurchaseOrderDTO getOrderById(Long articleId, Long purchaseOrderId) {
        return null;
    }

    @Override
    public PurchaseOrderDTO updateOrder(Long articleId, long purchaseOrderId, PurchaseOrderDTO purchaseOrderDTO) {
        return null;
    }

    @Override
    public void deleteOrder(Long articleId, Long purchaseOrderId) {

    }

    @Override
    public List<PurchaseOrderDTO> getAllOrder() {
        return null;
    }

    private PurchaseOrderDTO mapToDTO(PurchaseOrder purchaseOrder){
        PurchaseOrderDTO purchaseOrderDTO = mapper.map(purchaseOrder, PurchaseOrderDTO.class);
        return purchaseOrderDTO;
    }
    private PurchaseOrder mapToEntity(PurchaseOrderDTO purchaseOrderDTO){
        PurchaseOrder purchaseOrder = mapper.map(purchaseOrderDTO, PurchaseOrder.class);
        return purchaseOrder;
    }
}
