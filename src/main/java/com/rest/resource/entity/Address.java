package com.rest.resource.entity;

import com.rest.resource.dto.AddressInfo;
import jakarta.persistence.*;
import lombok.*;

import java.util.stream.DoubleStream;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
@EqualsAndHashCode(of = {"addressType"})
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private int addressId;
    @Column(name = "address_type")
    private String addressType;
    @Column(name = "street1 ")
    private String street1;
    @Column(name = "street2")
    private String street2;
    @Column(name = "city_or_town")
    private String cityOrTown;
    @Column(name = "district")
    private String district;
    @Column(name = "state")
    private String state;
    @Column(name = "country")
    private String country;
    @Column(name = "pin_code")
    private int pinCode;
    @ManyToOne
    @JoinColumn(name = "address_customer_id",nullable = true)
    private Customer customer;

}
