package com.tis5.NossoSindico.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CadCondAptoResource {
    private String nome;
    private String rua;
    private String bairro;
    private String cep;
    private String cidade;
    private int numero;
    private int numeroApto;
    private long id_usuario;
    private long id_condominio;
}