package com.tis5.NossoSindico.repository;


import com.tis5.NossoSindico.domain.Condominio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CondominioRepository  extends JpaRepository<Condominio,Long> {
    Optional<Condominio> findByNome(String nome);
    Optional<Condominio> findByCode(String code);

}
