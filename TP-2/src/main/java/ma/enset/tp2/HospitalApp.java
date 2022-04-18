package ma.enset.tp2;
import ma.enset.tp2.entities.*;
import ma.enset.tp2.repositories.MedecinRepository;
import ma.enset.tp2.repositories.PatientRepository;
import ma.enset.tp2.repositories.RendezVousRepository;
import ma.enset.tp2.service.IHospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalApp {
    public static void main(String[] args) {
        SpringApplication.run(HospitalApp.class, args);
    }

    @Bean
    CommandLineRunner start(IHospitalService hospitalService,
                            PatientRepository patientRepository,
                            MedecinRepository medecinRepository,
                            RendezVousRepository rendezVousRepository) {
        return args -> {
            Stream.of("Mohamed", "Amine", "Houssine")
                    .forEach(name->{
                        Patient patient = new Patient();
                        patient.setNom(name);
                        patient.setDateNaissance(new Date());
                        patient.setMalade(false);
                        hospitalService.savePatient(patient);
                    });
            Stream.of("Ahmed", "Ali", "Mustapha")
                    .forEach(name->{
                        Medecin medecin = new Medecin();
                        medecin.setNom(name);
                        medecin.setEmail(name+"@hospital.com");
                        medecin.setSpeciality(Math.random()>0.5?"Cardio":"Dentiste");
                        hospitalService.saveMedecin(medecin);
                    });

            //Patient p=patientRepository.findById(1L).orElse(null);
            Patient p=patientRepository.findByNom("Amine");
            Medecin m=medecinRepository.findByNom("Mustapha");

            RendezVous rdv = new RendezVous();
            rdv.setDate(new Date());
            rdv.setStatus(StatusRDV.PENDING);
            rdv.setMedecin(m);
            rdv.setPatient(p);
            RendezVous savedRdv = hospitalService.saveRDV(rdv);
            System.out.println(savedRdv.getId());

            RendezVous rendezVous = rendezVousRepository.findAll().get(0);
            Consultation consultation = new Consultation();
            consultation.setDateConsultation(new Date());
            consultation.setRendezVous(rendezVous);
            consultation.setRapport("Rapport de la consultation...");
            hospitalService.saveConsultation(consultation);
        };
    }
}
