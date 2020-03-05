package br.com.cielo.enqueteapi.domain;

import br.com.cielo.enqueteapi.dto.EnqueteDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@NoArgsConstructor
@Getter
public class Enquete extends BaseEntity{

    private String titulo;
    @ElementCollection
    private List<Alternativa> alternativas;
    @OneToMany
    private List<Voto> votos;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    @Transient
    private Map<Integer,Long> votosPorAlternativa = new HashMap<>();

    public Enquete(EnqueteDto dto){
        this.titulo = dto.getTitulo();
        this.alternativas = dto.getOpcoes();
        this.dataInicio = dto.getDataInicio();
        this.dataFim = dto.getDataFim();
    }

    public boolean isFinalizada(){
        return this.dataFim.isBefore(LocalDateTime.now()) ||
                this.dataFim.isEqual(LocalDateTime.now());
    }

    public void addVoto(Integer alternativa,Long numeroDeVotos){
        this.votosPorAlternativa.put(alternativa,numeroDeVotos);
    }

    public EnqueteDto toDto(){
        return new EnqueteDto(this.titulo,this.alternativas,this.dataInicio,this.dataFim);
    }
}
