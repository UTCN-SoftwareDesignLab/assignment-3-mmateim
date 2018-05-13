package demo.service;

import demo.dto.PatientDto;
import demo.entity.Patient;
import demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService{

    PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }


    @Override
    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient create(PatientDto patientDto) {
        Patient patient = new Patient(patientDto.getName(), patientDto.getIdCardNr(), patientDto.getCnp(), patientDto.getAddress(), patientDto.getBirthDate());
        return patientRepository.save(patient);
    }

    @Override
    public Patient update(PatientDto patientDto, Integer id) {
        if(id == null)
            return null;
        Patient patient = patientRepository.findById(id);
        patient.setAddress(patientDto.getAddress());
        patient.setBirthDate(patientDto.getBirthDate());
        patient.setCnp(patientDto.getCnp());
        patient.setIdCardNr(patientDto.getIdCardNr());
        patient.setName(patientDto.getName());
        return patientRepository.save(patient);
    }
}
