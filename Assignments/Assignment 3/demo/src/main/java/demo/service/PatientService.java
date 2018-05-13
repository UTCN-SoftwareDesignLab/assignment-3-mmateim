package demo.service;

import demo.dto.PatientDto;
import demo.entity.Patient;

import java.util.List;

public interface PatientService {
    List<Patient> getAll();
    Patient create(PatientDto patientDto);
    Patient update(PatientDto patientDto, Integer id);
}
