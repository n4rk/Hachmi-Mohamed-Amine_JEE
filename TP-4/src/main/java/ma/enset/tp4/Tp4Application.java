package ma.enset.tp4;

import ma.enset.tp4.entities.Patient;
import ma.enset.tp4.repositories.PatientRepository;
import ma.enset.tp4.security.services.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Date;

@SpringBootApplication
public class Tp4Application {

    public static void main(String[] args) {
        SpringApplication.run(Tp4Application.class, args);
    }
    //@Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepo) {
        return args -> {
            patientRepo.save(new Patient(null, "Amine", new Date(), true, 12));
            patientRepo.save(new Patient(null, "Bader", new Date(), false, 16));
            patientRepo.save(new Patient(null, "Anass", new Date(), false, 8));
            patientRepo.save(new Patient(null, "Soufiyan", new Date(), true, 21));
            patientRepo.save(new Patient(null, "Issam", new Date(), true, 15));

            patientRepo.findAll().forEach( p -> {
                System.out.println(p.getNom());
            });
        };
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //@Bean
    CommandLineRunner saveUsers(SecurityService securityService) {
        return args -> {
            securityService.saveNewUser("amine", "test", "test");
            securityService.saveNewUser("mod", "test", "test");

            securityService.saveNewRole("USER", "");
            securityService.saveNewRole("ADMIN", "");

            securityService.addRoleToUser("amine", "USER");
            securityService.addRoleToUser("mod", "USER");
            securityService.addRoleToUser("mod", "ADMIN");
        };
    }

}
