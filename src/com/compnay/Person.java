package com.compnay;

public class Person {

    // Properties of the Person - Variables

    private String name;
    private String surname;
    private String dateOfBirth;
    private String mobileNumber;

    // Constructor

    public Person(String name, String surname, String dateOfBirth, String mobileNumber) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.mobileNumber = mobileNumber;
    }

    // Setters and getters

    public void setName(String newName) {
        this.name = newName;
    }
    public String getName() {
        return name;
    }

    public void setSurname(String newSurname) {
        this.surname = newSurname;
    }
    public String getSurname() {
        return surname;
    }

    public void setDateOfBirth(String newDateOfBirth) {
        this.dateOfBirth = newDateOfBirth;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setMobileNumber(String newMobileNumber) {
        this.mobileNumber = newMobileNumber;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }


}

