package br.com.cielo.enquetesapi.service;

import br.com.cielo.enquetesapi.EnqueteApiException;
import br.com.cielo.enquetesapi.controller.EnqueteDto;
import br.com.cielo.enquetesapi.controller.ResultadoEnqueteDto;
import br.com.cielo.enquetesapi.domain.Enquete;
import br.com.cielo.enquetesapi.domain.ResultadoEnquete;
import br.com.cielo.enquetesapi.domain.Voto;
import br.com.cielo.enquetesapi.domain.VotoQuantidade;
import br.com.cielo.enquetesapi.repository.EnqueteRepository;
import br.com.cielo.enquetesapi.repository.ResultadoEnqueteReposiroty;
import br.com.cielo.enquetesapi.repository.VotoQuantidadeReposiroty;
import org.springframework.stereotype.Service;

@Service
public class EnqueteService {

    private EnqueteRepository repository;
    private ResultadoEnqueteReposiroty resultRepository;
    private VotoQuantidadeReposiroty votoRepository;

    public void criarEnquete(EnqueteDto dto){
        Enquete e = new Enquete(dto);
        repository.save(e);
    }

    public ResultadoEnqueteDto getResultado(Long enqueteId){
        Enquete e = repository.findById(enqueteId).orElseThrow(() ->new EnqueteApiException("enquete nao encontrada: " + enqueteId));
        if(e.isFinalizada()){
            ResultadoEnquete result = new ResultadoEnquete(repository.findVotosQuantidade(e.getId()));
            resultRepository.save(result);
            return result.toDto();
        }
        return null;
    }

    public void votar(Long enqueteId, Integer opcaoResposta, String usuarioId){
        repository.findById(enqueteId).ifPresent(e ->
            e.getOpcoes().stream()
                .findAny()
                .filter(o->o.getId().equals(opcaoResposta))
                .ifPresent(o-> {
                    Voto v = new Voto(e, o.getId(), usuarioId);
                    VotoQuantidade vq = new VotoQuantidade(e,v);
                    votoRepository.save(vq);
                    e.setVotos(opcaoResposta);
                    repository.save(e);
                })
        );
    }
}
