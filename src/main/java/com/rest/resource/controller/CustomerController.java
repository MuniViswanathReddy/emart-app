package com.rest.resource.controller;

import com.rest.resource.dto.CustomerInfo;
import com.rest.resource.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @RequestMapping(path = "/customer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createCustomer(@RequestBody CustomerInfo customerInfo) {
        return customerService.customerAccountCreation(customerInfo);
    }

    @RequestMapping(path = "/customer/{mobile-no}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerInfo getCustomerByMobileNo(@PathVariable("mobile-no") String mobileNo) {
        return customerService.getCustomerInfo(mobileNo);
    }

    @RequestMapping(path = "/delete/{mobile-no}", method = RequestMethod.DELETE)
    public String deleteCustomerByMobileNo(@PathVariable("mobile-no") String mobileNo) {
        return customerService.deleteCustomerByMobileNo(mobileNo);
    }

    @RequestMapping(path = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String updateCustomer(@RequestBody CustomerInfo customerInfo) {
        return customerService.updateCustomerInfo(customerInfo);
    }

}

