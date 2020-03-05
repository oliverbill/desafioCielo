package br.com.cielo.enqueteapi.service;

import br.com.cielo.enqueteapi.domain.Enquete;
import br.com.cielo.enqueteapi.domain.Voto;
import br.com.cielo.enqueteapi.dto.EnqueteDto;
import br.com.cielo.enqueteapi.dto.ResultadoEnqueteDto;
import br.com.cielo.enqueteapi.exception.EnqueteApiException;
import br.com.cielo.enqueteapi.repository.EnqueteRepository;
import br.com.cielo.enqueteapi.repository.VotoRepository;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
public class EnqueteService {

    private final EnqueteRepository repository;
    private final VotoRepository votoRepository;

    public List<EnqueteDto> getTodas(){
        return repository.findAll().stream()
                .map(e -> e.toDto())
                .collect(Collectors.toList());
    }

    @Transactional
    public void criarEnquete(EnqueteDto dto){
        Enquete e = new Enquete(dto);
        repository.save(e);
    }

    @Transactional
    public ResultadoEnqueteDto apurarResultado(Long enqueteId){
        Enquete e = repository.findById(enqueteId).orElseThrow(() ->new EnqueteApiException("enquete nao encontrada: " + enqueteId));
        if(e.isFinalizada()){
            e.getAlternativas().forEach(o->{
                long quantidadeVotosDaOpcao = votoRepository.findByIdOpcaoResposta(o.getNumero()).stream().count();
                e.addVoto(o.getNumero(),quantidadeVotosDaOpcao);
            });
            repository.save(e);
            return new ResultadoEnqueteDto(e.toDto(),e.getVotosPorAlternativa());
        }else{
            throw new EnqueteApiException("Essa enquete ainda não foi finalizada. O resultado estará disponível após: "+e.getDataFim());
        }
    }

    @Transactional
    public void votar(Long enqueteId, Integer numeroAlternativa, String usuarioId){

        if(!repository.findById(enqueteId).isPresent()){
            throw new EnqueteApiException("enquete nao encontrada: " + enqueteId);
        }

        repository.findById(enqueteId).ifPresent(e ->{

            if(e.getAlternativas().stream().noneMatch(a -> a.getNumero().equals(numeroAlternativa))){
                throw new EnqueteApiException("alternativa invalida na enquete: " + numeroAlternativa);
            }

            e.getAlternativas().stream()
                .findAny()
                .filter(a->a.getNumero().equals(numeroAlternativa))
                .ifPresent(a-> {
                    Voto v = new Voto(e, a.getNumero(), usuarioId);
                    votoRepository.save(v);
                });
        });
    }
}
