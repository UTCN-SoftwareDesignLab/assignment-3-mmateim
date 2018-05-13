package demo.repository;

import demo.entity.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ConsultationRepository extends JpaRepository<Consultation, Integer> {

    Consultation findById(int id);
    List<Consultation> findByDate(Date date);
}