package com.grkmgysl.productservice.service;

import com.grkmgysl.productservice.dto.ProductRequest;
import com.grkmgysl.productservice.dto.ProductResponse;
import com.grkmgysl.productservice.model.Product;
import com.grkmgysl.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    /*
        instead of doing this we can use @RequiredArgsConstructor

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    */
    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts(){
        List<Product> products = productRepository.findAll();

        //maps all products from products list to productResponse list
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product){
        //takes product and builds productResponse
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
