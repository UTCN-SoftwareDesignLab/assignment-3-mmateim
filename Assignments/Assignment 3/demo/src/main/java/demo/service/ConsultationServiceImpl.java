package demo.service;

import demo.dto.ConsultationDto;
import demo.entity.Consultation;
import demo.entity.Patient;
import demo.entity.User;
import demo.repository.ConsultationRepository;
import demo.repository.PatientRepository;
import demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ConsultationServiceImpl implements ConsultationService {

    ConsultationRepository consultationRepository;
    PatientRepository patientRepository;
    UserRepository userRepository;

    @Autowired
    public ConsultationServiceImpl(ConsultationRepository consultationRepository, PatientRepository patientRepository, UserRepository userRepository) {
        this.consultationRepository = consultationRepository;
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Consultation> getAll() {
        return consultationRepository.findAll();
    }

    @Override
    public Consultation findById(Integer id) {
        return consultationRepository.findById(id);
    }

    @Override
    public Consultation create(ConsultationDto consultationDto) {
        Patient patient = patientRepository.findById(consultationDto.getPatient_id());
        User doctor = userRepository.findById(consultationDto.getDoctor_id());
        Consultation consultation = new Consultation(doctor, patient, consultationDto.getDate());
        consultation.setDetails("");
        return consultationRepository.save(consultation);
    }

    @Override
    public void delete(int consultationId) {
        consultationRepository.delete(consultationId);
    }

    @Override
    public Consultation update(ConsultationDto consultationDto, Integer id) {
        Patient patient = patientRepository.findById(consultationDto.getPatient_id());
        User doctor = userRepository.findById(consultationDto.getDoctor_id());
        Consultation consultation = new Consultation(doctor, patient, consultationDto.getDate());
        consultation.setId(id);
        return consultationRepository.save(consultation);
    }

    @Override
    public Consultation update(Consultation consultation) {
        return consultationRepository.save(consultation);
    }

    @Override
    public boolean isDoctorAvailable(int doctor_id, Date date) {
        List<Consultation> consultations = consultationRepository.findByDate(date);
        for(Consultation c : consultations){
            if(c.getDoctor().getId() == doctor_id)
                return false;
        }
        return true;
    }

    @Override
    public List<Consultation> findByPatient(Integer patient_id) {
        return consultationRepository.findByPatient_id(patient_id);
    }
}
