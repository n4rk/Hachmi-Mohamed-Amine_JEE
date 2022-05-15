package ma.enset.tpetudiants;

import ma.enset.tpetudiants.entities.Etudiant;
import ma.enset.tpetudiants.repositories.EtudiantRepository;
import ma.enset.tpetudiants.security.services.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Date;

@SpringBootApplication
public class TpEtudiantsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TpEtudiantsApplication.class, args);
    }
    //@Bean
    CommandLineRunner commandLineRunner(EtudiantRepository etudiantRepo) {
        return args -> {
            etudiantRepo.save(new Etudiant(null, "Hachmi", "Amine", "aminehcm0@gmail.com", new Date(), "MASCULIN", true));
            etudiantRepo.save(new Etudiant(null, "Barazzouk", "Bader", "bader@enset.ma", new Date(), "MASCULIN", true));
            etudiantRepo.save(new Etudiant(null, "Habbazi", "Soufiyan", "soufiyan@enset.ma", new Date(), "MASCULIN", true));
            etudiantRepo.save(new Etudiant(null, "Bricha", "Anass", "anass@enset.ma", new Date(), "MASCULIN", true));
            etudiantRepo.save(new Etudiant(null, "Bouizou", "Issam", "issam@enset.ma", new Date(), "MASCULIN", true));
            etudiantRepo.save(new Etudiant(null, "Chatt", "Soumaya", "soumaya@enset.ma", new Date(), "FEMININ", false));
            etudiantRepo.save(new Etudiant(null, "Benchemsi", "Selma", "selma@enset.ma", new Date(), "FEMININ", false));
            etudiantRepo.save(new Etudiant(null, "Harrak", "Wissal", "wissal@enset.ma", new Date(), "FEMININ", false));
            etudiantRepo.save(new Etudiant(null, "Trabelsi", "Assia", "assia@enset.ma", new Date(), "FEMININ", false));
            etudiantRepo.save(new Etudiant(null, "Serhane", "Souad", "souad@enset.ma", new Date(), "FEMININ", true));

            etudiantRepo.findAll().forEach( p -> {
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
