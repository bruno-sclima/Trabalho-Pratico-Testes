package com.tis5.NossoSindico.repository;

import com.tis5.NossoSindico.domain.Apartamento;
import com.tis5.NossoSindico.domain.Condominio;
import com.tis5.NossoSindico.domain.Usuario;
import com.tis5.NossoSindico.domain.UsuarioComApto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApartamentoRepository  extends JpaRepository<Apartamento,Long> {

    Optional<List<Apartamento>> findByUsuario(Usuario usuario);
    Optional<List<Apartamento>> findByCondominio(Condominio condominio);

}
