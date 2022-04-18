package ma.enset.tp3.service;

import ma.enset.tp3.entities.Role;
import ma.enset.tp3.entities.User;

public interface UserService {
    User addNewUser(User user);
    Role addNewRole(Role role);
    User findUserByUsername(String username);
    Role findRoleByRoleName(String roleName);
    void addRoleToUser(String username, String roleName);
    User authenticate(String username, String password);
}
