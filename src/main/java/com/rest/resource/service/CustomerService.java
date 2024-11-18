package com.rest.resource.service;

import com.rest.resource.dto.AddressInfo;
import com.rest.resource.dto.CustomerInfo;
import com.rest.resource.entity.Address;
import com.rest.resource.entity.Customer;
import com.rest.resource.mappers.CustomerMapper;
import com.rest.resource.repository.AddressRepository;
import com.rest.resource.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Transactional
    public String customerAccountCreation(CustomerInfo customerInfo) {
        Customer customer = customerMapper.mapCustomerEntity(customerInfo);
        customerRepository.save(customer);

        List<AddressInfo> addressInfoList = customerInfo.getAddress();
        for (AddressInfo addressInfo : addressInfoList) {
            Address address = customerMapper.mapAddressEntity(addressInfo);
            address.setCustomer(customer);
            addressRepository.save(address);
        }
        return "Customer Account Created";
    }

    @Transactional(readOnly = true)
    public CustomerInfo getCustomerInfo(String mobileNo) {
        Customer customer = customerRepository.findByPhoneNo(mobileNo);
        CustomerInfo customerInfo = customerMapper.mapCustomerInfo(customer);

        List<AddressInfo> addressInfoList = new ArrayList<>();

        List<Address> addresses = customer.getAddresses();
        for (Address address : addresses) {
            AddressInfo addressInfo = customerMapper.mapAddressInfo(address);
            addressInfoList.add(addressInfo);
        }
        customerInfo.setAddress(addressInfoList);
        return customerInfo;
    }


    @Transactional
    public String deleteCustomerByMobileNo(String mobileNo) {
        Customer customer = customerRepository.findByPhoneNo(mobileNo);
        customerRepository.deleteById(customer.getId());
        return "Customer Deleted Successfully";
    }

    @Transactional
    public String updateCustomerInfo(CustomerInfo customerInfo) {
        Customer customer = customerRepository.findByPhoneNo(customerInfo.getPhone());
        if (customer == null) {
            throw new RuntimeException("customer not found");
        } else {
            customerMapper.mapCustome(customer, customerInfo);
        }
        return "Customer Updated Successfully";
    }

    @Transactional
    public String updateExistingAddress(CustomerInfo customerInfo) {
        Customer customer = customerRepository.findByPhoneNo(customerInfo.getPhone());
        if (customer == null) {
            throw new RuntimeException("Address not found with phone no: " + customerInfo.getPhone());
        } else {
            List<AddressInfo> addressInfoList = customerInfo.getAddress();
            List<Address> addressList = customer.getAddresses();

            for (AddressInfo addressInfo : addressInfoList) {
                Optional<Address> addressOptional = addressList.stream().filter(address -> Objects.equals(address.getAddressType(), addressInfo.getAddressType())).findFirst();
                if (addressOptional.isPresent()) {
                    Address address = addressOptional.get();
                    customerMapper.mapAddress(address, addressInfo);
                }
            }
        }
        return "Address Updated Successfully";
    }

    @Transactional
    public String updateNewAddress(CustomerInfo customerInfo,String mobileNo) {
        System.out.println(customerInfo.toString());
        Customer customer = customerRepository.findByPhoneNo(mobileNo);
        System.out.println(customer.toString());
        if (customer == null) {
            throw new RuntimeException("Customer not found with phone no: " + customerInfo.getPhone());
        }
        else {
            List<AddressInfo> addressInfoList = customerInfo.getAddress();
            List<Address> addressList = customer.getAddresses();
            addressInfoList.removeIf(addressInfo -> addressList.stream().anyMatch(address -> Objects.equals(address.getAddressType(), addressInfo.getAddressType())));          for (AddressInfo addressInfo : addressInfoList) {
                for (AddressInfo addressinfo : addressInfoList) {
                    Address address = customerMapper.mapAddressEntity(addressInfo);
                    address.setCustomer(customer);
                    addressList.add(address);
                }
            }
        }
        return "Address Updated Successfully";
    }

    @Transactional
    public String deleteExistingAddress(String mobileNo,String addressType){
        Customer customer=customerRepository.findByPhoneNo(mobileNo);
        List<Address> addressList=customer.getAddresses();
        Optional<Address> addressOptional=addressList.stream().filter(address->address.getAddressType().equals(addressType)).findAny();
       if (addressOptional.isPresent()) {
            addressList.remove(addressOptional.get());
        }else{
            throw new RuntimeException("Address not found with Address Type: " + addressType);
        }
        return "Address Deleted Successfully";
    }
}
