package com.compnay;

import java.util.Date;

public class Patient extends Person {

    private String patientID;
    private String doctorIncharge;
    private String timeSlot;
    private int cost;



    //Setters and getters


    //Constructor

    public Patient(String name, String surname, String dateofbirth, String mobilenumber, String patientID,
                   String doctorIncharge, String timeSlot, int cost) {

        super(name, surname, dateofbirth, mobilenumber);
        this.patientID = patientID;
        this.doctorIncharge = doctorIncharge;
        this.timeSlot = timeSlot;
        this.cost = cost;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCost(){
        return cost;
    }

    public void setDoctorIncharge(String doctorIncharge){
        this.doctorIncharge=doctorIncharge;
    }

    public String getDoctorIncharge(){
        return doctorIncharge;
    }

    public void setTimeSlot(String timeSlot){
        this.timeSlot = timeSlot;
    }

    public String getTimeSlot(){
        return timeSlot;
    }
}