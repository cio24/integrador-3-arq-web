package main.java.controllers;

import main.java.DTO.InscriptionDTO;
import main.java.services.InscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inscriptions")
public class InscriptionController {

    @Autowired
    private InscriptionService inscriptionService;

    @PostMapping()
    public InscriptionDTO save(@RequestBody InscriptionDTO iDTO){
        return inscriptionService.save(iDTO);
    }
}
