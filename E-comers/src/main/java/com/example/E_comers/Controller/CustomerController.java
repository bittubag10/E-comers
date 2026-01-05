package com.example.E_comers.Controller;

import com.example.E_comers.DTO.Request.CustomerRequest;
import com.example.E_comers.DTO.Response.CustomerResponse;
import com.example.E_comers.ENUM.Gender;
import com.example.E_comers.Service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<CustomerResponse> addCustomer(@RequestBody CustomerRequest customerRequest){
        CustomerResponse response=customerService.addCustomer(customerRequest);
        
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable("id") Long id){
        CustomerResponse response=customerService.getCustomerById(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/allCustomer")
    public ResponseEntity<List<CustomerResponse>> getAllCustomer(){
        List<CustomerResponse> responses=customerService.getAllCustomer();
        return new ResponseEntity<>(responses,HttpStatus.OK);
    }

    @GetMapping("/by-gender")
    public ResponseEntity<List<CustomerResponse>> getAllByGender(@RequestParam("gender") String gender){
        Gender g = Gender.valueOf(gender.toUpperCase());  // convert string → ENUM
        return ResponseEntity.ok(customerService.getAllCustomerByGender(g));
    }

    @PutMapping("/update-customer/{id}")
    public ResponseEntity<CustomerResponse>updateCustomer(@PathVariable Long id,@RequestBody CustomerRequest customerRequest){

        CustomerResponse response=customerService.updateCustomer(id,customerRequest);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>deleteCustomer(@PathVariable Long id){
        String message=customerService.deleteCustomer(id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

}
