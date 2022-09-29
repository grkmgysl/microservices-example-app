package com.grkmgysl.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemsDto {

    private Long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;

    public OrderLineItemsDto(String skuCode, BigDecimal price, Integer quantity) {
        this.skuCode = skuCode;
        this.price = price;
        this.quantity = quantity;
    }
}
