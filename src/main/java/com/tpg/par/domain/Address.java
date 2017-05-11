package com.tpg.par.domain;

import java.util.Optional;

import static java.util.Optional.empty;

public class Address {
    private String lineOne;
    private Optional<String> lineTwo = empty();
    private String city;
    private String region;
    private String country;
    private String postCode;


    public Address(String lineOne, Optional<String> lineTwo, String city, String region, String country, String postCode) {
        this.lineOne = lineOne;
        this.lineTwo = lineTwo;
        this.city = city;
        this.region = region;
        this.country = country;
        this.postCode = postCode;
    }

    public String getLineOne() {
        return lineOne;
    }

    public Optional<String> getLineTwo() {
        return lineTwo;
    }

    public String getCity() {
        return city;
    }

    public String getRegion() {
        return region;
    }

    public String getCountry() {
        return country;
    }

    public String getPostCode() {
        return postCode;
    }
}
