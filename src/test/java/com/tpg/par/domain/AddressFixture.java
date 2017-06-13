package com.tpg.par.domain;

import com.tpg.par.domain.builders.AddressBuilder;

public interface AddressFixture {
    default Address buildAddress(String lineOne, String city, String region, String country, String postCode) {
        AddressBuilder addressBuilder = new AddressBuilder();

        return addressBuilder
                .lineOne(lineOne)
                .city(city)
                .region(region)
                .country(country)
                .postCode(postCode)
                .build();
    }
}
