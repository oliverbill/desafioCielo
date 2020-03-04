package br.com.cielo.enquetesapi.domain;

import br.com.cielo.enquetesapi.controller.ResultadoEnqueteDto;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
@Data
public class ResultadoEnquete {

    @Id
    private Long id;
    @OneToOne
    private Enquete enquete;
    @OneToMany
    private List<VotoQuantidade> votos;

    public ResultadoEnquete(List<VotoQuantidade> votos) {
        this.enquete = votos.get(0).getEnquete();
        this.votos = votos;
    }

    public ResultadoEnqueteDto toDto() {
        ResultadoEnqueteDto dto = new ResultadoEnqueteDto();
        dto.
        this.enquete = votos.get(0).getEnquete();
        this.votos = votos;
    }
}
