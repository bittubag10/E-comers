package com.example.E_comers.Controller;

import com.example.E_comers.DTO.Request.CustomerRequest;
import com.example.E_comers.DTO.Request.ProductRequest;
import com.example.E_comers.DTO.Response.CustomerResponse;
import com.example.E_comers.DTO.Response.ProductResponse;
import com.example.E_comers.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<ProductResponse> addCustomer(@RequestBody ProductRequest productRequest){
       ProductResponse response=productService.addProduct(productRequest);

       return new ResponseEntity<>(response,HttpStatus.CREATED);

    }
    @GetMapping("/getProduct/{id}")
    public ResponseEntity<ProductResponse>getProduct(@PathVariable("id") Long id){
        ProductResponse response=productService.getProduct(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


}
