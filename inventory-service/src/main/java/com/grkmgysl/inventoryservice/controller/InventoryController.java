package com.grkmgysl.inventoryservice.controller;

import com.grkmgysl.inventoryservice.dto.InventoryResponse;
import com.grkmgysl.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {


    private final InventoryService inventoryService;


    //http://localhost:8082/api/inventory?skuCode=book1&skuCode=book2

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode){
        return inventoryService.isInStock(skuCode);
    }
}
