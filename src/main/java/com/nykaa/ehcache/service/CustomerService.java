package com.nykaa.ehcache.service;

import com.nykaa.ehcache.data.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CacheManager cacheManager;

    private static final Logger LOG = LoggerFactory.getLogger(CustomerService.class);

    @Cacheable(cacheNames = "customerCache", key="#id")
    public Customer getCustomer(String id){
        LOG.info("Returning customer information for customer id {} ",id);
        Customer customer = new Customer();
        customer.setCustomerId(id);
        customer.setFirstName("Test");
        customer.setLastName("User");
        customer.setEmail("abc@nykaa.com");
        return  customer;
    }

    @Cacheable(cacheNames = "customerList",key = "'customerList'")
    public List<Customer> getCustomers(){
        List<Customer> customers = new ArrayList<>();
        LOG.info("Returning customer list");
        for(int i=0; i< 4; i++){
            Customer customer = new Customer();
            customer.setCustomerId(String.valueOf(i));
            customer.setFirstName("FirstName"+i);
            customer.setLastName("LastName"+i);
            customer.setEmail("abc@nykaa.com"+i);
            customers.add(customer);
        }
        return customers;
    }
}
