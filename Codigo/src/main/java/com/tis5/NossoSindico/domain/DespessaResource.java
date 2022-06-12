package com.tis5.NossoSindico.domain;

import lombok.Data;

import java.time.LocalDate;
@Data
public class DespessaResource {

    private String titulo;

    private String descricao;

    private double valor;

    private LocalDate data_referente;

    private long id_condominio;
}