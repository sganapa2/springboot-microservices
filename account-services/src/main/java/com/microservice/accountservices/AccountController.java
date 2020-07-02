package com.microservice.accountservices;

import com.microservice.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@EnableSwagger2
public class AccountController {


    private List<Account> listOfAccounts = new ArrayList<>(5);

    /*@Autowired
    RestTemplate restTemplate;
    */

    @Autowired
    CustomerServiceProxy customerServiceProxy;

    public AccountController(){
        Account account1 = new Account(new Long(1), null, new Double(1111.50));
        Account account2 = new Account(new Long(2), null, new Double(1112.50));
        Account account3 = new Account(new Long(3), null, new Double(1113.50));
        this.listOfAccounts.add(account1);
        this.listOfAccounts.add(account2);
        this.listOfAccounts.add(account3);
    }

    @GetMapping("/accounts")
    public List<Account> getAllCustomers(){
        return this.listOfAccounts;
    }

    @GetMapping("/accounts/{account_id}")
    public Account getCustomerDetails(@PathVariable Long account_id) {
        Account account = this.listOfAccounts.stream().filter(acc -> acc.getId().equals(account_id)).findAny().orElse(null);
        Map<String, Long> params = new HashMap<>();
        params.put("customer_id", account.getId());
        /*Customer customer = restTemplate.getForObject("http://localhost:8899/customers/{customer_id}" , Customer.class, params);
        */

        //Customer customer = customerServiceProxy.getForObject("http://localhost:8899/customers/{customer_id}" , Customer.class, params);
        Customer customer = customerServiceProxy.getCustomerDetails(account_id);

        System.out.println("************** Cust found : "+customer);
        account.setCustomer(customer);
        return account;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // OR else we can use builder
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // Do any additional configuration here
        return builder.build();
    }

}