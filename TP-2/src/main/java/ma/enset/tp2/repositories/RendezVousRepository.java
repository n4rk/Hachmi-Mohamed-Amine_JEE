package ma.enset.tp2.repositories;

import ma.enset.tp2.entities.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;

public interface RendezVousRepository extends JpaRepository<RendezVous,String> {
    RendezVous findRendezVousByDate(Date date);
}
