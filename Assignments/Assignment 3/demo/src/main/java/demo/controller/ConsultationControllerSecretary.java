package demo.controller;

import demo.dto.ConsultationDto;
import demo.entity.User;
import demo.service.ConsultationService;
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
@RequestMapping(value = "/consultations-secretary")
public class ConsultationControllerSecretary {

    @Autowired
    private ConsultationService consultationService;

    @Autowired
    private UserService userService;

    @Autowired
    private PatientService patientService;

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("consultations", consultationService.getAll());
        model.addAttribute("message", "");
        model.addAttribute("doctors", userService.getAll().stream().filter(d -> d.getRole().equals("DOCTOR")).toArray());
        model.addAttribute("patients", patientService.getAll());
        model.addAttribute("consultationDto", new ConsultationDto());
        System.out.println("ConsultationController : return consultations-secretary.html");
        return "consultations-secretary";
    }

    @RequestMapping(params = "create=", method = RequestMethod.POST)
    public String createConsultation(Model model, @Valid @ModelAttribute("consultationDto") ConsultationDto consultationDto, BindingResult bindingResult) {
        System.out.println("ConsultationController : create");
        String message;
        if (!bindingResult.hasErrors()) {
            if (consultationService.isDoctorAvailable(consultationDto.getDoctor_id(), consultationDto.getDate())) {
                if (consultationService.create(consultationDto) != null) {
                    System.out.println("ConsultationController : create consultation Done");
                    message = "";
                    model.addAttribute("consultationDto", new ConsultationDto());
                } else {
                    message = "SQL error at insert";
                }
            } else {
                message = "Doctor is not available for this date";
            }
        } else {
            message = getErrors(bindingResult);
        }
        if (!message.equals("")) {
            model.addAttribute("consultationDto", consultationDto);
            System.out.println("ConsultationController : error at create consultation");
        }
        model.addAttribute("consultations", consultationService.getAll());
        model.addAttribute("message", message);
        model.addAttribute("doctors", userService.getAll().stream().filter(d -> d.getRole().equals("DOCTOR")).toArray());
        model.addAttribute("patients", patientService.getAll());
        return "consultations-secretary";
    }

    @RequestMapping(params = "update=", method = RequestMethod.POST)
    public String updateConsultation(Model model, @Valid @ModelAttribute("consultationDto") ConsultationDto consultationDto, @NotNull @RequestParam("consultationId") Integer consultationId, BindingResult bindingResult) {
        System.out.println("ConsultationController : update");
        String message;
        if (!bindingResult.hasErrors()) {
            if (consultationService.update(consultationDto, consultationId) != null) {
                System.out.println("ConsultationController : update consultation Done");
                message = "";
                model.addAttribute("consultationDto", new ConsultationDto());
            } else {
                message = "SQL error at update";
            }
        } else {
            message = getErrors(bindingResult);
        }
        if (!message.equals("")) {
            model.addAttribute("consultationDto", consultationDto);
            System.out.println("ConsultationController : error at update consultation");
        }
        model.addAttribute("consultations", consultationService.getAll());
        model.addAttribute("message", message);
        model.addAttribute("doctors", userService.getAll().stream().filter(d -> d.getRole().equals("DOCTOR")).toArray());
        model.addAttribute("patients", patientService.getAll());
        return "consultations-secretary";
    }

    @RequestMapping(params = "delete=", method = RequestMethod.GET)
    public String deleteConsultation(Model model, @RequestParam("deleteId") Integer deleteId) {
        System.out.println("ConsultationController : delete");
        String message = "";
        if (deleteId != null) {
            consultationService.delete(deleteId);
        } else {
            message = "Id field is empty";
        }
        model.addAttribute("consultationDto", new ConsultationDto());
        model.addAttribute("consultations", consultationService.getAll());
        model.addAttribute("doctors", userService.getAll().stream().filter(d -> d.getRole().equals("DOCTOR")).toArray());
        model.addAttribute("patients", patientService.getAll());
        model.addAttribute("message", message);
        return "consultations-secretary";
    }

    private String getErrors(BindingResult bindingResult) {
        String message = "";
        List<ObjectError> errors = bindingResult.getAllErrors();
        for (ObjectError e : errors) {
            message += "ERROR: " + e.getDefaultMessage();
        }
        return message;
    }
}
