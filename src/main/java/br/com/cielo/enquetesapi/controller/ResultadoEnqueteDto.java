package br.com.cielo.enquetesapi.controller;

import br.com.cielo.enquetesapi.domain.VotoQuantidadeDto;
import lombok.Data;

import java.util.List;

@Data
public class ResultadoEnqueteDto {

    private Long enqueteId;
    private List<VotoQuantidadeDto> votos;
}
