package ma.enset.tpetudiants.security.repositories;

import ma.enset.tpetudiants.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
    AppUser findByUsername(String username);
}
