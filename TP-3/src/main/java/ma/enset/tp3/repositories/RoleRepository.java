package ma.enset.tp3.repositories;

import ma.enset.tp3.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

// @Repository  (n'est pas obligatoire)
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);

}
