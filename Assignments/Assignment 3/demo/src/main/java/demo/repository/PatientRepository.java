package demo.repository;

import demo.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository  extends JpaRepository<Patient, Integer> {

    Patient findById(int id);
}
