package com.rest.resource.entity;

import jakarta.persistence.*;
import lombok.*;

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
    public int addressId;
    @Column(name = "address_type")
    public String addressType;
    @Column(name = "street1 ")
    public String street1;
    @Column(name = "street2")
    public String street2;
    @Column(name = "city_or_town")
    public String cityOrTown;
    @Column(name = "district")
    public String district;
    @Column(name = "state")
    public String state;
    @Column(name = "country")
    public String country;
    @Column(name = "pin_code")
    public int pinCode;
    @ManyToOne
    @JoinColumn(name = "address_customer_id", nullable = true)
    public Customer customer;
}
