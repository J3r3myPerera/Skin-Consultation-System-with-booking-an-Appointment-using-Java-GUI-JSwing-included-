package com.compnay;

import java.io.*;
import java.util.*;

import static java.util.Arrays.sort;

public class WestminsterSkinConsultationManager implements SkinConsultationManager {

    public static LinkedList<Doctor> doctorsList = new LinkedList<>();
    public static LinkedList<Patient> patientList = new LinkedList<>();

    //Implements addDoctor
    @Override
    public void addDoctor(Doctor doctor) {

        if (doctorsList.size() < 10) {
            doctorsList.add(doctor);
            System.out.println("Doctor added successfully.");
        } else {
            System.out.println("Cannot add more doctors. The consultation center can only allocate a maximum of 10 doctors.");
        }


    }

    //Implements Deleting doctor
    @Override
    public void deleteDoctor(String medicalLicenseNumber) {
        for (int i = 0; i < doctorsList.size(); i++) {
            Doctor doctor = doctorsList.get(i);
            if (doctor.getMedicalLicenseNumber().equals(medicalLicenseNumber)) {
                doctorsList.remove(doctor);
                System.out.println("Doctor with medical license number " + medicalLicenseNumber + " has been deleted.");
                break;
            }
        }
        System.out.println("Doctor with medical license number " + medicalLicenseNumber + " not found.");
    }


    //Implements the printing of list of doctors
    @Override


        public void printDoctors(){

        //Using the collection methos through the priciple of encapsulation
            Collections.sort(doctorsList, new Comparator<Doctor>() {
                @Override
                public int compare(Doctor o1, Doctor o2) { //To compare the two strings and get them in the alphabetical order
                    return String.valueOf(o1.getSurname()).compareTo(o2.getSurname());
                }
            });
            //Printing out the data in the ArrayList
            System.out.println("Doctors: ");
            for (Doctor doctor : doctorsList) {
                System.out.println("Name: " + doctor.getName() + " " + doctor.getSurname());
                System.out.println("Medical License Number: " + doctor.getMedicalLicenseNumber());
                System.out.println("Specialisation: " + doctor.getSpecialisation());
                System.out.println("-------------------");
            }
        }




    //Implements save in a file
    @Override
    public void saveData() throws IOException, ClassNotFoundException {

//        creating the outputFile object
        File outputFile = new File("Doctor.txt");
        try {
            //Create stream - file is created
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));

            //Sedn data through the file writer
            bw.write(String.valueOf(doctorsList));
            //Close the writer
            bw.close();
            System.out.println("Data saved.");
        }catch(IOException e){
            System.out.println("File not saved.");
        }
//
    }

    @Override
    public void readData() throws IOException {

        try {
            //list that holds the strings of a file
            List<String> listOfStrings = new ArrayList<String>();
            //load data from the file
            BufferedReader bf = new BufferedReader(new FileReader("Doctor.txt"));
            //read entire line as a string
            String line = bf.readLine();
            //checking for the end of the file
            while (line != null) {
                listOfStrings.add(line);
                line = bf.readLine();
            }
            //closing the buffer object
            bf.close();
            //storing the data in the arrayList to an array
            String[] array = listOfStrings.toArray(new String[0]);
            //printing each line of file which is stored as an array
            for (String str : array) {
                System.out.println(str);
            }
            //catchin the erroe if there is no file already there
        }catch(IOException e){
            System.out.println("File not found.");
        }
//

    }
    //get method and set method to acces the Doctor LinkedList for the GUI
    public LinkedList<Doctor> getDoctorsList(){
        return doctorsList;
    }

    public void setDoctorsList(LinkedList<Doctor> doctorsList) {
        this.doctorsList = doctorsList;
    }

    //Get method and set method to access the patient linked list for the GUI
    public LinkedList<Patient> getPatientList(){
        return patientList;
    }
    public void setPatientList(LinkedList<Patient> patientList) {
        this.getPatientList();
    }
}
