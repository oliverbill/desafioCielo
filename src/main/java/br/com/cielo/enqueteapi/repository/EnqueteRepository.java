package br.com.cielo.enqueteapi.repository;

import br.com.cielo.enqueteapi.domain.Enquete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnqueteRepository extends JpaRepository<Enquete,Long> {

}
