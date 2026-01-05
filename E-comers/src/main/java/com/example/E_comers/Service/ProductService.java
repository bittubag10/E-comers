package com.example.E_comers.Service;

import com.example.E_comers.DTO.Request.ProductRequest;
import com.example.E_comers.DTO.Response.ProductResponse;
import com.example.E_comers.Model.Product;
import com.example.E_comers.Repository.ProductRepository;
import com.example.E_comers.Transformer.ProductTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ProductResponse addProduct(ProductRequest productRequest) {
        Product product= ProductTransformer.ProductRequestToProduct(productRequest);
        Product savedProduct=productRepository.save(product);
        return ProductTransformer.ProductToProductResponse(savedProduct);
    }

    public ProductResponse getProduct(Long id) {
        Optional<Product> product=productRepository.findById(id);
        Product save=product.get();
        if (product.isEmpty()){
            throw new IllegalArgumentException("product ID is invalid ");
        }
        return ProductTransformer.ProductToProductResponse(save);
    }
}
