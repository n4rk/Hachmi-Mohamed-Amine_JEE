package ma.enset.tp2.repositories;

import ma.enset.tp2.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;

public interface ConsultationRepository extends JpaRepository<Consultation,Long> {
    Consultation findConsultationByDateConsultation(Date date);
}
