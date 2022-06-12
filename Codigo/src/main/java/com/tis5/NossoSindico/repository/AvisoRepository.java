package com.tis5.NossoSindico.repository;


import com.tis5.NossoSindico.domain.Aviso;
import com.tis5.NossoSindico.domain.Condominio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AvisoRepository extends JpaRepository<Aviso,Long> {

    Optional<List<Aviso>> findByCondominio(Condominio condominio);
}
