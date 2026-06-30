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
public class DoctorAssignment {

    private int assignmentID;

    @NotBlank(message = "Service code is required")
    private String serviceCode;

    // Dùng để hiển thị khi JOIN
    private String serviceName;

    @NotNull(message = "Doctor is required")
    private Integer doctorID;

    // Dùng để hiển thị khi JOIN
    private String doctorName;

    private Date assignmentDate;

    public DoctorAssignment() {
    }

    public DoctorAssignment(int assignmentID, String serviceCode,
            String serviceName, Integer doctorID,
            String doctorName, Date assignmentDate) {

        this.assignmentID = assignmentID;
        this.serviceCode = serviceCode;
        this.serviceName = serviceName;
        this.doctorID = doctorID;
        this.doctorName = doctorName;
        this.assignmentDate = assignmentDate;
    }

    public int getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
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

    public Integer getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(Integer doctorID) {
        this.doctorID = doctorID;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Date getAssignmentDate() {
        return assignmentDate;
    }

    public void setAssignmentDate(Date assignmentDate) {
        this.assignmentDate = assignmentDate;
    }

}
