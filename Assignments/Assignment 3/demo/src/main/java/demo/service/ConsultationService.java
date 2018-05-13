package demo.service;

import demo.dto.ConsultationDto;
import demo.entity.Consultation;

import java.util.Date;
import java.util.List;

public interface ConsultationService {
    List<Consultation> getAll();
    Consultation findById(Integer id);
    Consultation create(ConsultationDto consultationDto);
    void delete(int consultationId);
    Consultation update(ConsultationDto consultationDto, Integer id);
    Consultation update(Consultation consultation);
    boolean isDoctorAvailable(int doctor_id, Date date);
    List<Consultation> findByPatient(Integer patient_id);
}
