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
@Table(name = "apartamento")
public class Apartamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_apartamento")
    private long id;

    @Column
    private String bloco;

    @Column
    private Integer numero;

    @Column
    private boolean sindico;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Condominio condominio;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Usuario usuario;

}
