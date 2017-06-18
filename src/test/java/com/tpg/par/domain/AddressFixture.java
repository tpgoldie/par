package com.tpg.par.domain;

public interface AddressFixture {
    default Address buildAddress(String lineOne, String city, String region, String country, String postCode) {
        Address.Builder addressBuilder = new Address.Builder();

        return addressBuilder
                .lineOne(lineOne)
                .city(city)
                .region(region)
                .country(country)
                .postCode(postCode)
                .build();
    }
}
