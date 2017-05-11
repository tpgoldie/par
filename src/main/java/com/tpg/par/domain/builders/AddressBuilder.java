package com.tpg.par.domain.builders;

import com.tpg.par.domain.Address;

import static java.util.Optional.empty;

public class AddressBuilder {
    private String lineOne;
    private String city;
    private String region;
    private String country;
    private String postCode;

    public AddressBuilder lineOne(String value) {
        lineOne = value;
        return this;
    }

    public AddressBuilder city(String value) {
        city = value;
        return this;
    }

    public AddressBuilder region(String value) {
        region = value;
        return this;
    }

    public AddressBuilder country(String value) {
        country = value;
        return this;
    }

    public AddressBuilder postCode(String value) {
        postCode = value;
        return this;
    }

    public Address build() {
        return new Address(lineOne, empty(), city, region, country, postCode);
    }
}
