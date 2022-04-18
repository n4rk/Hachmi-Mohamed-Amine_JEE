package ma.enset.tp3.service;

import lombok.AllArgsConstructor;
import ma.enset.tp3.entities.Role;
import ma.enset.tp3.entities.User;
import ma.enset.tp3.repositories.RoleRepository;
import ma.enset.tp3.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Override
    public User addNewUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = findUserByUsername(username);
        Role role = findRoleByRoleName(roleName);
        if(user.getRoles()!=null) {
            user.getRoles().add(role);
            role.getUsers().add(user);
        }
        // On n'a pas besoin d'ajouter : userRepository.save(user); et roleRepository.save(role);
        // Puisque l'application a l'annotation @Transactional
    }

    @Override
    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if(user==null)
            throw new RuntimeException("Bad Credentials !");
        if(user.getPassword().equals(password)) {
            return user;
        }
        throw new RuntimeException("Bad Credentials !");
    }
}
