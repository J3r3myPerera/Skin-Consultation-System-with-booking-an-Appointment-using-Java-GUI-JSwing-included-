package com.compnay;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface SkinConsultationManager {

    void addDoctor(Doctor doctor);
    void deleteDoctor(String medicalLicenseNumber);
    void printDoctors();
    void saveData() throws IOException, ClassNotFoundException;

    void readData() throws IOException;

}