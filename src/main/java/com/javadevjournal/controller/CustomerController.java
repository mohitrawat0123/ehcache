package com.javadevjournal.controller;

import com.javadevjournal.data.Customer;
import com.javadevjournal.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CacheManager cacheManager;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable Long id){
        return customerService.getCustomer(id);
    }

    @GetMapping("/cache/clear/{name}")
    public ResponseEntity<String> clearCache(@PathVariable(name = "cacheName") String cacheName){
        cacheManager.getCache(cacheName).clear();
        return ResponseEntity.ok("Cleared...");
    }

    @GetMapping
    public List<Customer> getCustomers(){
       return customerService.getCustomers();
    }
}
