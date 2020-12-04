package com.sayan.microservices.demospringbootmicroservices.endpoints;

import com.sayan.microservices.demospringbootmicroservices.service.CustomerService;
import com.sayan.microservices.demospringbootmicroservices.utils.ApplicationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApplicationConstants.AUTH_V1)
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService=customerService;
    }
}
