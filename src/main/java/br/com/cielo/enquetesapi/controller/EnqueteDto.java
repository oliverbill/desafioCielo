package br.com.cielo.enquetesapi.controller;

import br.com.cielo.enquetesapi.domain.OpcaoResposta;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class EnqueteDto {

    private String titulo;
    private List<OpcaoResposta> opcoes;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
}
