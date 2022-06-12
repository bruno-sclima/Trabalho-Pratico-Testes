package com.tis5.NossoSindico.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "espacocompartilhado")
public class Espaco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_espaco")
    private long id;

    @Column
    private String nome;

    @Column
    private String descricao;

    @Column
    private int capacidadeMax;

    @Column
    private int id_condominio;
}
