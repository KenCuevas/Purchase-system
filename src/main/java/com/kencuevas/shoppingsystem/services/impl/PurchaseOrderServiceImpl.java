package com.kencuevas.shoppingsystem.services.impl;

import com.kencuevas.shoppingsystem.dto.PurchaseOrderDTO;
import com.kencuevas.shoppingsystem.exceptions.PurchaseApiException;
import com.kencuevas.shoppingsystem.exceptions.ResourceNotFoundException;
import com.kencuevas.shoppingsystem.models.Article;
import com.kencuevas.shoppingsystem.models.PurchaseOrder;
import com.kencuevas.shoppingsystem.repositories.ArticleRepository;
import com.kencuevas.shoppingsystem.repositories.PurchaseOrderRepository;
import com.kencuevas.shoppingsystem.services.PurchaseOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    private PurchaseOrderRepository purchaseOrderRepository;
    private ArticleRepository articleRepository;
    private ModelMapper mapper;

    public PurchaseOrderServiceImpl(PurchaseOrderRepository purchaseOrderRepository, ArticleRepository articleRepository, ModelMapper mapper) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.articleRepository = articleRepository;
        this.mapper = mapper;
    }

    @Override
    public PurchaseOrderDTO createPurchaseOrder(long articleId, PurchaseOrderDTO purchaseOrderDTO) {
        PurchaseOrder purchaseOrder = mapToEntity(purchaseOrderDTO);

        // Retrieve article entity by id
        Article article = articleRepository.findById(articleId).orElseThrow(
                ()-> new ResourceNotFoundException("Article", "id", articleId));

        // set article to purchase order entity
        purchaseOrder.setArticle(article);

        // Purchase Order entity to DB
        PurchaseOrder newPurchaseOrder = purchaseOrderRepository.save(purchaseOrder);

        return mapToDTO(newPurchaseOrder);
    }

    @Override
    public List<PurchaseOrderDTO> getOrdersByArticleId(long articleId) {
        // Retrieve orders by articleId
        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findByArticleId(articleId);

        // Convert list of orders entities to list of articles DTO's
        return purchaseOrders.stream().map(purchaseOrder -> mapToDTO(purchaseOrder)).collect(Collectors.toList());
    }

    @Override
    public PurchaseOrderDTO getOrderById(Long articleId, Long purchaseOrderId) {
        //Retrieve article entity by id
        Article article = articleRepository.findById(articleId).orElseThrow(
                ()->new ResourceNotFoundException("Article", "id", articleId));
        // Retrieve orders by id
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(purchaseOrderId).orElseThrow(()->
                new ResourceNotFoundException("Purchase Order", "id", purchaseOrderId));

        if(!purchaseOrder.getArticle().getId().equals(article.getId())){
            throw new PurchaseApiException(HttpStatus.BAD_REQUEST, "Order does not belong to article");
        }
        return mapToDTO(purchaseOrder);
    }

    @Override
    public PurchaseOrderDTO updateOrder(Long articleId, long purchaseOrderId, PurchaseOrderDTO purchaseRequest) {
        //Retrieve article entity by Id
        Article article = articleRepository.findById(articleId).orElseThrow(
                ()->new ResourceNotFoundException("Article", "id", articleId));
        // Retrieve orders by Id
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(purchaseOrderId).orElseThrow(()->
                new ResourceNotFoundException("Purchase Order", "id", purchaseOrderId));
        //It is evaluated if this order corresponds to this article.
        if(!purchaseOrder.getArticle().getId().equals(article.getId())){
            throw new PurchaseApiException(HttpStatus.BAD_REQUEST, "Order does not belong to article");
        }
        purchaseOrder.setOrderNumber(purchaseRequest.getOrderNumber());
        purchaseOrder.setDateOrder(purchaseRequest.getDateOrder());
        purchaseOrder.setStatus(purchaseRequest.isStatus());
        purchaseOrder.setQuantity(purchaseRequest.getQuantity());
        purchaseOrder.setUnitCost(purchaseRequest.getUnitCost());

        PurchaseOrder updatedOrder = purchaseOrderRepository.save(purchaseOrder);
        return mapToDTO(updatedOrder);
    }

    @Override
    public void deleteOrder(Long articleId, Long purchaseOrderId) {
        // Retrieve article entity by id
        Article article = articleRepository.findById(articleId).orElseThrow(()->
                new ResourceNotFoundException("Article", "id", articleId));
        // Retrieve orders by Id
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(purchaseOrderId).orElseThrow(()->
                new ResourceNotFoundException("Purchase Order", "id", purchaseOrderId));
        //It is evaluated if this order corresponds to this article.
        if(!purchaseOrder.getArticle().getId().equals(article.getId())){
            throw new PurchaseApiException(HttpStatus.BAD_REQUEST, "Order does not belong to article");
        }
        purchaseOrderRepository.delete(purchaseOrder);
    }

    private PurchaseOrderDTO mapToDTO(PurchaseOrder purchaseOrder){
        PurchaseOrderDTO purchaseOrderDTO = mapper.map(purchaseOrder, PurchaseOrderDTO.class);
        return  purchaseOrderDTO;
    }
    private PurchaseOrder mapToEntity(PurchaseOrderDTO purchaseOrderDTO){
        PurchaseOrder purchaseOrder = mapper.map(purchaseOrderDTO, PurchaseOrder.class);
        return  purchaseOrder;
    }
}
