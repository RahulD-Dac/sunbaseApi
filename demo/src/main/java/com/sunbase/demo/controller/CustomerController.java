package com.sunbase.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbase.demo.model.CustomerDTO;
import com.sunbase.demo.service.CustomerService;

@RestController
@RequestMapping("/sunbase/portal/api") 
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/assignment.jsp")
    public ResponseEntity<String> createCustomer(@RequestBody CustomerDTO customerDTO) {
        
        
        
        String accessToken = "dGVzdEBzdW5iYXNlZGF0YS5jb206VGVzdEAxMjM=";

        // Set the access token in the CustomerService
        customerService.setAccessToken(accessToken);

        // Call the createNewCustomer method to create a new customer
        String result = customerService.createNewCustomer(customerDTO.getFirst_name(), customerDTO.getLast_name(),
                customerDTO.getStreet(), customerDTO.getAddress(), customerDTO.getCity(), customerDTO.getState(),
                customerDTO.getEmail(), customerDTO.getPhone());

        return ResponseEntity.ok(result);
    }
}
