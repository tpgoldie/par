package com.tpg.par.domain;

public interface ContactDetailsFixture {
    default ContactDetails buildContactDetails(Address address, String contactNumber, String emailAddress) {
        return new ContactDetails.Builder()
                .address(address)
                .contactNumber(contactNumber)
                .emailAddress(emailAddress)
                .build();
    }
}
