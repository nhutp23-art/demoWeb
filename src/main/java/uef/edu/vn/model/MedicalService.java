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
public class MedicalService {

    @NotBlank(message = "Service code is required")
    @Size(max = 20, message = "Maximum 20 characters")
    private String serviceCode;

    @NotBlank(message = "Service name is required")
    @Size(max = 100)
    private String serviceName;

    @NotBlank(message = "Category is required")
    @Size(max = 100)
    private String category;

    @Size(max = 500)
    private String description;

    @DecimalMin(value = "0.01", message = "Fee must be greater than 0")
    private double fee;

    @Min(value = 1, message = "Duration must be greater than 0")
    private int duration;

    @NotBlank(message = "Status is required")
    @Pattern(regexp = "Available|Unavailable",
            message = "Status must be Available or Unavailable")
    private String status;

    public MedicalService() {
    }

    public MedicalService(String serviceCode, String serviceName,
            String category, String description,
            double fee, int duration, String status) {

        this.serviceCode = serviceCode;
        this.serviceName = serviceName;
        this.category = category;
        this.description = description;
        this.fee = fee;
        this.duration = duration;
        this.status = status;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
