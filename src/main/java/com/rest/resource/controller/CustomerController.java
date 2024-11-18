package com.rest.resource.controller;

import com.rest.resource.dto.CustomerInfo;
import com.rest.resource.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createCustomer(@RequestBody CustomerInfo customerInfo) {
        return customerService.customerAccountCreation(customerInfo);
    }

    @RequestMapping(path = "/{mobile-no}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerInfo getCustomerByMobileNo(@PathVariable("mobile-no") String mobileNo) {
        return customerService.getCustomerInfo(mobileNo);
    }

    @RequestMapping(path = "{mobile-no}", method = RequestMethod.DELETE)
    public String deleteCustomerByMobileNo(@PathVariable("mobile-no") String mobileNo) {
        return customerService.deleteCustomerByMobileNo(mobileNo);
    }

    @RequestMapping(path = "/update-customer", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String updateCustomer(@RequestBody CustomerInfo customerInfo) {
        return customerService.updateCustomerInfo(customerInfo);
    }

    @RequestMapping(path = "/update-existing-address", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String updateExistingAddress(@RequestBody CustomerInfo customerInfo) {
        return customerService.updateExistingAddress(customerInfo);
    }

    @RequestMapping(path = "/update-new-address/{mobile-no}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String updateNewAddress(@RequestBody CustomerInfo customerInfo,@PathVariable("mobile-no") String mobileNo) {
        return customerService.updateNewAddress(customerInfo,mobileNo);
    }

    @RequestMapping(path="/{mobile-no}/{address-type}",method=RequestMethod.DELETE)
    public String deleteExistingAddress(@PathVariable("mobile-no") String mobileNo,@PathVariable("address-type") String AddressType){
       return customerService.deleteExistingAddress(mobileNo,AddressType);
    }
}

