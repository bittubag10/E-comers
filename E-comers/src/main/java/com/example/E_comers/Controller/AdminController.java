package com.example.E_comers.Controller;

import com.example.E_comers.DTO.Request.CustomerRequest;
import com.example.E_comers.DTO.Response.CustomerResponse;
import com.example.E_comers.Service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final CustomerService customerService;
@GetMapping("/AllCustomer")
    public ResponseEntity<List<CustomerResponse>>getAllCustomer(){
        List<CustomerResponse>responses=customerService.getAllCustomers();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
}
