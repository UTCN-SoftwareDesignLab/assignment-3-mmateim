package demo.controller;


import demo.dto.PatientDto;
import demo.dto.UserDto;
import demo.service.PatientService;
import demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
@RequestMapping(value = "/patients")
public class PatientController {

    @Autowired
    private PatientService service;

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("message", "");
        model.addAttribute("patients", service.getAll());
        model.addAttribute("patientDto", new PatientDto());
        System.out.println("PatientController : return patients.html");
        return "patients";
    }

    @RequestMapping(params = "create=", method = RequestMethod.POST)
    public String createPatient(Model model, @Valid @ModelAttribute("patientDto") PatientDto patientDto, BindingResult bindingResult) {
        System.out.println("PatientController : create");
        String message;
        if (!bindingResult.hasErrors()) {
            if(service.create(patientDto) != null) {
                System.out.println("PatientController : create patient Done");
                message = "";
                model.addAttribute("patientDto", new PatientDto());
            }
            else {
                message = "SQL error at insert";
            }
        } else {
            message = getErrors(bindingResult);
        }
        if(!message.equals("")){
            model.addAttribute("patientDto", patientDto);
            System.out.println("PatientController : error at create patient");
        }
        model.addAttribute("message", message);
        model.addAttribute("patients", service.getAll());
        return "patients";
    }

    @RequestMapping(params = "update=", method = RequestMethod.POST)
    public String updatePatient(Model model, @Valid @ModelAttribute("patientDto") PatientDto patientDto, @NotNull @RequestParam("patientId") Integer patientId, BindingResult bindingResult) {
        System.out.println("PatientController : update");
        String message;
        if (!bindingResult.hasErrors()) {
            if(service.update(patientDto, patientId) != null) {
                System.out.println("PatientController : update patient Done");
                message = "";
                model.addAttribute("patientDto", new PatientDto());
            }
            else {
                message = "SQL error at update";
            }
        } else {
            message = getErrors(bindingResult);
        }
        if(!message.equals("")){
            model.addAttribute("patientDto", patientDto);
            System.out.println("PatientController : error at update patient");
        }
        model.addAttribute("message", message);
        model.addAttribute("patients", service.getAll());
        return "patients";
    }

    private String getErrors(BindingResult bindingResult){
        String message = "";
        List<ObjectError> errors = bindingResult.getAllErrors();
        for( ObjectError e : errors){
            message += "ERROR: " + e.getDefaultMessage();
        }
        return message;
    }
}

