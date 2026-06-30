/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uef.edu.vn.model;

import jakarta.validation.constraints.*;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Patient {

    private int patientID;

    @NotBlank(message = "Full name is required")
    @Size(max = 100)
    private String fullName;

    @Past(message = "Date of birth must be in the past")
    private Date dateOfBirth;

    @Email(message = "Invalid email")
    private String email;

    @Pattern(regexp = "^(0)[0-9]{9,10}$",
            message = "Invalid phone number")
    private String phoneNumber;

    public Patient() {
    }

    public Patient(int patientID, String fullName,
            Date dateOfBirth,
            String email,
            String phoneNumber) {

        this.patientID = patientID;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
