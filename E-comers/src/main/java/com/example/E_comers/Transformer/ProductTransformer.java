package com.example.E_comers.Transformer;

import com.example.E_comers.DTO.Request.ProductRequest;
import com.example.E_comers.DTO.Response.ProductResponse;
import com.example.E_comers.Model.Product;

public class ProductTransformer {
    public static Product ProductRequestToProduct(ProductRequest productRequest){
        return Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .createdAt(productRequest.getCreatedAt())
                .updatedAt(productRequest.getUpdatedAt())
                .build();
    }

    public static ProductResponse ProductToProductResponse(Product product){
        return ProductResponse.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}
