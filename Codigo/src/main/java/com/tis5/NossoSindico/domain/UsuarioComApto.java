package com.tis5.NossoSindico.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioComApto {

    private long id;

    private String nome;

    private String sobrenome;

    private String email;

    private String senha;

    private int nroApto;
}