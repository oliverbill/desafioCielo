package br.com.cielo.enquetesapi.repository;

import br.com.cielo.enquetesapi.domain.ResultadoEnquete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoQuantidadeReposiroty extends JpaRepository<ResultadoEnquete,Long> {
}
