package com.rest.resource.mappers;

import com.rest.resource.dto.AddressInfo;
import com.rest.resource.dto.CustomerInfo;
import com.rest.resource.entity.Address;
import com.rest.resource.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer mapCustomerEntity(CustomerInfo customerInfo){
        Customer customer = new Customer();
        customer.setName(customerInfo.getName());
        customer.setEmail(customerInfo.getEmail());
        customer.setMobileNo(customerInfo.getPhone());
        customer.setPassword(customerInfo.getPassword());
        customer.setDob(customerInfo.getDob());
        customer.setGender(customerInfo.getGender());
        return customer;
    }

    public Address mapAddressEntity(AddressInfo addressInfo){
        Address address = new Address();
        address.setAddressType(addressInfo.getAddressType());
        address.setStreet1(addressInfo.getStreet1());
        address.setStreet2(addressInfo.getStreet2());
        address.setCityOrTown(addressInfo.getCityOrTown());
        address.setDistrict(addressInfo.getDistrict());
        address.setState(addressInfo.getState());
        address.setCountry(addressInfo.getCountry());
        address.setPinCode(addressInfo.getPinCode());
       return address;
    }

    public CustomerInfo mapCustomerInfo(Customer customer){
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setName(customer.getName());
        customerInfo.setEmail(customer.getEmail());
        customerInfo.setGender(customer.getGender());
        customerInfo.setDob(customer.getDob());
        customerInfo.setPassword(customer.getPassword());
        customerInfo.setPhone(customer.getMobileNo());
        return customerInfo;
    }
    public AddressInfo mapAddressInfo(Address address){
        AddressInfo addressInfo = new AddressInfo();
        addressInfo.setAddressType(address.getAddressType());
        addressInfo.setStreet1(address.getStreet1());
        addressInfo.setStreet2(address.getStreet2());
        addressInfo.setCityOrTown(address.getCityOrTown());
        addressInfo.setDistrict(address.getDistrict());
        addressInfo.setState(address.getState());
        addressInfo.setCountry(address.getCountry());
        addressInfo.setPinCode(address.getPinCode());
        return addressInfo;
    }

    public Customer mapCustome(Customer customer,CustomerInfo customerInfo){
        customer.setName(customerInfo.getName());
        customer.setEmail(customerInfo.getEmail());
        customer.setPassword(customerInfo.getPassword());
        customer.setDob(customerInfo.getDob());
        customer.setGender(customerInfo.getGender());
        return customer;
    }

    public Address mapAddress(Address address,AddressInfo addressInfo){
        address.setStreet1(addressInfo.getStreet1());
        address.setStreet2(addressInfo.getStreet2());
        address.setCityOrTown(addressInfo.getCityOrTown());
        address.setDistrict(addressInfo.getDistrict());
        address.setState(addressInfo.getState());
        address.setCountry(addressInfo.getCountry());
        address.setPinCode(addressInfo.getPinCode());
        return address;
    }
}
