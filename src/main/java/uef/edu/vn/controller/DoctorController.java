/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uef.edu.vn.controller;

/**
 *
 * @author ADMIN
 */
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import uef.edu.vn.model.Doctor;
import uef.edu.vn.repository.DoctorRepository;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    // ==========================
    // Doctor List
    // ==========================
    @GetMapping("")
    public String index(Model model) {

        model.addAttribute("doctors", repository.getAll());

        return "doctor/index";
    }

    // ==========================
    // Show Create Form
    // ==========================
    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("doctor", new Doctor());

        return "doctor/form";
    }

    // ==========================
    // Show Edit Form
    // ==========================
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int doctorID,
                       Model model) {

        Doctor doctor = repository.findById(doctorID);

        if (doctor == null) {
            return "redirect:/doctor";
        }

        model.addAttribute("doctor", doctor);

        return "doctor/form";
    }

    // ==========================
    // Save (Create + Update)
    // ==========================
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("doctor") Doctor doctor,
                       BindingResult result,
                       Model model) {

        if (result.hasErrors()) {
            return "doctor/form";
        }

        // Add new
        if (doctor.getDoctorID() == 0) {

            if (repository.emailExists(doctor.getEmail())) {

                model.addAttribute("error", "Email already exists.");

                return "doctor/form";
            }

            if (repository.phoneExists(doctor.getPhoneNumber())) {

                model.addAttribute("error", "Phone number already exists.");

                return "doctor/form";
            }

            repository.insert(doctor);

        } else {

            repository.update(doctor);

        }

        return "redirect:/doctor";
    }

    // ==========================
    // Delete
    // ==========================
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int doctorID) {

        repository.delete(doctorID);

        return "redirect:/doctor";
    }

    // ==========================
    // Search
    // ==========================
    @GetMapping("/search")
    public String search(
            @RequestParam(value = "keyword", required = false)
            String keyword,
            Model model) {

        if (keyword == null || keyword.trim().isEmpty()) {

            model.addAttribute(
                    "doctors",
                    repository.getAll());

        } else {

            model.addAttribute(
                    "doctors",
                    repository.search(keyword));
        }

        model.addAttribute("keyword", keyword);

        return "doctor/index";
    }

}
