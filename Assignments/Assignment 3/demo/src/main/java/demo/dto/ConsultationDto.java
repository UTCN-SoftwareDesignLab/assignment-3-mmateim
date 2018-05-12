package demo.dto;

import demo.entity.Patient;
import demo.entity.User;

import javax.validation.constraints.Future;
import java.util.Date;

public class ConsultationDto {

    private User doctor;
    private Patient patient;

    @Future
    private Date date;
}
