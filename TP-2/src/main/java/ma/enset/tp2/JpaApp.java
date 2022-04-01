package ma.enset.tp2;

import ma.enset.tp2.entities.Patient;
import ma.enset.tp2.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class JpaApp implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepo;
    public static void main(String[] args) {
        SpringApplication.run(JpaApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /*patientRepo.save(new Patient(null, "Amine", new Date(), false, 75));
        patientRepo.save(new Patient(null, "Mohamed", new Date(), false, 50));
        patientRepo.save(new Patient(null, "Mustapha", new Date(), false, 25));*/

        for(int i=0; i<100;i++) {
            patientRepo.save(new Patient(null, "Amine", new Date(), Math.random()>0.5?true:false, (int)(Math.random()*100)));
        }

        Page<Patient> patients = patientRepo.findAll(PageRequest.of(1,10));
        System.out.println("Total de pages : "+patients.getTotalPages());
        System.out.println("Total Elements : "+patients.getTotalElements());
        System.out.println("Num de page : "+patients.getNumber());
        List<Patient> content = patients.getContent(); // Content contains the list of elements (patients).
        //List<Patient> byMalade = patientRepo.findByMalade(true);
        Page<Patient> byMalade = patientRepo.findByMalade(true, PageRequest.of(0,10));
        // We can do content.forEach()... instead of patients.forEach()...
        byMalade.forEach(p -> {
            System.out.println("=================================");
            System.out.println(p.getId());
            System.out.println(p.getNom());
            System.out.println(p.getScore());
            System.out.println(p.getDateNaissance());
        });
        System.out.println("**********************************");
        //Patient patient = patientRepo.findById(new Long(1)).get();
        //Patient patient = patientRepo.findById(1L).get();
        //Patient patient = patientRepo.findById(1L).orElseThrow(()-> new RuntimeException("Patient not found!"));
        Patient patient = patientRepo.findById(2L).orElse(null);
        if(patient != null) {
            System.out.println(patient.getNom());
            System.out.println(patient.isMalade());
            patient.setMalade(true);
            patientRepo.save(patient);
            System.out.println("! Updated patient !");
            System.out.println(patient.getNom());
            System.out.println(patient.isMalade());
            //patientRepo.deleteById(1L);
        }
    }
}
