package com.example.E_comers.Transformer;

import com.example.E_comers.DTO.Request.CustomerRequest;
import com.example.E_comers.DTO.Response.CustomerResponse;
import com.example.E_comers.Model.Customer;

public class CustomerTransformer {
    public static Customer CustomerRequestToCustomer(CustomerRequest customerRequest){
        return Customer.builder()
                .name(customerRequest.getName())
                .email(customerRequest.getEmail())
                .phone(customerRequest.getPhone())
                .gender(customerRequest.getGender())
                .build();
    }

    public static CustomerResponse CustomerToCustomerResponse(Customer customer){
        return CustomerResponse.builder()
                .name(customer.getName())
                .phone(customer.getPhone())
                .gender(customer.getGender())
                .build();
    }
}
