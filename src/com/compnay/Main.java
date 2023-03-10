package com.compnay;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();


        Scanner scan = new Scanner(System.in);


        while (true) {

            System.out.println("Welcome to the Westminster Skin Care !");
            System.out.println("__Please select an Option__");
            System.out.println("                                               ");

            System.out.println("1. Add a doctor");
            System.out.println("2. Remove a doctor");
            System.out.println("3. Print saved doctors");
            System.out.println("4. Exit and save");
            System.out.println("5. Review saved data");
            System.out.println("6. To display the GUI of this application");

            System.out.print("Enter your choice: ");
            int choice = scan.nextInt();

            switch (choice) {
                case 1:

                    System.out.print("Enter First name: ");
                    String name = scan.next();

                    System.out.print("Enter surname: ");
                    String surname = scan.next();

                    System.out.println("Enter your Date of Birth:(dd/MM/yyyy)");
                    String dateOfBirth = scan.next();
                    CheckForDate(dateOfBirth);


                    System.out.println("Enter your mobile number: ");
                    String mobileNumber = scan.next();
                    CheckPhoneNumber(mobileNumber);

                    System.out.print("Enter medical license number: ");
                    String medicalLicenseNumber = scan.next();

                    System.out.print("Enter specialisation: ");
                    String specialisation = scan.next();

                    System.out.println("Enter the available date(dd/MM/yyyy)");
                    String availableDate = scan.next();
                    CheckForDate(availableDate);

                    System.out.println("Enter the available Time slot");
                    String availableTime = scan.next();

                    Doctor doctor = new Doctor(name, surname, dateOfBirth,mobileNumber, medicalLicenseNumber, specialisation, availableDate,availableTime);
                    manager.addDoctor(doctor);
                    break;
                case 2:
                    System.out.print("Enter medical license number: ");
                    medicalLicenseNumber = scan.next();
                    manager.deleteDoctor(medicalLicenseNumber);
                    break;
                case 3:
                    manager.printDoctors();
                    break;
                case 4:
                    manager.saveData();
                    return;
                case 5:
                    manager.readData();
                    return;
                case 6:
                    CunsultGUI GUI = new CunsultGUI();
                    return;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    //Check if the entered date is of the proper format
    public static void CheckForDate(String date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateToCheck = date;
        simpleDateFormat.setLenient(false);
        try{
            simpleDateFormat.parse(dateToCheck);
        }catch (ParseException ex) {
            System.out.println("Add date in the proper order");
            System.out.println("Enter date of birth: ");

        }
    }

    //Check if the entered number is a valid number
    public static void CheckPhoneNumber(String number){
        if(number.length()>10){
            System.out.println("Invalid Number format!");
            System.out.println("Enter your mobile number: ");


        }else{
            for(int i=0; i<number.length(); i++){
                if(!Character.isDigit(number.charAt(i))){
                    System.out.println("Invalid format");
                    System.out.println("Enter your number");
                    break;
                }
            }
        }
    }
}

