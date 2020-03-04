package br.com.cielo.enquetesapi.controller;

import lombok.Data;

@Data
public class VotoDto {

    private EnqueteDto enquete;
    private Integer idOpcaoResposta;
    private String usuarioId;
}
