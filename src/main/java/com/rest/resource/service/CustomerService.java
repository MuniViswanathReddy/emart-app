package com.rest.resource.service;

import com.rest.resource.dto.AddressInfo;
import com.rest.resource.dto.CustomerInfo;
import com.rest.resource.entity.Address;
import com.rest.resource.entity.Customer;
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

    @Transactional
    public String customerAccountCreation(CustomerInfo customerInfo) {
        Customer customer = new Customer();
        customer.setName(customerInfo.getName());
        customer.setEmail(customerInfo.getEmail());
        customer.setMobileNo(customerInfo.getPhone());
        customer.setPassword(customerInfo.getPassword());
        customer.setDob(customerInfo.getDob());
        customer.setGender(customerInfo.getGender());
        customerRepository.save(customer);

        List<AddressInfo> addressInfoList = customerInfo.getAddress();
        for (AddressInfo addressInfo : addressInfoList) {
            Address address = new Address();
            address.setAddressType(addressInfo.getAddressType());
            address.setStreet1(addressInfo.getStreet1());
            address.setStreet2(addressInfo.getStreet2());
            address.setCityOrTown(addressInfo.getCityOrTown());
            address.setDistrict(addressInfo.getDistrict());
            address.setState(addressInfo.getState());
            address.setCountry(addressInfo.getCountry());
            address.setPinCode(addressInfo.getPinCode());
            address.setCustomer(customer);
            addressRepository.save(address);
        }
        return "Customer Account Created";
    }

    @Transactional(readOnly = true)
    public CustomerInfo getCustomerInfo(String mobileNo) {
        Customer customer = customerRepository.findByPhoneNo(mobileNo);
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setName(customer.getName());
        customerInfo.setEmail(customer.getEmail());
        customerInfo.setGender(customer.getGender());
        customerInfo.setDob(customer.getDob());
        customerInfo.setPassword(customer.getPassword());
        customerInfo.setPhone(customer.getMobileNo());

        List<AddressInfo> addressInfoList = new ArrayList<>();

        List<Address> addresses = customer.getAddresses();
        for (Address address : addresses) {
            AddressInfo addressInfo = new AddressInfo();
            addressInfo.setAddressType(address.getAddressType());
            addressInfo.setStreet1(address.getStreet1());
            addressInfo.setStreet2(address.getStreet2());
            addressInfo.setCityOrTown(address.getCityOrTown());
            addressInfo.setDistrict(address.getDistrict());
            addressInfo.setState(address.getState());
            addressInfo.setCountry(address.getCountry());
            addressInfo.setPinCode(address.getPinCode());
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
        customer.setName(customerInfo.getName());
        customer.setEmail(customerInfo.getEmail());
        customer.setPassword(customerInfo.getPassword());
        customer.setDob(customerInfo.getDob());
        customer.setGender(customerInfo.getGender());

        List<AddressInfo> addressInfoList = customerInfo.getAddress();
        List<Address> addressList = customer.getAddresses();

        for (AddressInfo addressInfo : addressInfoList) {
            Optional<Address> addressOptional = addressList.stream().filter(address -> Objects.equals(address.getAddressType(), addressInfo.getAddressType())).findFirst();
            if (addressOptional.isPresent()) {
                Address address = addressOptional.get();
                address.setStreet1(addressInfo.getStreet1());
                address.setStreet2(addressInfo.getStreet2());
                address.setCityOrTown(addressInfo.getCityOrTown());
                address.setDistrict(addressInfo.getDistrict());
                address.setState(addressInfo.getState());
                address.setCountry(addressInfo.getCountry());
                address.setPinCode(addressInfo.getPinCode());
                address.setCustomer(customer);

            }
        }
        addressInfoList.removeIf(addressInfo -> addressList.stream().anyMatch(address -> Objects.equals(address.getAddressType(), addressInfo.getAddressType())));

        for (AddressInfo addressInfo : addressInfoList) {
            Address address = new Address();
            address.setAddressType(addressInfo.getAddressType());
            address.setStreet1(addressInfo.getStreet1());
            address.setStreet2(addressInfo.getStreet2());
            address.setCityOrTown(addressInfo.getCityOrTown());
            address.setDistrict(addressInfo.getDistrict());
            address.setState(addressInfo.getState());
            address.setCountry(addressInfo.getCountry());
            address.setPinCode(addressInfo.getPinCode());
            address.setCustomer(customer);
            addressList.add(address);
        }
        return "Customer Updated Successfully";
    }

}
