package ma.enset.tpetudiants.repositories;

import ma.enset.tpetudiants.entities.Etudiant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    Page<Etudiant> findByNomContainsOrPrenomContains(String kwN, String kwP,Pageable pageable);
}
