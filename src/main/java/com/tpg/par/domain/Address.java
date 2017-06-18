package com.tpg.par.domain;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

public class Address {
    private String lineOne;
    private Optional<String> lineTwo = empty();
    private String city;
    private String region;
    private String country;
    private String postCode;


    Address(String lineOne, Optional<String> lineTwo, String city, String region, String country, String postCode) {
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

    public static class Builder {
        private String lineOne;
        private Optional<String> lineTwo = empty();
        private String city;
        private String region;
        private String country;
        private String postCode;

        public Builder lineOne(String value) {
            lineOne = value;
            return this;
        }

        public Builder lineTwo(String value) {
            lineTwo = of(value);
            return this;
        }

        public Builder city(String value) {
            city = value;
            return this;
        }

        public Builder region(String value) {
            region = value;
            return this;
        }

        public Builder country(String value) {
            country = value;
            return this;
        }

        public Builder postCode(String value) {
            postCode = value;
            return this;
        }

        public Address build() {
            return new Address(lineOne, lineTwo, city, region, country, postCode);
        }
    }
}
