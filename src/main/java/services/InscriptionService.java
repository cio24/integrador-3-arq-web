package main.java.services;

import main.java.DTO.InscriptionDTO;
import main.java.entities.Inscription;
import main.java.repositories.InscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InscriptionService {

    @Autowired
    private InscriptionRepository inscriptionRepository;

    public InscriptionDTO saveInscription(Inscription inscription){
        Inscription i = inscriptionRepository.save(inscription);
        return new InscriptionDTO(i.getStudent().getName(), i.getCareer().getName());
    }
}
