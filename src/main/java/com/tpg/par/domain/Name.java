package com.tpg.par.domain;

public class Name {
    private String firstName;
    private String surname;

    public Name(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }


    public static class Builder {
        private String firstName;
        private String surname;

        public Builder firstName(String value) {
            firstName = value;

            return this;
        }

        public Builder surname(String value) {
            surname = value;

            return this;
        }

        public Name build() {
            return new Name(firstName, surname);
        }
    }
}
