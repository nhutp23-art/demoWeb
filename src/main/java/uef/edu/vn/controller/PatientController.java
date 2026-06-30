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

import uef.edu.vn.model.Patient;
import uef.edu.vn.repository.PatientRepository;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    // ==========================
    // Patient List
    // ==========================
    @GetMapping("")
    public String index(Model model) {

        model.addAttribute("patients", repository.getAll());

        return "patient/index";
    }

    // ==========================
    // Show Create Form
    // ==========================
    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("patient", new Patient());

        return "patient/form";
    }

    // ==========================
    // Show Edit Form
    // ==========================
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int patientID,
                       Model model) {

        Patient patient = repository.findById(patientID);

        if (patient == null) {
            return "redirect:/patient";
        }

        model.addAttribute("patient", patient);

        return "patient/form";
    }

    // ==========================
    // Save (Create + Update)
    // ==========================
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("patient") Patient patient,
                       BindingResult result,
                       Model model) {

        if (result.hasErrors()) {
            return "patient/form";
        }

        // Create
        if (patient.getPatientID() == 0) {

            if (repository.emailExists(patient.getEmail())) {

                model.addAttribute("error",
                        "Email already exists.");

                return "patient/form";
            }

            if (repository.phoneExists(patient.getPhoneNumber())) {

                model.addAttribute("error",
                        "Phone number already exists.");

                return "patient/form";
            }

            repository.insert(patient);

        } else {

            repository.update(patient);

        }

        return "redirect:/patient";
    }

    // ==========================
    // Delete
    // ==========================
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int patientID) {

        repository.delete(patientID);

        return "redirect:/patient";
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
                    "patients",
                    repository.getAll());

        } else {

            model.addAttribute(
                    "patients",
                    repository.search(keyword));
        }

        model.addAttribute("keyword", keyword);

        return "patient/index";
    }

}
