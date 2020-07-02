package com.microservice.microserviceexample;

import java.util.List;

import com.microservice.Customer;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;

//import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(url="localhost:8899" , name="customer-service")
//@FeignClient(name="customer-service")
//@RibbonClient(name="customer-service")
public interface CustomerServiceProxy {

    @GetMapping("/customers")
    public List<Customer> getAllCustomers();

    @GetMapping("/customers/{customer_id}")
    public Customer getCustomerDetails(@PathVariable("customer_id") Long customer_id);

}