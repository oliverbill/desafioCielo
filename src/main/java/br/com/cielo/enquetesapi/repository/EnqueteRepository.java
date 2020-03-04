package br.com.cielo.enquetesapi.repository;

import br.com.cielo.enquetesapi.domain.Enquete;
import br.com.cielo.enquetesapi.domain.VotoQuantidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnqueteRepository extends JpaRepository<Enquete,Long> {

    List<VotoQuantidade> findVotosQuantidade(Long id);
}
