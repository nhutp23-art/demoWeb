/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uef.edu.vn.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import uef.edu.vn.model.Appointment;
import uef.edu.vn.repository.AppointmentRepository;
import uef.edu.vn.repository.MedicalServiceRepository;
import uef.edu.vn.repository.PatientRepository;

/*@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private MedicalServiceRepository serviceRepository;

    // ==========================
    // Appointment List
    // ==========================
    @GetMapping("")
    public String index(Model model) {

        model.addAttribute("appointments", appointmentRepository.getAll());

        return "appointment/index";
    }

    // ==========================
    // Show Create Form
    // ==========================
    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("appointment", new Appointment());

        model.addAttribute("patients", patientRepository.getAll());

        model.addAttribute("services",
                serviceRepository.getAvailableServices());

        return "appointment/form";
    }

    // ==========================
    // Show Edit Form
    // ==========================
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int appointmentID,
            Model model) {

        Appointment appointment
                = appointmentRepository.findById(appointmentID);

        if (appointment == null) {
            return "redirect:/appointment";
        }

        model.addAttribute("appointment", appointment);

        model.addAttribute("patients",
                patientRepository.getAll());

        model.addAttribute("services",
                serviceRepository.getAvailableServices());

        return "appointment/form";
    }

    // ==========================
    // Save (Create + Update)
    // ==========================
    public void update(Appointment appointment) {

        String sql = "UPDATE Appointment SET patientID=?, serviceCode=?, appointmentDate=?, status=? WHERE appointmentID=?";

        jdbcTemplate.update(sql,
                appointment.getPatientID(),
                appointment.getServiceCode(),
                appointment.getAppointmentDate(),
                appointment.getStatus(),
                appointment.getAppointmentID());
    }

    @PostMapping("/save")
    public String save(
            @Valid @ModelAttribute("appointment") Appointment appointment,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {

            model.addAttribute("patients",
                    patientRepository.getAll());

            model.addAttribute("services",
                    serviceRepository.getAvailableServices());

            return "appointment/form";
        }

        if (appointment.getAppointmentID() == 0) {

            // Check service available
            if (!appointmentRepository.isServiceAvailable(
                    appointment.getServiceCode())) {

                model.addAttribute("error",
                        "Medical service is unavailable.");

                model.addAttribute("patients",
                        patientRepository.getAll());

                model.addAttribute("services",
                        serviceRepository.getAvailableServices());

                return "appointment/form";
            }

            // Check duplicate appointment
            if (appointmentRepository.isDuplicateAppointment(
                    appointment.getPatientID(),
                    appointment.getServiceCode(),
                {

                model.addAttribute("error",
                        "Patient already booked this service on this date.");

                model.addAttribute("patients",
                        patientRepository.getAll());

                model.addAttribute("services",
                        serviceRepository.getAvailableServices());

                return "appointment/form";
            }

            if (appointment.getStatus() == null
                    || appointment.getStatus().isBlank()) {

                appointment.setStatus("Pending");
            }

            appointmentRepository.insert(appointment);

        } else {

            appointmentRepository.update(appointment);

        }

        return "redirect:/appointment";
    }

    // ==========================
    // Delete
    // ==========================
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int appointmentID) {

        appointmentRepository.delete(appointmentID);

        return "redirect:/appointment";
    }

    // ==========================
    // Appointment History
    // ==========================
    @GetMapping("/history/{patientID}")
    public String history(@PathVariable int patientID,
            Model model) {

        model.addAttribute("appointments",
                appointmentRepository.getByPatient(patientID));

        return "appointment/history";
    }

    // ==========================
    // Search
    // ==========================
    @GetMapping("/search")
    public String search(
            @RequestParam(value = "keyword", required = false) String keyword,
            Model model) {

        if (keyword == null || keyword.trim().isEmpty()) {

            model.addAttribute("appointments",
                    appointmentRepository.getAll());

        } else {

            model.addAttribute("appointments",
                    appointmentRepository.search(keyword));
        }

        model.addAttribute("keyword", keyword);

        return "appointment/index";
    }

}*/
