package com.compnay;

import java.util.Date;

public class Doctor extends Person {

    private String medicalLicenseNumber;
    private String specialisation;

    private String availableDate;

    private String availableTime;


    public Doctor(String name, String surname, String dateOfBirth, String mobileNumber,  String medicalLicenseNumber, String specialisation, String availableDate, String availableTime) {
        super(name, surname,dateOfBirth, mobileNumber);
        this.medicalLicenseNumber = medicalLicenseNumber;
        this.specialisation = specialisation;
        this.availableDate = availableDate;
        this.availableTime = availableTime;
    }
    // Setters and getters

    public void setMedicalLicenseNumber(String medicalLicenseNumber) {
        this.medicalLicenseNumber = medicalLicenseNumber;
    }
    public String getMedicalLicenseNumber() {
        return medicalLicenseNumber;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }
    public String getSpecialisation() {
        return specialisation;
    }

    public String getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(String availableDate) {
        this.availableDate = availableDate;
    }

    public String getAvailableTime(){
        return availableTime;
    }

    public void setAvailableTime(String availableTime){
        this.availableTime = availableTime;
    }

    public int compareToIgnoreCase(Doctor s2) {
        return 0;
    }
}
