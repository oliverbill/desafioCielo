package br.com.cielo.enquetesapi.domain;

import br.com.cielo.enquetesapi.controller.EnqueteDto;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Enquete {

    @Id
    private Long id;
    private String titulo;
    private List<OpcaoResposta> opcoes;
    @OneToMany
    private List<Voto> votos;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

    public Enquete(EnqueteDto dto){
        this.titulo = dto.getTitulo();
        this.opcoes = dto.getOpcoes();
        this.dataInicio = dto.getDataInicio();
        this.dataFim = dto.getDataFim();
    }

    public boolean isFinalizada(){
        return this.dataFim.isBefore(LocalDateTime.now()) ||
                this.dataFim.isEqual(LocalDateTime.now());
    }
}
