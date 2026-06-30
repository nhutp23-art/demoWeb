/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uef.edu.vn.controller;

import jakarta.validation.Valid;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import uef.edu.vn.model.DoctorAssignment;
import uef.edu.vn.repository.DoctorAssignmentRepository;
import uef.edu.vn.repository.DoctorRepository;
import uef.edu.vn.repository.MedicalServiceRepository;

@Controller
@RequestMapping("/assignment")
public class DoctorAssignmentController {

    @Autowired
    private DoctorAssignmentRepository assignmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private MedicalServiceRepository serviceRepository;

    // ==========================
    // Assignment List
    // ==========================
    @GetMapping("")
    public String index(Model model) {

        model.addAttribute("assignments", assignmentRepository.getAll());

        return "assignment/index";
    }

    // ==========================
    // Show Create Form
    // ==========================
    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("assignment", new DoctorAssignment());

        model.addAttribute("services", serviceRepository.getAll());

        model.addAttribute("doctors", doctorRepository.getAll());

        return "assignment/form";
    }

    // ==========================
    // Show Edit Form
    // ==========================
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int assignmentID,
                       Model model) {

        DoctorAssignment assignment =
                assignmentRepository.findById(assignmentID);

        if (assignment == null) {
            return "redirect:/assignment";
        }

        model.addAttribute("assignment", assignment);

        model.addAttribute("services",
                serviceRepository.getAll());

        model.addAttribute("doctors",
                doctorRepository.getAll());

        return "assignment/form";
    }

    // ==========================
    // Save (Create + Update)
    // ==========================
    @PostMapping("/save")
    public String save(
            @Valid @ModelAttribute("assignment")
            DoctorAssignment assignment,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {

            model.addAttribute("services",
                    serviceRepository.getAll());

            model.addAttribute("doctors",
                    doctorRepository.getAll());

            return "assignment/form";
        }

        // Check Medical Service
        if (serviceRepository.findByCode(
                assignment.getServiceCode()) == null) {

            model.addAttribute("error",
                    "Medical service does not exist.");

            model.addAttribute("services",
                    serviceRepository.getAll());

            model.addAttribute("doctors",
                    doctorRepository.getAll());

            return "assignment/form";
        }

        // Check Doctor
        if (doctorRepository.findById(
                assignment.getDoctorID()) == null) {

            model.addAttribute("error",
                    "Doctor does not exist.");

            model.addAttribute("services",
                    serviceRepository.getAll());

            model.addAttribute("doctors",
                    doctorRepository.getAll());

            return "assignment/form";
        }

        if (assignment.getAssignmentID() == 0) {

            // Prevent duplicate assignment
            if (assignmentRepository.existsByService(
                    assignment.getServiceCode())) {

                model.addAttribute("error",
                        "This service has already been assigned.");

                model.addAttribute("services",
                        serviceRepository.getAll());

                model.addAttribute("doctors",
                        doctorRepository.getAll());

                return "assignment/form";
            }

            // Nếu chưa chọn ngày thì lấy ngày hiện tại
            if (assignment.getAssignmentDate() == null) {
                assignment.setAssignmentDate(
                        new Date(System.currentTimeMillis()));
            }

            assignmentRepository.insert(assignment);

        } else {

            assignmentRepository.update(assignment);

        }

        return "redirect:/assignment";
    }

    // ==========================
    // Delete
    // ==========================
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int assignmentID) {

        assignmentRepository.delete(assignmentID);

        return "redirect:/assignment";
    }

}
