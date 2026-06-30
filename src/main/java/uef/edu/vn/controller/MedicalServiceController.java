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

import uef.edu.vn.model.MedicalService;
import uef.edu.vn.repository.MedicalServiceRepository;

@Controller
@RequestMapping("/medicalservice")
public class MedicalServiceController {

    @Autowired
    private MedicalServiceRepository repository;

    // ==========================
    // Service List
    // ==========================
    @GetMapping("")
    public String index(Model model) {

        model.addAttribute("services", repository.getAll());

        return "medicalservice/index";
    }

    // ==========================
    // Show Create Form
    // ==========================
    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("service", new MedicalService());

        return "medicalservice/form";
    }

    // ==========================
    // Save Service
    // ==========================
    @PostMapping("/create")
    public String create(
            @Valid @ModelAttribute("service") MedicalService service,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "medicalservice/form";
        }

        if (repository.exists(service.getServiceCode())) {

            model.addAttribute("error",
                    "Service Code already exists.");

            return "medicalservice/form";
        }

        repository.insert(service);

        return "redirect:/medicalservice";
    }

    // ==========================
    // Show Edit Form
    // ==========================
    @GetMapping("/edit/{code}")
    public String edit(
            @PathVariable("code") String serviceCode,
            Model model) {

        MedicalService service =
                repository.findByCode(serviceCode);

        if (service == null) {
            return "redirect:/medicalservice";
        }

        model.addAttribute("service", service);

        return "medicalservice/form";
    }

    // ==========================
    // Update Service
    // ==========================
    @PostMapping("/edit")
    public String update(
            @Valid @ModelAttribute("service") MedicalService service,
            BindingResult result) {

        if (result.hasErrors()) {
            return "medicalservice/form";
        }

        repository.update(service);

        return "redirect:/medicalservice";
    }

    // ==========================
    // Delete Service
    // ==========================
    @GetMapping("/delete/{code}")
    public String delete(
            @PathVariable("code") String serviceCode) {

        repository.delete(serviceCode);

        return "redirect:/medicalservice";
    }

    // ==========================
    // Search
    // ==========================
    @GetMapping("/search")
    public String search(
            @RequestParam(value = "keyword", required = false) String keyword,
            Model model) {

        if (keyword == null || keyword.trim().isEmpty()) {
            model.addAttribute("services", repository.getAll());
        } else {
            model.addAttribute("services", repository.search(keyword));
        }

        model.addAttribute("keyword", keyword);

        return "medicalservice/index";
    }

}
