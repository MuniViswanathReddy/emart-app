package com.rest.resource.repository;

import com.rest.resource.entity.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    @Query(value = "from Customer where mobileNo=:phoneNo")
    public Customer findByPhoneNo(String phoneNo);

}
