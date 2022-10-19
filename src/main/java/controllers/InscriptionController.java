package main.java.controllers;

import main.java.DTO.CareerReportDTO;
import main.java.DTO.InscriptionDTO;
import main.java.services.InscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/inscriptions")
public class InscriptionController {

    @Autowired
    private InscriptionService inscriptionService;

    @PostMapping()
    public InscriptionDTO save(@RequestBody InscriptionDTO iDTO){
        return inscriptionService.save(iDTO);
    }


    @GetMapping("/reports")
    public List<CareerReportDTO> getReports(){
            return inscriptionService.getReports();
    }
}
