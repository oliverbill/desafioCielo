package br.com.cielo.enquetesapi.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Data
public class VotoQuantidade {

    @OneToOne
    private Enquete enquete;
    private Voto voto;

    //autoIncrement
    private Long quantidade;

    public VotoQuantidade(Enquete enquete, Voto voto, Long quantidade) {
        this.enquete = enquete;
        this.voto = voto;
        this.quantidade = quantidade;
    }
}
