package com.project.moagym.domain.enums;

import lombok.Getter;

import javax.persistence.Embeddable;

//member의 address
@Embeddable
@Getter
public class Address {

    private String city;

    private String street;

    private String detail;

    private String zipcode;

    protected Address() {
    }

    public Address(String city, String street, String detail, String zipcode) {
        this.city = city;
        this.street = street;
        this.detail = detail;
        this.zipcode = zipcode;
    }
}

