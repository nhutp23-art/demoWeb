/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uef.edu.vn.model;

import jakarta.validation.constraints.*;

/**
 *
 * @author ADMIN
 */
public class Doctor {

    private int doctorID;

    @NotBlank(message = "Full name is required")
    @Size(max = 100)
    private String fullName;

    @NotBlank(message = "Specialty is required")
    @Size(max = 100)
    private String specialty;

    @Email(message = "Invalid email")
    private String email;

    @Pattern(regexp = "^(0)[0-9]{9,10}$",
            message = "Invalid phone number")
    private String phoneNumber;

    public Doctor() {
    }

    public Doctor(int doctorID, String fullName,
            String specialty, String email,
            String phoneNumber) {

        this.doctorID = doctorID;
        this.fullName = fullName;
        this.specialty = specialty;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
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
