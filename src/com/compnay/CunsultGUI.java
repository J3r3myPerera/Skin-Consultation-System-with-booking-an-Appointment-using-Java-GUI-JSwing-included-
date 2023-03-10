package com.compnay;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CunsultGUI extends JFrame  {
    WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();
        //Defining the frames, panels, labels, textfields and the buttons
      JFrame frame;
      JFrame frame2;
      JPanel panel;
      JPanel panel2;
      JLabel selectDoctor;
      JLabel selectTimeFrame;
      JLabel doctorsList;
      JLabel patientFName;
      JLabel patientLName;
      JLabel patientID;
      JLabel patientDOB;
      JLabel patientMobileNo;
      JLabel cost;
      JLabel notes;
      JLabel topicLabel;
      JLabel columnLabel;
      JLabel columnLabel2;

      JTextField doctorsName;
      JTextField timeFrame;
      JTextField patientFName1;
      JTextField patientLName1;
      JTextField patientID1;
      JTextField patientDOB1;
      JTextField patientMobileNo1;
      JTextField noOfHours;
      JTextField givenNotes;

      JButton sort;
      JButton check;
      JButton addImage;
      JButton submit;
      JButton calculate;
      JButton checkAvailability;

      JTable doctorsTable;
      JTable patientTable;









    public CunsultGUI() {



        //Defining the labels and the text fields in the GUI
        this.frame = new JFrame("Westminster Skin Care");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(1350, 800);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.selectDoctor = new JLabel("Select Doctor: ");
        this.selectDoctor.setBounds(10, 10, 100, 25);
        this.panel.add(selectDoctor);


        this.doctorsName = new JTextField(10);
        this.doctorsName.setBounds(10, 50, 100, 25);
        this.panel.add(doctorsName);

        this.selectTimeFrame = new JLabel("Select time Date:");
        this.selectTimeFrame.setBounds(200, 10, 100, 25);
        this.panel.add(selectTimeFrame);

        this.timeFrame = new JTextField(10);
        this.timeFrame.setBounds(200, 50, 100, 25);
        this.panel.add(timeFrame);

        this.checkAvailability = new JButton("Check");
        this.checkAvailability.setBounds(305,50,75,25);
        checkAvailability.addActionListener(new ActionListener() {
            //To check if the entered date is valid
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String checkDate = timeFrame.getText();//Getting the date of birth which was entered
                sdf.setLenient(false);//strick passing
                try{
                    sdf.parse(checkDate);
                    String choice = timeFrame.getText();
                    //check if the doctors is already booked and if booked give a random assign
                    for(int i =0; i< manager.doctorsList.size(); i++){
                        String checkdoc = String.valueOf(manager.getDoctorsList().get(i).getAvailableDate());
                        if (checkdoc == choice){
                            int element = (int)(Math.random() * (manager.doctorsList.size() +1)) ;
                            doctorsName.setText(String.valueOf(manager.doctorsList.get(element)));
                        }
                    }
                }catch (ParseException ex){
                    //if the date is not in the format to enter it again
                    JOptionPane.showMessageDialog(frame,"Use the proper format for entry!");
                }
            }
        });
        this.panel.add(checkAvailability);

        this.doctorsList = new JLabel("Doctors List: ");
        this.doctorsList.setBounds(400, 10, 100, 25);
        this.panel.add(doctorsList);

        //For the doctors table
        String[] column = {"Surname", "First Name", "Specialisation", "Medical License No.", "Date", "Time Slot"};
        DefaultTableModel docTable = new DefaultTableModel(column, 0);
        doctorsTable = new JTable(docTable);
        doctorsTable.setBounds(450, 60, 900, 100);
        doctorsTable.setAutoCreateRowSorter(true);
        //Getting the doctors detials from the Doctors array
        for (int i = 0; i < manager.getDoctorsList().size();  i++) {
            String sName = manager.getDoctorsList().get(i).getSurname();
            String fName = manager.getDoctorsList().get(i).getName();
            String specialisation = manager.getDoctorsList().get(i).getSpecialisation();
            String medicalNo = manager.getDoctorsList().get(i).getMedicalLicenseNumber();
            String dateAvailable = manager.getDoctorsList().get(i).getAvailableDate();
            String timeAvailable = manager.getDoctorsList().get(i).getAvailableTime();


            Object[] row = {sName, fName, specialisation, medicalNo, dateAvailable, timeAvailable};
            docTable.addRow(row);
        }

        this.columnLabel = new JLabel();
        this.columnLabel.setText("Surname                                | First Name                             |Specialisation                         | Medical License No.            |Date Available                  |Time available");
        this.columnLabel.setBounds(450, 37, 900, 25);
        this.panel.add(columnLabel);
        doctorsTable.setShowGrid(true);
        doctorsTable.setGridColor(Color.BLACK);
        doctorsTable.setVisible(true);
        this.panel.add(doctorsTable);


        //to sort the doctors in alphabetical order
        this.sort = new JButton("Sort");
        this.sort.setBounds(440, 165, 75, 20);
        sort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doctorsTable.getRowSorter().toggleSortOrder(0);
//
            }
        });
        this.panel.add(sort);


        this.patientFName = new JLabel("Patient First Name:");
        this.patientFName.setBounds(10, 90, 120, 25);
        this.panel.add(patientFName);

        this.patientFName1 = new JTextField();
        this.patientFName1.setBounds(10, 130, 100, 25);
        this.panel.add(patientFName1);

        this.patientLName = new JLabel("Patient Surname Name:");
        this.patientLName.setBounds(200, 90, 150, 25);
        this.panel.add(patientLName);

        this.patientLName1 = new JTextField();
        this.patientLName1.setBounds(200, 130, 100, 25);
        this.panel.add(patientLName1);

        this.patientDOB = new JLabel("Patient Date of Birth (dd/MM/yyyy):");
        this.patientDOB.setBounds(10, 170, 200, 25);
        this.panel.add(patientDOB);

        this.patientDOB1 = new JTextField();
        this.patientDOB1.setBounds(10, 210, 100, 25);
        this.panel.add(patientDOB1);

        this.check = new JButton("Check");
        this.check.setBounds(115, 210, 75, 25);
        check.addActionListener(new ActionListener() {
            @Override
            //to check if it is in the correct format
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String checkDate = patientID1.getText();//Getting the date of birth which was entered
                sdf.setLenient(false);//strick passing
                try{
                    sdf.parse(checkDate);
                }catch (ParseException ex){
                    JOptionPane.showMessageDialog(frame,"Use the proper format for entry!");
                }
            }
        });
        this.panel.add(check);


        this.patientMobileNo = new JLabel("Mobile number:");
        this.patientMobileNo.setBounds(200, 170, 100, 25);
        this.panel.add(patientMobileNo);

        this.patientMobileNo1 = new JTextField();
        this.patientMobileNo1.setBounds(200, 210, 100, 25);
        this.panel.add(patientMobileNo1);

        this.patientID = new JLabel("ID No.");
        this.patientID.setBounds(400, 170, 100, 25);
        this.panel.add(patientID);

        this.patientID1 = new JTextField();
        this.patientID1.setBounds(400, 210, 100, 25);
        this.panel.add(patientID1);

        this.cost = new JLabel("Cost");
        this.cost.setBounds(10, 250, 200, 25);
        this.panel.add(cost);

        this.noOfHours = new JTextField();
        this.noOfHours.setBounds(10, 290, 100, 25);
        this.panel.add(noOfHours);

        this.calculate = new JButton("Calculate");
        this.calculate.setBounds(13, 330, 90, 25);
        calculate.addActionListener(new ActionListener() {
            //to calculate the payment
            @Override
            public void actionPerformed(ActionEvent e) {
                int amount = Integer.parseInt(noOfHours.getText());
                int totalAmount = amount * 15;
                JOptionPane.showMessageDialog(frame, "The cost of the payment is: £" + totalAmount);
                saveData(totalAmount);
            }
        });
        this.panel.add(calculate);

        this.notes = new JLabel("Notes(Add image possible)");
        this.notes.setBounds(400, 250, 200, 25);
        this.panel.add(notes);

        this.givenNotes = new JTextField();
        this.givenNotes.setBounds(400, 290, 200, 100);
        this.panel.add(givenNotes);

        //To add an image to the system
        this.addImage = new JButton("Add image");
        this.addImage.setBounds(400, 395, 100, 25);
        addImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Encryption
                String key = "Bar12345Bar12345"; // 128 bit key
                // String key = "Bar12345Bar12345Bar12345";
                // String key = "Bar12345Bar12345Bar12345Bar12345";
                FileInputStream inFile = null;
                FileOutputStream outFile = null;
                try {
                    inFile = new FileInputStream("imageinput.png");
                    outFile = new FileOutputStream("imageoutput.png");
                    Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
                    Cipher cipher = Cipher.getInstance("AES");
                    cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                    byte[] input = new byte[64];
                    int bytesRead;
                    while ((bytesRead = inFile.read(input)) != -1) {
                        byte[] output = cipher.update(input, 0, bytesRead);
                        outFile.write(output);
                    }
                    byte[] output = cipher.doFinal();
                    outFile.write(output);
                    inFile.close();
                    outFile.flush();
                    outFile.close();
                    System.out.println("File Encrypted.");
                }catch (NoSuchAlgorithmException | NoSuchPaddingException
                        | InvalidKeyException | IOException
                        | IllegalBlockSizeException | BadPaddingException ex) {
                    System.out.println(ex.getMessage());
                }
                JFileChooser filechooser = new JFileChooser();
                filechooser.showOpenDialog(null);
                File f = filechooser.getSelectedFile();

                //Decryption
                String enkey = "Bar12345Bar12345"; // 128 bit key
                // String key = "Bar12345Bar12345Bar12345";
                // String key = "Bar12345Bar12345Bar12345Bar12345";
                FileInputStream eninFile = null;
                FileOutputStream enoutFile = null;
                try {
                    eninFile = new FileInputStream("output.png");
                    enoutFile = new FileOutputStream("decrypted.png");
                    Key ensecretKey = new SecretKeySpec(key.getBytes(), "AES");
                    Cipher cipher = Cipher.getInstance("AES");
                    cipher.init(Cipher.DECRYPT_MODE, ensecretKey);
                    byte[] input = new byte[64];
                    int bytesRead;
                    while ((bytesRead = eninFile.read(input)) != -1) {
                        byte[] output = cipher.update(input, 0, bytesRead);
                        enoutFile.write(output);
                    }
                    byte[] output = cipher.doFinal();
                    enoutFile.write(output);
                    eninFile.close();
                    enoutFile.flush();
                    enoutFile.close();
                    System.out.println("File Decrypted.");
                    //to handle the errors
                } catch (NoSuchAlgorithmException | NoSuchPaddingException
                         | InvalidKeyException | IOException
                         | IllegalBlockSizeException | BadPaddingException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        this.panel.add(addImage);

        //to submit the detials
        this.submit = new JButton("Submit");
        this.submit.setBounds(535, 420, 75, 25);
        submit.addActionListener(new ActionListener() {
            @Override
            //check if there are any empty textfields that needs to be filled
            public void actionPerformed(ActionEvent e) {
                if (doctorsName.getText().equals("") || timeFrame.getText().equals("") || patientFName1.getText().equals("")
                        || patientLName1.getText().equals("") || patientDOB1.getText().equals("")
                        || patientMobileNo1.getText().equals("") || patientID1.getText().equals("") || noOfHours.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "Please fill all the information! ");
                } else {
                    //If no errors the booking is successful
                    JOptionPane.showMessageDialog(frame, "Booking Successful!");
                    saveData(Integer.parseInt(noOfHours.getText()) * 15);//save data
                    dispose();
                    storing();//to save the data to the new table for the patient to view
                }
            }


        });
        this.panel.add(submit);

        this.frame.add(panel);
        this.frame.setVisible(true);

    }



    //For getting the data from the relevant text fields and sotring them in a new table for the
    //patient to view
    public void storing(){
        JFrame frame2 = new JFrame();
        this.frame2 = new JFrame("Consultation Deatils");
        this.frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame2.setSize(1080, 800);

        this.panel2 = new JPanel();
        this.panel2.setLayout(null);

//        for (int i = 0; i < manager.getDoctorsList().size();  i++) {
//            String sName = manager.getDoctorsList().get(i).getSurname();
//            String fName = manager.getDoctorsList().get(i).getName();
//            String specialisation = manager.getDoctorsList().get(i).getSpecialisation();
//            String medicalNo = manager.getDoctorsList().get(i).getMedicalLicenseNumber();
//            String dateAvailable = manager.getDoctorsList().get(i).getAvailableDate();
//            String timeAvailable = manager.getDoctorsList().get(i).getAvailableTime();
        //adding the items from the textfields to the strings

        String sDoctorsName = doctorsName.getText();
        String timeSlot = timeFrame.getText();
        String fname = patientFName1.getText();
        String lname = patientLName1.getText();
        String DOB = patientDOB1.getText();
        String mobileNum = patientMobileNo1.getText();
        String idNO = patientID1.getText();
        int costCal =(Integer.parseInt(noOfHours.getText()) * 15);

        Patient patient = new Patient(fname,lname,DOB,mobileNum,idNO,sDoctorsName,timeSlot,costCal);

        String[] column = {"Patient Name","Patient Surname", "DOB", "Mobile No", "ID No", "Doctors Name",
                "Time Slot", "Cost"};
        DefaultTableModel pTable = new DefaultTableModel(column, 0);
        patientTable = new JTable(pTable);
        patientTable.setBounds(10, 60, 800, 100);
        patientTable.setAutoCreateRowSorter(true);

            for(int i = 0; i< manager.getPatientList().size(); i++) {
                String fName = manager.getPatientList().get(i).getName();
                String sName = manager.getPatientList().get(i).getSurname();
                String DOfB = manager.getPatientList().get(i).getDateOfBirth();
                String IDNo = manager.getPatientList().get(i).getPatientID();
                String docName = manager.getPatientList().get(i).getDoctorIncharge();
                String appointmentTime = manager.getPatientList().get(i).getTimeSlot();
                int amoutCost = manager.getPatientList().get(i).getCost();

                Object[] row = {fName, sName, DOfB, IDNo, docName, appointmentTime, amoutCost};
                pTable.addRow(row);
            }


        this.columnLabel2 = new JLabel();
        this.columnLabel2.setText("First Name    | Surname Name        |DOB     | Mobile No.      |ID NO    |Doctor's Name  |Time Slot  |Cost" );
        this.columnLabel2.setBounds(10, 37, 800, 25);
        this.panel2.add(columnLabel2);
        patientTable.setShowGrid(true);
        patientTable.setGridColor(Color.BLACK);
        patientTable.setVisible(true);
        this.panel2.add(patientTable);
        this.frame2.add(panel2);
        this.frame2.setVisible(true);

    }


    //saving the data as a file to
    public void saveData(int total){
        ArrayList<String> contains = new ArrayList<String>();

        //adding items to the string
        boolean adding = true;
        while(adding){
            contains.add(doctorsName.getText());
            contains.add(timeFrame.getText());
            contains.add(patientFName1.getText());
            contains.add(patientLName1.getText());
            contains.add(patientDOB1.getText());
            contains.add(patientMobileNo1.getText());
            contains.add(patientID1.getText());
            contains.add(String.valueOf(total));
        }

        try{
            FileWriter writer = new FileWriter("GUIData.txt");
            for(int i =0; i < contains.size(); i ++){
                writer.write(contains.get(i) + "\n");
            }
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}





