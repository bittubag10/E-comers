package com.example.E_comers.Repository;

import com.example.E_comers.ENUM.Gender;
import com.example.E_comers.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    List<Customer> findByGender(Gender gender);


}
