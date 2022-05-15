package ma.enset.tp4.security.services;

import ma.enset.tp4.security.entities.AppRole;
import ma.enset.tp4.security.entities.AppUser;

public interface SecurityService {
    AppUser saveNewUser(String username, String password, String confirmPassword);
    AppRole saveNewRole(String roleName, String roleDescription);
    void addRoleToUser(String username, String roleName);
    void removeRoleFromUser(String username, String roleName);
    AppUser loadUserByUserName(String username);
}
