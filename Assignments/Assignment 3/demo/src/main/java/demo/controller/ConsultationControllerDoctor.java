package demo.controller;

import demo.dto.ConsultationDto;
import demo.entity.Consultation;
import demo.service.ConsultationService;
import demo.service.PatientService;
import demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/consultations-doctor")
public class ConsultationControllerDoctor {

    @Autowired
    private ConsultationService consultationService;

    @Autowired
    private UserService userService;

    @Autowired
    private PatientService patientService;

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("message", "");
        model.addAttribute("patients", patientService.getAll());
        System.out.println("ConsultationController : return consultations-secretary.html");
        return "consultations-doctor";
    }

    @RequestMapping(params = "filter=", method = RequestMethod.GET)
    public String getInfo(Model model, @NotNull @RequestParam("patientId") Integer patientId) {
        System.out.println("ConsultationController : getInfo");
        List<Consultation> consultations = consultationService.findByPatient(patientId);
        Date now = new Date();
        model.addAttribute("consultations", consultations.stream().filter(c->c.getDate().getTime() < now.getTime()).toArray());
        model.addAttribute("message", "");
        model.addAttribute("patients", patientService.getAll());
        return "consultations-doctor";
    }

    @RequestMapping(params = "update=", method = RequestMethod.GET)
    public String updateInfo(Model model, @NotNull @RequestParam("consultationId") Integer consultationId, @RequestParam("details") String details) {
        System.out.println("ConsultationController : updateInfo");
        Consultation consultation = consultationService.findById(consultationId);
        consultation.setDetails(consultation.getDetails()==null?"":consultation.getDetails() + details);
        consultationService.update(consultation);
        Date now = new Date();
        model.addAttribute("message", "Updated details");
        model.addAttribute("patients", patientService.getAll());
        return "consultations-doctor";
    }
}
