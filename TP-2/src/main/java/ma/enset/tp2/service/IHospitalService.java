package ma.enset.tp2.service;

import ma.enset.tp2.entities.Consultation;
import ma.enset.tp2.entities.Medecin;
import ma.enset.tp2.entities.Patient;
import ma.enset.tp2.entities.RendezVous;

public interface IHospitalService {
    Patient savePatient(Patient patient);
    Medecin saveMedecin(Medecin medecin);
    RendezVous saveRDV(RendezVous rendezVous);
    Consultation saveConsultation(Consultation consultation);
}
