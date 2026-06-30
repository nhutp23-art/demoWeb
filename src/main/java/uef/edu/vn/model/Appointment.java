/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uef.edu.vn.model;

import com.google.protobuf.Timestamp;
import jakarta.validation.constraints.*;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Appointment {

    private int appointmentID;

    @NotNull(message = "Patient is required")
    private Integer patientID;

    // Hiển thị khi JOIN
    private String patientName;

    @NotBlank(message = "Service is required")
    private String serviceCode;

    // Hiển thị khi JOIN
    private String serviceName;

    @FutureOrPresent(message = "Appointment date must be today or later")
    private Date appointmentDate;

    @Pattern(
            regexp = "Pending|Confirmed|Completed|Cancelled",
            message = "Invalid status")
    private String status;

    private Timestamp createdAt;

    public Appointment() {
    }

    public Appointment(int appointmentID,
            Integer patientID,
            String patientName,
            String serviceCode,
            String serviceName,
            Date appointmentDate,
            String status,
            Timestamp createdAt) {

        this.appointmentID = appointmentID;
        this.patientID = patientID;
        this.patientName = patientName;
        this.serviceCode = serviceCode;
        this.serviceName = serviceName;
        this.appointmentDate = appointmentDate;
        this.status = status;
        this.createdAt = createdAt;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public Integer getPatientID() {
        return patientID;
    }

    public void setPatientID(Integer patientID) {
        this.patientID = patientID;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

}
