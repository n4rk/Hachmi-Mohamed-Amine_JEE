package ma.enset.tp3;

import ma.enset.tp3.entities.Role;
import ma.enset.tp3.entities.User;
import ma.enset.tp3.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.stream.Stream;

@SpringBootApplication
public class Tp3Application {

    public static void main(String[] args) {
        SpringApplication.run(Tp3Application.class, args);
    }

    @Bean
    CommandLineRunner start(UserService userService) {
        return args -> {
            User u1 = new User();
            u1.setUsername("user");
            u1.setPassword("123456");
            userService.addNewUser(u1);

            User u2 = new User();
            u2.setUsername("admin");
            u2.setPassword("123456");
            userService.addNewUser(u2);

            Stream.of("STUDENT","ADMIN","USER").forEach(r->{
                Role r1 = new Role();
                r1.setRoleName(r);
                r1.setDesc("ROLE-"+r);
                userService.addNewRole(r1);
            });

            userService.addRoleToUser("user","USER");
            userService.addRoleToUser("admin","USER");
            userService.addRoleToUser("user","STUDENT");
            userService.addRoleToUser("admin","ADMIN");

            try {
                User user = userService.authenticate("user","123456");
                System.out.println(user.getUserId());
                System.out.println(user.getUsername());
                System.out.println("Roles => ");
                user.getRoles().forEach(r->{
                    // Solution 1 pour exception StackOverflow : System.out.println("Role : "+r.getRoleName().toString());
                    // Solution 2 : ajouter @ToString.Exclude
                    System.out.println("Role : "+r);
                });
            }catch(Exception e) {
                e.printStackTrace();
            }
        };
    }
}
