package demo.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class ConsultationDto {

    @NotNull(message = "doctor may not be null")
    private Integer doctor_id;

    @NotNull(message = "patient may not be null")
    private Integer patient_id;

    @Future(message = "Date must be in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    public Integer getDoctor_id() {
        return doctor_id;
    }

    public Integer getPatient_id() {
        return patient_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDoctor_id(Integer doctor_id) {
        this.doctor_id = doctor_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
