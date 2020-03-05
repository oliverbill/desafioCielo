package br.com.cielo.enqueteapi.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@Embeddable
@NoArgsConstructor
public class Alternativa {

    private Integer numero;
    private String descricao;
}
