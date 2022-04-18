package ma.enset.tp3.repositories;

import ma.enset.tp3.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

// @Repository  (n'est pas obligatoire)
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}
