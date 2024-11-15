package com.rest.resource.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
@EqualsAndHashCode(of = {"addressType"})
public class AddressInfo {
    public String addressType;
    public String street1;
    public String street2;
    public String cityOrTown;
    public String district;
    public String state;
    public String country;
    public int pinCode;
}
