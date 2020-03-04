package br.com.cielo.enquetesapi.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
public class VotoQuantidadeDto {

    private String enqueteTitulo;
    private Voto voto;
    private Long quantidade;
}
