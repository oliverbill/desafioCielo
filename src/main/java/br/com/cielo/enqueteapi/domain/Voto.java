package br.com.cielo.enqueteapi.domain;

import br.com.cielo.enqueteapi.dto.VotoDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@Getter
public class Voto extends BaseEntity{

    @ManyToOne
    private Enquete enquete;
    private Integer idOpcaoResposta;
    private String usuarioId;

    public Voto(Enquete enquete, Integer idOpcaoResposta, String usuarioId) {
        this.enquete = enquete;
        this.idOpcaoResposta = idOpcaoResposta;
        this.usuarioId = usuarioId;
    }

    public VotoDto toDto(){
        return new VotoDto(this.enquete.toDto(),this.idOpcaoResposta,this.usuarioId);
    }
}
