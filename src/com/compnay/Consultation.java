package com.compnay;

import java.util.Date;

public class Consultation {
    private Date dateconsultation;
    private String timeslot;
    private Double cost;
    private String specialnotes;
    private Doctor doctor;
    private Patient patient;

    public Consultation(Doctor doctor, Patient patient, Date dateconsultation, String timeslot, double cost, String specialnotes) {
        this.doctor = doctor;
        this.patient = patient;
        this.dateconsultation = dateconsultation;
        this.timeslot = timeslot;
        this.cost = cost;
        this.specialnotes = specialnotes;
    }

    //setters and getters
    public void setDateconsultation(Date dateconsultation) {
        this.dateconsultation = dateconsultation;
    }

    public Date getDateconsultation() {
        return dateconsultation;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }

    public String getTimeslot() {
        return timeslot;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }




}
