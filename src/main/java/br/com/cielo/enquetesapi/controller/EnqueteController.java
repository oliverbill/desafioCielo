package br.com.cielo.enquetesapi.controller;

import br.com.cielo.enquetesapi.service.EnqueteService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("enquetes")
public class EnqueteController {

    private EnqueteService service;

    public EnqueteController(EnqueteService service) {
        this.service = service;
    }

    @GetMapping("/resultado/{id}")
    public ResultadoEnqueteDto getResultados(@RequestParam("id") Long enqueteId){
        return service.getResultado(enqueteId);
    }

    @PutMapping
    public void votarEnquete(@Param("id") Long enqueteId, @Param("resposta") Integer opcaoResposta){
        service.votar(enqueteId,opcaoResposta);
    }

    @PostMapping
    public void criarEnquete(EnqueteDto dto) {
        service.criarEnquete(dto);
    }
}
