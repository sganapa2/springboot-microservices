package com.microservice.microserviceexample;

import com.microservice.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class CustomerController {
    private List<Customer> customersList = new ArrayList<Customer>(5);

    public CustomerController() {
        Customer customer1 = new Customer(new Long(1), "Santosh Ganapa", "Pune");
        Customer customer2 = new Customer(new Long(2), "Satish Ganapa", "Solapur");
        Customer customer3 = new Customer(new Long(3), "Vedant Ganapa", "Mumbai");

        this.customersList.add(customer1);
        this.customersList.add(customer2);
        this.customersList.add(customer3);

    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return this.customersList;
    }

    @GetMapping("/customers/{customer_id}")
    public Customer getCustomerDetails(@PathVariable Long customer_id) {
        return this.customersList.stream().filter(customer -> customer.getId().equals(customer_id)).findAny().get();
    }
}
