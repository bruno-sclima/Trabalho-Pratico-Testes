package com.tis5.NossoSindico.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AcessoCondominioResource {
    private String acesso;
    private long id_usuario;
    private int numeroApto;

}