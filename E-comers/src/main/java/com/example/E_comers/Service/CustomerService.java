package com.example.E_comers.Service;

import com.example.E_comers.DTO.Request.CustomerRequest;
import com.example.E_comers.DTO.Response.CustomerResponse;
import com.example.E_comers.ENUM.Gender;
import com.example.E_comers.Exception.CustomerNotFoundException;
import com.example.E_comers.Model.Customer;
import com.example.E_comers.Repository.CustomerRepository;
import com.example.E_comers.Transformer.CustomerTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;


    public CustomerResponse addCustomer(CustomerRequest customerRequest) {
        Customer customer= CustomerTransformer.CustomerRequestToCustomer(customerRequest);
        Customer savedCustomer=customerRepository.save(customer);
        return CustomerTransformer.CustomerToCustomerResponse(savedCustomer);
    }

    public CustomerResponse getCustomerById(Long id) {

        Optional<Customer> customer=customerRepository.findById(id);
        Customer saved=customer.get();
        if (customer.isEmpty()){
            throw new CustomerNotFoundException("Customer not found");
        }
        return CustomerTransformer.CustomerToCustomerResponse(saved);

    }

    public List<CustomerResponse> getAllCustomer() {

        List<Customer> customers=customerRepository.findAll();

        List<CustomerResponse>responseList=new ArrayList<>();

        for(Customer customer:customers){
            responseList.add(CustomerTransformer.CustomerToCustomerResponse(customer));

        }
        return responseList;
    }


    public List<CustomerResponse> getAllCustomerByGender(Gender gender) {

        List<Customer> customers = customerRepository.findByGender(gender);

        List<CustomerResponse> responseList = new ArrayList<>();
        for (Customer customer : customers) {
            responseList.add(CustomerTransformer.CustomerToCustomerResponse(customer));
        }

        return responseList;
    }

    public CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        customer.setName(customerRequest.getName());
        customer.setEmail(customerRequest.getEmail());
        customer.setPhone(customerRequest.getPhone());
        customer.setGender(customerRequest.getGender());

        Customer update=customerRepository.save(customer);
        return CustomerTransformer.CustomerToCustomerResponse(update);

    }

    public String deleteCustomer(Long id) {
        Optional<Customer> optionalCustomer=customerRepository.findById(id);
        if(optionalCustomer.isEmpty()){
            return"Customer not found";
        }
        customerRepository.deleteById(id);
        return "delete successfully!!!!!!";
    }

    //admin can find all customer
    public List<CustomerResponse> getAllCustomers() {
        List<Customer>customers=customerRepository.findAll();
        List<CustomerResponse>responseList=new ArrayList<>();
        for(Customer customer:customers){
            responseList.add(CustomerTransformer.CustomerToCustomerResponse(customer));

        }
        return responseList;
    }
}
