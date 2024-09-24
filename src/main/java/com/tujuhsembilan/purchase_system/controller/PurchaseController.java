package com.tujuhsembilan.purchase_system.controller;

import com.tujuhsembilan.purchase_system.dto.PurchaseDTO;
import com.tujuhsembilan.purchase_system.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    // Endpoint untuk handle pembelian
    @PostMapping("/optimize")
    public ResponseEntity<?> optimizePurchase(@RequestBody PurchaseDTO purchaseDTO) {
        Map<String, Object> response = purchaseService.findOptimalPurchase(purchaseDTO);
        return ResponseEntity.ok(response);
    }
}
