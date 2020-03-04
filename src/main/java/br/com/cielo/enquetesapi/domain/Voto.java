package br.com.cielo.enquetesapi.domain;

import br.com.cielo.enquetesapi.controller.VotoDto;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Voto {

    @Id
    private Long id;
    @ManyToOne
    private Enquete enquete;
    private Integer idOpcaoResposta;
    private String usuarioId;

    public Voto(Enquete enquete, Integer idOpcaoResposta, String usuarioId) {
        this.enquete = enquete;
        this.idOpcaoResposta = idOpcaoResposta;
        this.usuarioId = usuarioId;
    }

    public Voto(VotoDto dto){
        this.enquete = new Enquete(dto.getEnquete());
        this.idOpcaoResposta = dto.getIdOpcaoResposta();
        this.usuarioId = dto.getUsuarioId();
    }
}
