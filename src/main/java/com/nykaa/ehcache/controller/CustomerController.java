package com.nykaa.ehcache.controller;

import com.nykaa.ehcache.data.Customer;
import com.nykaa.ehcache.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CacheManager cacheManager;

    @Autowired
    @Qualifier("caeffineCacheManager")
    CacheManager caeffineCM;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable String id){
        return customerService.getCustomer(id);
    }

    @GetMapping("/cache/clear/{name}")
    public ResponseEntity<String> clearCache(@PathVariable(name = "name") String cacheName){
        cacheManager.getCache(cacheName).clear();
        return ResponseEntity.ok("Cleared...");
    }

    @GetMapping("/customer/list")
    public List<Customer> getCustomers(){
       return customerService.getCustomers();
    }
}
