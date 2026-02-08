package com.foodie.eatzy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private Long id;
    private String addressLine;
    private String city;
    private String state;
    private String pincode;
    private String country;
    private boolean isDefault;

}
