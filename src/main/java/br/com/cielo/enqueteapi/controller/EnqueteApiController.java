package br.com.cielo.enqueteapi.controller;

import br.com.cielo.enqueteapi.dto.EnqueteDto;
import br.com.cielo.enqueteapi.dto.ResultadoEnqueteDto;
import br.com.cielo.enqueteapi.service.EnqueteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Enquetes")
@RestController
@RequestMapping("enquetes")
@Data
public class EnqueteApiController {

    private final EnqueteService service;

    @GetMapping
    public List<EnqueteDto> getTodasEnquetes(){
        return service.getTodas();
    }

    @ApiOperation(value = "Retorna o resultado da enquete especificada pelo id. Somente o usuário que criou a enquete " +
            "pode ver seu resultado.", response = ResultadoEnqueteDto.class)
    @GetMapping("/resultado/{id}")
    public ResultadoEnqueteDto getResultados(@PathVariable("id") Long enqueteId){
        return service.apurarResultado(enqueteId);
    }

    @ApiOperation(value = "Registra um voto em uma alternativa de uma enquete para o usuario informado.")
    @PutMapping
    public void votarEnquete(@RequestParam Long enqueteId, @RequestParam Integer alternativaId,
                             @RequestParam String usuarioId)
    {
        service.votar(enqueteId,alternativaId,usuarioId);
    }

    @ApiOperation(value = "Cria uma enquete informando seus dados.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarEnquete(@RequestBody EnqueteDto dto) {
        service.criarEnquete(dto);
    }
}
