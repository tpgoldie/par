package com.tpg.par.domain;

public class ContactDetails {
    private Address address;
    private String emailAddress;
    private String contactNumber;

    ContactDetails(Address address, String emailAddress, String contactNumber) {
        this.address = address;
        this.emailAddress = emailAddress;
        this.contactNumber = contactNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public Address getAddress() {
        return address;
    }

    public static class Builder {
        private Address address;
        private String emailAddress;
        private String contactNumber;

        public Builder address(Address value) {
            address = value;
            return this;
        }

        public Builder emailAddress(String value) {
            emailAddress = value;
            return this;
        }

        public Builder contactNumber(String value) {
            contactNumber = value;
            return this;
        }

        public ContactDetails build() {
            return new ContactDetails(address, emailAddress, contactNumber);
        }
    }
}
