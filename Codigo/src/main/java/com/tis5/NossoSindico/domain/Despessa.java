package com.tis5.NossoSindico.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "despessa")
public class Despessa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_despessa")
    private long id;

    @Column
    private String titulo;

    @Column
    private String descricao;

    @Column
    private double valor;

    @Column
    private LocalDate data_referente;

    @ManyToOne
    private Condominio condominio;
}
