package com.tis5.NossoSindico.repository;

import com.tis5.NossoSindico.domain.Condominio;
import com.tis5.NossoSindico.domain.Despessa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DespessaRepository   extends JpaRepository<Despessa,Long> {
    Optional<List<Despessa>> findByCondominio(Condominio condominio);
}
