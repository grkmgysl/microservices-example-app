package com.grkmgysl.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    //we created productResponse because we don't want to expose our models to outer world
    //we can add new fields to product model but not the productResponse

    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}
