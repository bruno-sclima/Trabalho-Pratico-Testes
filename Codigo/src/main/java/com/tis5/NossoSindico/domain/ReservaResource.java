package com.tis5.NossoSindico.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaResource {

    private LocalDate data;
    private long id_espaco;
    private long id_condominio;
    private int numero_pessoas;
    private int numero;
    private String descricao;
}