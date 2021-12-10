package com.kencuevas.shoppingsystem.controllers;

import com.kencuevas.shoppingsystem.dto.PurchaseOrderDTO;
import com.kencuevas.shoppingsystem.services.PurchaseOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "CRUD REST APIs for purchasing orders resources")
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class PurchaseOrderController {
    private PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Create a new order REST API")
    @PostMapping("/articles/{articleId}/orders")
    public ResponseEntity<PurchaseOrderDTO>createPurchaseOrder(@PathVariable(value = "articleId")long articleId,
                                                               @Valid @RequestBody PurchaseOrderDTO purchaseOrderDTO){
        return new ResponseEntity<>(purchaseOrderService.createPurchaseOrder(articleId, purchaseOrderDTO), HttpStatus.CREATED);
    }
    @ApiOperation("Get all Orders By Article Id REST API")
    @GetMapping("/articles/{articleId}/orders")
    public List<PurchaseOrderDTO>getOrdersByArticlesId(@PathVariable(value = "articleId")Long articleId){
        return purchaseOrderService.getOrdersByArticleId(articleId);
    }
    @ApiOperation(value = "Get Single orders by Id REST API")
    @GetMapping("/articles/{articleId}/orders/{id}")
    public ResponseEntity<PurchaseOrderDTO>getOrdersById(@PathVariable(value = "articleId")Long articleId,
                                                         @PathVariable(value = "id")Long purchaseOrderId){
        PurchaseOrderDTO purchaseOrderDTO = purchaseOrderService.getOrderById(articleId, purchaseOrderId);
        return new ResponseEntity<>(purchaseOrderDTO, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Update Orders by ID REST API")
    @PutMapping("/articles/{articleId}/orders/{id}")
    public ResponseEntity<PurchaseOrderDTO>updateOrders(@PathVariable(value = "articleId")Long articleId,
                                                        @PathVariable(value = "id")Long purchaseOrderId,
                                                        @Valid @RequestBody PurchaseOrderDTO purchaseOrderDTO){
        PurchaseOrderDTO updatedPurchaseOrder = purchaseOrderService.updateOrder(articleId, purchaseOrderId, purchaseOrderDTO);
        return new ResponseEntity<>(updatedPurchaseOrder, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Delete Orders by ID REST API")
    @DeleteMapping("/articles/{articleId}/orders/{id}")
    public ResponseEntity<String>deleteOrder(@PathVariable(value = "articleId")Long articleId,
                                             @PathVariable(value = "id")Long purchaseOrderId){
        purchaseOrderService.deleteOrder(articleId, purchaseOrderId);
        return new ResponseEntity<>("Order deleted successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Get all orders")
    @GetMapping("/all/orders")
    public List<PurchaseOrderDTO>getAllOrders(){
        return purchaseOrderService.getAllOrders();
    }
}
